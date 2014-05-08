package br.rj.cefet.joe.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.rj.cefet.joe.app.R;
import br.rj.cefet.joe.app.controller.Controller;
import br.rj.cefet.joe.app.model.entidade.Jogo;
import br.rj.cefet.joe.app.util.Constantes;

public class MainActivity extends Activity {

    private Button btJogar;
    private Button btTreinar;

    private TextView tvTotalAcertos;
    private TextView tvPontuacao;
    private TextView tvTempoTotalTreino;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new Controller(MainActivity.this);

        this.btJogar = (Button) this.findViewById(R.id.btJogar);
        this.btTreinar = (Button) this.findViewById(R.id.btTreinar);

        this.tvTotalAcertos = (TextView) this.findViewById(R.id.tvTotalAcertos);
        this.tvPontuacao = (TextView) this.findViewById(R.id.tvPontuacao);
        this.tvTempoTotalTreino = (TextView) this.findViewById(R.id.tvTempoTotalTreino);

        this.btJogar.setOnClickListener(this.handleJogarEvent);
        this.btTreinar.setOnClickListener(this.handleTreinarEvent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setHistoricoJogo();
    }

    private final View.OnClickListener handleJogarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
//            Log.d("MainActivity.handleJogarEvent", "botão jogar acionado");

            controller.iniciarPartida(Constantes.ID_JOGAR);
        }
    };

    private final View.OnClickListener handleTreinarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
//            Log.d("MainActivity.handleTreinarEvent", "botão treinar acionado");

            controller.iniciarPartida(Constantes.ID_TREINAR);
        }
    };

    private void setHistoricoJogo() {
        Jogo jogo = controller.getRegistroJogo();
        tvTotalAcertos.setText(String.valueOf(jogo.getQtdAcertosTotal()));
        tvPontuacao.setText(String.valueOf(jogo.getPontuacaoTotal()));
        tvTempoTotalTreino.setText(String.valueOf(jogo.getTempoTotalTreino() / 60) + " minutos");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
