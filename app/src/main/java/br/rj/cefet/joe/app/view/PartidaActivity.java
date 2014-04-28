package br.rj.cefet.joe.app.view;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
    private Button btProximaPalavra;
    private TextView tvQtdErros;
    private TextView tvQtdAcertos;

    private Controller controller;
    private ArrayList<Palavra> palavras = new ArrayList<Palavra>();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private Chronometer cronometro;

    private int modoJogo;
    //auxiliar para avançar na lista de palavra
    private int posicaoAtual = 0;
    private int qtdErros = 0;
    private int qtdAcertos = 0;
    private boolean isPermitidoDica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_view);

        this.controller = new Controller(PartidaActivity.this);

        this.tvModoJogo = (TextView) this.findViewById(R.id.tvModoJogo);
        this.tvContagemRegressiva = (TextView) this.findViewById(R.id.tvContagemRegressiva);
        this.btOuvir = (Button) this.findViewById(R.id.btOuvir);
        this.etPalavraDigitada = (EditText) this.findViewById(R.id.etPalavraDigitada);
        this.btProximaPalavra = (Button) this.findViewById(R.id.btProximaPalavra);
        this.tvQtdErros = (TextView) this.findViewById(R.id.tvQtdErros);
        this.tvQtdAcertos = (TextView) this.findViewById(R.id.tvQtdAcertos);

        Bundle extras = getIntent().getExtras();
        modoJogo = extras.getInt("MODO_ESCOLHIDO");
        palavras = controller.getPalavras(modoJogo);

        ajustaTela(modoJogo);
        prepararAudio();
        iniciarCronometro();

        this.btOuvir.setOnClickListener(this.handleOuvirEvent);
        this.etPalavraDigitada.setOnClickListener(this.handlePalavraDigitadaEvent);
        this.btProximaPalavra.setOnClickListener(this.handleProximaPalavraEvent);
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
                tvContagemRegressiva.setText(millisUntilFinished / 1000 + " segundos restantes.");
            }

            public void onFinish() {
                tvContagemRegressiva.setText("Acabou o tempo!");
                terminarPartida();
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
            Log.d("MainActivity.handleOuvirEvent", "botão ouvir acionado");
            tocarAudio();
        }
    };

    private final View.OnClickListener handlePalavraDigitadaEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handlePalavraDigitadaEvent", "botão palavra digitada acionado");
            etPalavraDigitada.setText("");
        }
    };

    private final View.OnClickListener handleProximaPalavraEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handleProximaPalavraEvent", "botão próxima palavra acionado");
            verificarPalavraDigitada();
            etPalavraDigitada.setText("");
            carregarProximaPalavra();
        }

        private void verificarPalavraDigitada() {
            int idRegra = getPalavras().get(posicaoAtual).getIdRegra();
            int idNormaPalavra = controller.getIdNorma(idRegra);
            String palavraAtual = palavras.get(posicaoAtual).getNome();
            String palavraDigitada = etPalavraDigitada.getText().toString();

            if (palavraAtual.equalsIgnoreCase(palavraDigitada)) {
                alterarAnimacaoBackgroud(R.drawable.borda_acerto);
                qtdAcertos++;
                tvQtdAcertos.setText(String.valueOf(qtdAcertos));
                addQtdAcertoNorma(idNormaPalavra, qtdAcertos);
            } else {
                mostraDica(isPermitidoDica);
                alterarAnimacaoBackgroud(R.drawable.borda_erro);
                qtdErros++;
                tvQtdErros.setText(String.valueOf(qtdErros));
                addQtdErroNorma(idNormaPalavra, qtdErros);
            }
        }

        private void addQtdAcertoNorma(int idNormaPalavra, int qtdAcertos) {
            switch (idNormaPalavra) {
                case Constantes.ID_HIFEN:
                    controller.setQtdAcertosHifen(qtdAcertos);
                    break;
                case Constantes.ID_ACENTUACAO:
                    controller.setQtdAcertosAcentuacao(qtdAcertos);
                    break;
            }

        }

        private void addQtdErroNorma(int idNormaPalavra, int qtdErros) {
            switch (idNormaPalavra) {
                case Constantes.ID_HIFEN:
                    controller.setQtdErrosHifen(qtdErros);
                    break;
                case Constantes.ID_ACENTUACAO:
                    controller.setQtdErrosAcentuacao(qtdErros);
                    break;
            }
        }

        private void mostraDica(boolean isPermitidoDica) {
            if (isPermitidoDica) {
                int idRegra = getPalavras().get(posicaoAtual).getIdRegra();
                String texto = controller.getDica(idRegra);

                Mensagem.show(PartidaActivity.this, texto, Constantes.INFORMACAO);
            }
        }

        private void alterarAnimacaoBackgroud(int borda) {
            RelativeLayout tela = (RelativeLayout) findViewById(R.id.rlTelaPartida);
            tela.setBackgroundResource(borda);

            Animation animacaoBorda = AnimationUtils.loadAnimation(PartidaActivity.this, R.anim.efeito_alpha);
            tela.startAnimation(animacaoBorda);
        }

        private void carregarProximaPalavra() {
            posicaoAtual++;
            if (posicaoAtual == Constantes.QTD_PALAVRAS_PARTIDA) {
                terminarPartida();
            } else {
                mediaPlayer.reset();
                prepararAudio();
            }
        }
    };

    private void prepararAudio() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            String nomeArquivo = getPalavras().get(posicaoAtual).getAudio();
            Uri myUri = Uri.parse("android.resource://br.rj.cefet.joe.app/raw/" + nomeArquivo);
            mediaPlayer.setDataSource(PartidaActivity.this, myUri);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tocarAudio() {
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarCronometro(){
        cronometro.setBase(SystemClock.elapsedRealtime());

        cronometro.start();
    }

    private void terminarPartida() {
        cronometro.stop();
        long elapsedMillis = SystemClock.elapsedRealtime() - cronometro.getBase();
        int duracaoEmSegundos = (int) elapsedMillis / 1000;
        controller.setDuracao(duracaoEmSegundos);

        controller.mostrarResultado();
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
