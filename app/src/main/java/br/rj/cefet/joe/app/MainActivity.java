package br.rj.cefet.joe.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.rj.cefet.joe.app.controller.PartidaController;
import br.rj.cefet.joe.app.util.Constantes;

public class MainActivity extends Activity {

    private Button btnJogar;
    private Button btnTreinar;

    private PartidaController partidaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.partidaController = new PartidaController(this);

        this.btnJogar = (Button) this.findViewById(R.id.btnJogar);
        this.btnTreinar = (Button) this.findViewById(R.id.btnTreinar);

        this.btnJogar.setOnClickListener(this.handleJogarEvent);
        this.btnTreinar.setOnClickListener(this.handleTreinarEvent);
    }

    private final View.OnClickListener handleJogarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handleJogarEvent", "botão jogar acionado");

            partidaController.iniciarPartida(Constantes.ID_JOGAR);
        }
    };

    private final View.OnClickListener handleTreinarEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Log.d("MainActivity.handleTreinarEvent", "botão estudar acionado");

            partidaController.iniciarPartida(Constantes.ID_TREINAR);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
