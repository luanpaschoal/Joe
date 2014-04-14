package br.rj.cefet.joe.app.controller;

import android.content.Context;

import java.util.ArrayList;

import br.rj.cefet.joe.app.model.Model;
import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.util.Constantes;

/**
 * Created by Luan on 12/04/2014.
 */

public class PartidaController {
    private Model model;
    private ArrayList<Palavra> palavras;
    private int qtdPalavrasJogadas;
    private int duracao;
    private int qtdErrosHifen;
    private int qtdAcertosHifen;
    private int qtdAcertosAcentuacao;
    private int qtdErrosAcentuacao;
    private int tempoLimite;

    public PartidaController(Context app_context) {
        palavras = new ArrayList<Palavra>();
        model = new Model(app_context);
    }

    public void iniciarPartida(int idModoJogo) {
        palavras.clear();
        palavras = model.getPalavras(idModoJogo);

        switch (idModoJogo) {
            case Constantes.ID_JOGAR:
                jogar(palavras);
                break;
            case Constantes.ID_TREINAR:
                treinar(palavras);
                break;
        }
    }

    private void treinar(ArrayList<Palavra> palavras) {
        //TODO:
    }

    private void jogar(ArrayList<Palavra> palavras) {
        //TODO:
    }

    public void mostrarMsg() {
        //TODO:
    }

    /*public void addTarefa(final String titulo)
    {
        final ContentValues data = new ContentValues();
        data.put("titulo", titulo);
        model.addTarefa(data);
    }

    public void deleteTarefa(final String titulo)
    {
        model.deleteTarefa("titulo='" + titulo + "'");
    }

    public void deleteTarefa(final long id)
    {
        model.deleteTarefa("id='" + id + "'");
    }

    public void deleteAllTarefa()
    {
        model.deleteTarefa(null);
    }*/
}
