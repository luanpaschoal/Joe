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
    private TextView tvAcertosAcentuacao;
    private TextView tvErrosAcentuacao;
    private TextView tvAcertosHifen;
    private TextView tvErrosHifen;

    private Controller controller;

    private int duracao;
    private int qtdErrosHifen;
    private int qtdAcertosHifen;
    private int qtdAcertosAcentuacao;
    private int qtdErrosAcentuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        this.controller = new Controller(ResultadoActivity.this);

        this.tvDuracao = (TextView) this.findViewById(R.id.tvDuracao);
        this.tvAcertosAcentuacao = (TextView) this.findViewById(R.id.tvAcertosAcentuacao);
        this.tvErrosAcentuacao = (TextView) this.findViewById(R.id.tvErrosAcentuacao);
        this.tvAcertosHifen = (TextView) this.findViewById(R.id.tvAcertosHifen);
        this.tvErrosHifen = (TextView) this.findViewById(R.id.tvErrosHifen);

        Bundle extras = getIntent().getExtras();
        duracao = extras.getInt("DURACAO");
        qtdErrosHifen = extras.getInt("ERROS_HIFEN");
        qtdAcertosHifen = extras.getInt("ACERTOS_HIFEN");
        qtdAcertosAcentuacao = extras.getInt("ACERTOS_ACENTUACAO");
        qtdErrosAcentuacao = extras.getInt("ERROS_ACENTUACAO");

        setTextoComponentes();

        addTotalAcertos();
        addPontuacao();
        addTempoDedicadoTreino();

    }

    private void addTotalAcertos() {

    }

    private void addPontuacao() {

    }

    private void addTempoDedicadoTreino() {

    }

    private void setTextoComponentes(){
        tvDuracao.setText(duracao + " segundos");
        tvAcertosAcentuacao.setText(String.valueOf(qtdAcertosAcentuacao));
        tvErrosAcentuacao.setText(String.valueOf(qtdErrosAcentuacao));
        tvAcertosHifen.setText(String.valueOf(qtdAcertosHifen));
        tvErrosHifen.setText(String.valueOf(qtdErrosHifen));
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
