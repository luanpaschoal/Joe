package br.rj.cefet.joe.app.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import br.rj.cefet.joe.app.model.Model;
import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.view.PartidaActivity;
import br.rj.cefet.joe.app.view.ResultadoActivity;

/**
 * Created by Luan on 12/04/2014.
 */

public class Controller extends Activity {
    private Model model;
    private Context ctx;
    private int duracao;
    private int qtdErrosHifen;
    private int qtdAcertosHifen;
    private int qtdAcertosAcentuacao;
    private int qtdErrosAcentuacao;

    public Controller(Context app_context) {
        model = new Model(app_context);
        ctx = app_context;
    }

    public void iniciarPartida(int idModoJogo) {
        Intent it = new Intent(ctx, PartidaActivity.class);
        it.putExtra("MODO_ESCOLHIDO", idModoJogo);
        ctx.startActivity(it);
    }

    public ArrayList<Palavra> getPalavras(int idModoJogo) {
        return model.getPalavras(idModoJogo);
    }

    public void mostrarResultado() {
        Intent it = new Intent(ctx, ResultadoActivity.class);
        it.putExtra("DURACAO", duracao);
        it.putExtra("ERROS_HIFEN", qtdErrosHifen);
        it.putExtra("ACERTOS_HIFEN", qtdAcertosHifen);
        it.putExtra("ACERTOS_ACENTUACAO", qtdAcertosAcentuacao);
        it.putExtra("ERROS_ACENTUACAO", qtdErrosAcentuacao);
        ctx.startActivity(it);
    }

    /*public void addTarefa(final String titulo)
    {
        final ContentValues data = new ContentValues();
        data.put("titulo", titulo);
        model.addTarefa(data);
    }*/

    public String getDica(int idRegra) {
        return model.getDica(idRegra);
    }

    public int getIdNorma(int idRegra) {
        return model.getIdNorma(idRegra);
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getQtdErrosHifen() {
        return qtdErrosHifen;
    }

    public void setQtdErrosHifen(int qtdErrosHifen) {
        this.qtdErrosHifen = qtdErrosHifen;
    }

    public int getQtdAcertosHifen() {
        return qtdAcertosHifen;
    }

    public void setQtdAcertosHifen(int qtdAcertosHifen) {
        this.qtdAcertosHifen = qtdAcertosHifen;
    }

    public int getQtdAcertosAcentuacao() {
        return qtdAcertosAcentuacao;
    }

    public void setQtdAcertosAcentuacao(int qtdAcertosAcentuacao) {
        this.qtdAcertosAcentuacao = qtdAcertosAcentuacao;
    }

    public int getQtdErrosAcentuacao() {
        return qtdErrosAcentuacao;
    }

    public void setQtdErrosAcentuacao(int qtdErrosAcentuacao) {
        this.qtdErrosAcentuacao = qtdErrosAcentuacao;
    }
}
