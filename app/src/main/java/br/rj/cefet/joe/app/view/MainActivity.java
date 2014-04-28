package br.rj.cefet.joe.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.rj.cefet.joe.app.R;
import br.rj.cefet.joe.app.controller.Controller;
import br.rj.cefet.joe.app.util.Constantes;

public class MainActivity extends Activity {

    private Button btJogar;
    private Button btTreinar;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new Controller(MainActivity.this);

        this.btJogar = (Button) this.findViewById(R.id.btJogar);
        this.btTreinar = (Button) this.findViewById(R.id.btTreinar);

        this.btJogar.setOnClickListener(this.handleJogarEvent);
        this.btTreinar.setOnClickListener(this.handleTreinarEvent);
    }

    private final View.OnClickListener handleJogarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handleJogarEvent", "botão jogar acionado");

            controller.iniciarPartida(Constantes.ID_JOGAR);
        }
    };

    private final View.OnClickListener handleTreinarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handleTreinarEvent", "botão treinar acionado");

            controller.iniciarPartida(Constantes.ID_TREINAR);
        }
    };

//    public void mostrarMsg() {
//        //exemplo chamada de msgs personalizadas:
//        Mensagem.show(this, "Aqui vai o texto que será mostrado", Constantes.INFORMACAO);
//
//        Mensagem.show(this, "Aqui vai o texto que será mostrado", Constantes.AVISO);
//
//        Mensagem.show(this, "Aqui vai o texto que será mostrado", Constantes.ERRO);
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
