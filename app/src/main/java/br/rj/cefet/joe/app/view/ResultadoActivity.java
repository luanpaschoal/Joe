package br.rj.cefet.joe.app.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.rj.cefet.joe.app.R;
import br.rj.cefet.joe.app.controller.Controller;
import br.rj.cefet.joe.app.util.Constantes;

public class ResultadoActivity extends Activity {

    private TextView tvDuracao;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        this.controller = new Controller(ResultadoActivity.this);

        this.tvDuracao = (TextView) this.findViewById(R.id.tvDuracao);



    }

//    private final View.OnClickListener handleJogarEvent = new View.OnClickListener() {
//        @Override
//        public void onClick(final View view) {
//            Log.d("MainActivity.handleJogarEvent", "botão jogar acionado");
//
//            controller.iniciarPartida(Constantes.ID_JOGAR);
//        }
//    };


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
