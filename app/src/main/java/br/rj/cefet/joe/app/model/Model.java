package br.rj.cefet.joe.app.model;

/**
 * Created by Luan on 12/04/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.util.Constantes;
import br.rj.cefet.joe.app.util.CriaTabelas;
import br.rj.cefet.joe.app.util.Mensagem;
import br.rj.cefet.joe.app.util.PopulaTabelas;
import br.rj.cefet.joe.app.view.PartidaView;

public final class Model {
    private final SQLiteDatabase database = null;

    public Model(final Context ctx) {
        CriaTabelas criaTabelas = new CriaTabelas(ctx);
        PopulaTabelas populaTabelas = new PopulaTabelas(ctx);
    }

    public void add(ContentValues data, String tabela) {
        this.database.insert(tabela, null, data);
    }

    public void delete(final String field_params, String tabela) {
        this.database.delete(tabela, field_params, null);
    }

    public Cursor loadAll(String tabela, String[] columns) {
        Log.d("Model", "loadAll()");

        final Cursor c = this.database.query(tabela,
                columns, null, null, null, null, null);

        return c;
    }

//    public Cursor getPalavras(ModoJogo modoJogo) {
//        Log.d("Model", "getPalavras()");
//
//        final Cursor c = this.database.rawQuery("SELECT * FROM " + Constantes.PALAVRA + " WHERE idModoJogo +=?",
//                new String[]{String.valueOf(modoJogo.getId())});
//
//        return c;
//    }

    public ArrayList<Palavra> getPalavras(int idModoJogo) {
        Log.d("Model", "getPalavras()");
        try {
            String tabela = Constantes.PALAVRA;
            String[] collumns = null;
            String where = " idModoJogo+=? ";
            String[] parametros = new String[]{String.valueOf(idModoJogo)};
            String groupBy = null;
            String having = null;
            String orderby = null;

            final Cursor c = this.database.query(tabela, collumns, where, parametros, groupBy, having, orderby);
            ArrayList<Palavra> palavras = new ArrayList<Palavra>();
            Palavra palavra = new Palavra();

            if (c != null) {
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    palavra.setNome(c.getString(1));
                    palavra.setAudio(c.getString(2));
                    palavra.setUso(c.getString(3));
                    palavra.setQtdVisualizacao(c.getInt(4));
                    palavra.setQtdErros(c.getInt(5));

                    palavras.add(palavra);
                    c.moveToNext();
                }
                c.close();
            }
            return palavras;
        } catch (SQLiteException e) {
            Log.e("Model.getPalavras", e.toString(), e);
            return null;
        }
    }
}
