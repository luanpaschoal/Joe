package br.rj.cefet.joe.app.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Map;

import br.rj.cefet.joe.app.model.Model;
import br.rj.cefet.joe.app.model.entidade.Jogo;
import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.view.MainActivity;
import br.rj.cefet.joe.app.view.PartidaActivity;
import br.rj.cefet.joe.app.view.ResultadoActivity;

/**
 * Created by Luan on 12/04/2014.
 */

public class Controller {
    private Model model;
    private Context ctx;

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

    public void mostrarResultado(Map<String, Integer> parametros) {
        Intent it = new Intent(ctx, ResultadoActivity.class);
        it.putExtra("ERROS_HIFEN", parametros.get("ERROS_HIFEN"));
        it.putExtra("ERROS_ACENTUACAO", parametros.get("ERROS_ACENTUACAO"));
        it.putExtra("ACERTOS_HIFEN", parametros.get("ACERTOS_HIFEN"));
        it.putExtra("ACERTOS_ACENTUACAO", parametros.get("ACERTOS_ACENTUACAO"));
        it.putExtra("DURACAO", parametros.get("DURACAO"));
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

    public String getDificuldade(int idRegra) {
        return model.getDificuldade(idRegra);
    }

    public void setPalavraVisualizada(String nomePalavra) {
        model.setPalavraVisualizada(nomePalavra);
    }

    public void addPontuacao(int pontuacao) {
        model.updatePontuacao(pontuacao);
    }

    public void addAcertos(int qtdAcertos) {
        model.updateAcertos(qtdAcertos);
    }

    public void addTempoTreino(int duracaoEmSegundos) {
        model.updateTempoTreino(duracaoEmSegundos);
    }

    public Jogo getRegistroJogo() {
        if (model.getRegistroJogo() == null) {
            Jogo jogo = new Jogo();
            jogo.setPontuacaoTotal(0);
            jogo.setTempoTotalTreino(0);
            jogo.setQtdAcertosTotal(0);
            return jogo;
        } else {
            return model.getRegistroJogo();
        }
    }
}
