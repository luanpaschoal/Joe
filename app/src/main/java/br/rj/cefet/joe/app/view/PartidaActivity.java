package br.rj.cefet.joe.app.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.rj.cefet.joe.app.R;
import br.rj.cefet.joe.app.controller.Controller;
import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.util.Constantes;
import br.rj.cefet.joe.app.util.Mensagem;

public class PartidaActivity extends Activity {

    private TextView tvModoJogo;
    private TextView tvContagemRegressiva;
    private Button btOuvir;
    private EditText etPalavraDigitada;
    private Button btVerificarPalavra;
    private TextView tvQtdErros;
    private TextView tvQtdAcertos;

    private Controller controller;
    private ArrayList<Palavra> palavras;
    MediaPlayer mediaPlayer;
    private Chronometer cronometro;

    private int modoJogo;
    //auxiliar para avançar na lista de palavra
    private int posicaoAtual = 0;
    private int qtdErrosAcentuacao = 0;
    private int qtdAcertosAcentuacao = 0;
    private int qtdErrosHifen = 0;
    private int qtdAcertosHifen = 0;
    private int pontuacao = 0;
    private boolean isPermitidoDica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        this.controller = new Controller(PartidaActivity.this);
        this.palavras = new ArrayList<Palavra>();
        this.mediaPlayer = new MediaPlayer();

        this.tvModoJogo = (TextView) this.findViewById(R.id.tvModoJogo);
        this.tvContagemRegressiva = (TextView) this.findViewById(R.id.tvContagemRegressiva);
        this.btOuvir = (Button) this.findViewById(R.id.btOuvir);
        this.etPalavraDigitada = (EditText) this.findViewById(R.id.etPalavraDigitada);
        this.btVerificarPalavra = (Button) this.findViewById(R.id.btVerificarPalavra);
        this.tvQtdErros = (TextView) this.findViewById(R.id.tvQtdErros);
        this.tvQtdAcertos = (TextView) this.findViewById(R.id.tvQtdAcertos);

        cronometro = new Chronometer(this);

        Bundle extras = getIntent().getExtras();
        modoJogo = extras.getInt("MODO_ESCOLHIDO");
        palavras = controller.getPalavras(modoJogo);

        ajustaTela(modoJogo);
        prepararAudio();
        iniciarCronometro();

        this.btOuvir.setOnClickListener(this.handleOuvirEvent);
        this.etPalavraDigitada.setOnKeyListener(this.handleEnterApertadoEvent);
        this.btVerificarPalavra.setOnClickListener(this.handleVerificaPalavraEvent);
    }

    private void ajustaTela(int modoJogo) {
        switch (modoJogo) {
            case Constantes.ID_JOGAR:
                modoJogar();
                break;
            case Constantes.ID_TREINAR:
                modoTreinar();
                break;
        }
    }

    private void modoJogar() {
        tvModoJogo.setText(Constantes.MODO_JOGAR);
        isPermitidoDica = false;
        tvContagemRegressiva.setVisibility(View.VISIBLE);
        contagemRegressiva();
    }

    private void contagemRegressiva() {
        new CountDownTimer(Constantes.TEMPO_PARTIDA, Constantes.TEMPO_INTERVALO) {

            public void onTick(long millisUntilFinished) {
                tvContagemRegressiva.setText(millisUntilFinished / 1000 + " segundos restantes");
            }

            public void onFinish() {
                tvContagemRegressiva.setText("Acabou o tempo!");
                if (PartidaActivity.this.hasWindowFocus()) {
                    terminarPartida();
                }
            }
        }.start();
    }

    private void modoTreinar() {
        tvModoJogo.setText(Constantes.MODO_TREINAR);
        isPermitidoDica = true;
        tvContagemRegressiva.setVisibility(View.INVISIBLE);
    }


    private final View.OnClickListener handleOuvirEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
//            Log.d("MainActivity.handleOuvirEvent", "botão ouvir acionado");
            tocarAudio();
        }
    };

    private final View.OnKeyListener handleEnterApertadoEvent = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        esconderTecladoVirtual();
                        verificarPalavraDigitada();
                        if (modoJogo == Constantes.ID_JOGAR) {
                            prepararProximaPalavra();
                            tocarAudio();
                        }
                        return true;
                }
            }
            return false;
        }
    };

    private void esconderTecladoVirtual() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etPalavraDigitada.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private final View.OnClickListener handleVerificaPalavraEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
//            Log.d("MainActivity.handleVerificaPalavraEvent", "botão próxima palavra acionado");
            verificarPalavraDigitada();

            if (modoJogo == Constantes.ID_JOGAR) {
                prepararProximaPalavra();
                tocarAudio();
            }
        }
    };

    private void verificarPalavraDigitada() {
        if (posicaoAtual < Constantes.QTD_PALAVRAS_PARTIDA) {
            int idRegra = getPalavras().get(posicaoAtual).getIdRegra();
            int idNormaPalavra = controller.getIdNorma(idRegra);
            String palavraAtual = palavras.get(posicaoAtual).getNome();
            String palavraDigitada = etPalavraDigitada.getText().toString();

            if (palavraDigitada.equalsIgnoreCase(palavraAtual)) {
                alterarAnimacaoBackgroud(R.drawable.borda_acerto);

                addQtdAcertoNorma(idNormaPalavra);

                addPontuacao(controller.getDificuldade(idRegra), modoJogo);
                tvQtdAcertos.setText(String.valueOf(qtdAcertosAcentuacao + qtdAcertosHifen));
            } else {
                mostrarPalavraCorreta(palavraAtual);
                alterarAnimacaoBackgroud(R.drawable.borda_erro);

                addQtdErroNorma(idNormaPalavra);
                tvQtdErros.setText(String.valueOf(qtdErrosAcentuacao + qtdErrosHifen));
            }
            mostrarDica(isPermitidoDica);
        }
    }

    private void addQtdAcertoNorma(int idNormaPalavra) {
        switch (idNormaPalavra) {
            case Constantes.ID_HIFEN:
                qtdAcertosHifen++;
                break;
            case Constantes.ID_ACENTUACAO:
                qtdAcertosAcentuacao++;
                break;
        }

    }

    private void addPontuacao(String dificuldade, int modoJogo) {
        switch (modoJogo) {
            case Constantes.ID_JOGAR:
                if (Constantes.REGRA_FACIL.equalsIgnoreCase(dificuldade)) {
                    pontuacao += Constantes.PONTOS_FACIL;
                } else if (Constantes.REGRA_DIFICIL.equalsIgnoreCase(dificuldade)) {
                    pontuacao += Constantes.PONTOS_DIFICIL;
                }
                break;
        }
    }

    private void addQtdErroNorma(int idNormaPalavra) {
        switch (idNormaPalavra) {
            case Constantes.ID_HIFEN:
                qtdErrosHifen++;
                break;
            case Constantes.ID_ACENTUACAO:
                qtdErrosAcentuacao++;
                break;
        }
    }

    private void mostrarPalavraCorreta(String palavraAtual) {
        Mensagem.show(PartidaActivity.this, "O correto é: " + palavraAtual, Constantes.AVISO);
    }

    private void mostrarDica(boolean isPermitidoDica) {
        if (isPermitidoDica) {
            int idRegra = getPalavras().get(posicaoAtual).getIdRegra();
            String texto = controller.getDica(idRegra);

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(R.string.dica);
            alertDialog.setMessage(texto);
            alertDialog.setIcon(R.drawable.informacao);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    voltarBackgroundNormal();
                    prepararProximaPalavra();
                    tocarAudio();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alertDialog.show();
        }
    }

    private void alterarAnimacaoBackgroud(int borda) {
        RelativeLayout tela = (RelativeLayout) findViewById(R.id.rlTelaPartida);
        tela.setBackgroundResource(borda);

        Animation animacaoBorda = AnimationUtils.loadAnimation(PartidaActivity.this, R.anim.efeito_alpha);
        tela.startAnimation(animacaoBorda);

    }

    private void voltarBackgroundNormal(){
        RelativeLayout tela = (RelativeLayout) findViewById(R.id.rlTelaPartida);
        tela.setBackgroundResource(R.drawable.borda_normal);
        Animation animacaoBorda = AnimationUtils.loadAnimation(PartidaActivity.this, R.anim.efeito_alpha_background_normal);
        tela.startAnimation(animacaoBorda);
    }

    private void prepararProximaPalavra() {
        etPalavraDigitada.setText("");
        posicaoAtual++;
        if (posicaoAtual == Constantes.QTD_PALAVRAS_PARTIDA) {
            terminarPartida();
        } else {
            mediaPlayer.reset();
            prepararAudio();
        }
    }

    private void prepararAudio() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            String nomeArquivo = getPalavras().get(posicaoAtual).getAudio();
            Uri myUri = Uri.parse("android.resource://br.rj.cefet.joe.app/raw/" + nomeArquivo);
            mediaPlayer.setDataSource(PartidaActivity.this, myUri);
            mediaPlayer.prepare();
            this.mediaPlayer = mediaPlayer;

            controller.setPalavraVisualizada(getPalavras().get(posicaoAtual).getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tocarAudio() {
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("tocarAudio", e.getMessage());
        }
    }

    private void iniciarCronometro() {
        cronometro.setBase(SystemClock.elapsedRealtime());

        cronometro.start();
    }

    private void terminarPartida() {
        mediaPlayer.release();
        cronometro.stop();
        long elapsedMillis = SystemClock.elapsedRealtime() - cronometro.getBase();
        int duracaoEmSegundos = (int) elapsedMillis / 1000;

        this.finish();

        Map<String, Integer> parametros = new HashMap<String, Integer>();
        parametros.put("ERROS_HIFEN", qtdErrosHifen);
        parametros.put("ERROS_ACENTUACAO", qtdErrosAcentuacao);
        parametros.put("ACERTOS_HIFEN", qtdAcertosHifen);
        parametros.put("ACERTOS_ACENTUACAO", qtdAcertosAcentuacao);
        parametros.put("DURACAO", duracaoEmSegundos);

        registrarHistorico(pontuacao, duracaoEmSegundos, qtdAcertosAcentuacao + qtdAcertosHifen);
        controller.mostrarResultado(parametros);
    }

    private void registrarHistorico(int pontuacao, int duracaoEmSegundos, int qtdAcertos) {
        switch (modoJogo) {
            case Constantes.ID_JOGAR:
                controller.addPontuacao(pontuacao);
                controller.addAcertos(qtdAcertos);
                break;
            case Constantes.ID_TREINAR:
                controller.addTempoTreino(duracaoEmSegundos);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void setModoJogo(int modoJogo) {
        this.modoJogo = modoJogo;
    }

    public int getModoJogo() {
        return modoJogo;
    }

    public void setPalavras(ArrayList<Palavra> palavras) {
        this.palavras = palavras;
    }

    public ArrayList<Palavra> getPalavras() {
        return palavras;
    }
}
