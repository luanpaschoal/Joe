package br.rj.cefet.joe.app.model;

/**
 * Created by Luan on 12/04/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import br.rj.cefet.joe.app.model.entidade.Jogo;
import br.rj.cefet.joe.app.model.entidade.Palavra;
import br.rj.cefet.joe.app.util.Constantes;
import br.rj.cefet.joe.app.util.CriaTabelas;
import br.rj.cefet.joe.app.util.PopulaTabelas;

public final class Model extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private Context context;
    private Object registroJogo;

    public Model(final Context ctx) {
        super(ctx, Constantes.NOME_DB, null, Constantes.DB_VERSION);
        this.context = ctx;

        database = super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;

        CriaTabelas criaTabelas = new CriaTabelas(context, sqLiteDatabase);
        criaTabelas.onCreate();

        PopulaTabelas populaTabelas = new PopulaTabelas(context);
        populaTabelas.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        this.database = sqLiteDatabase;

        CriaTabelas criaTabelas = new CriaTabelas(context, sqLiteDatabase);
        criaTabelas.onUpgrade(i, i2);

        PopulaTabelas populaTabelas = new PopulaTabelas(context);
        populaTabelas.onUpgrade(sqLiteDatabase, i, i2);
    }

    public void add(ContentValues data, String tabela) {
        this.database.insert(tabela, null, data);
    }

    public void delete(final String field_params, String tabela) {
        this.database.delete(tabela, field_params, null);
    }

    public Cursor loadAll(String tabela, String[] columns) {
//        Log.d("Model", "loadAll()");

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
//        Log.d("Model", "getPalavras()");
        try {
//            String tabela = Constantes.PALAVRA;
//            String[] collumns = null;
//            String where = " idModoJogo+=? ";
//            String[] parametros = new String[]{String.valueOf(idModoJogo)};
//            String groupBy = null;
//            String having = null;
//            String orderby = null;

            ArrayList<Palavra> palavras = new ArrayList<Palavra>();

//            final Cursor c = this.database.query(tabela, collumns, where, parametros, groupBy, having, orderby);

            final Cursor c = database.rawQuery("SELECT * FROM " + Constantes.PALAVRA + " WHERE idModoJogo =? ORDER BY qtdVisualizacao ASC LIMIT " + Constantes.QTD_PALAVRAS_PARTIDA,
                    new String[]{String.valueOf(idModoJogo)});

            if (c != null) {
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    Palavra palavra = new Palavra();
                    palavra.setNome(c.getString(1));
                    palavra.setAudio(c.getString(2));
                    palavra.setUso(c.getString(3));
                    palavra.setQtdVisualizacao(c.getInt(4));
                    palavra.setQtdErros(c.getInt(5));
                    palavra.setIdModoJogo(c.getInt(6));
                    palavra.setIdRegra(c.getInt(7));

                    palavras.add(palavra);
                    c.moveToNext();
                }
                c.close();
            }
            return palavras;
        } catch (SQLiteException e) {
//            Log.e("Model.getPalavras", e.toString(), e);
            return null;
        }
    }

    public String getDica(int idRegra) {
        String dica = "";
        try {
            final Cursor c = database.rawQuery("SELECT texto FROM " + Constantes.DICA_REGRA + " WHERE idRegra =?",
                    new String[]{String.valueOf(idRegra)});

            if (c != null) {
                c.moveToFirst();
                dica = c.getString(0);
                c.close();
            }
            return dica;
        } catch (SQLiteException e) {
//            Log.e("Model.getDica", e.toString(), e);
            return "";
        }
    }

    public int getIdNorma(int idRegra) {
        int idNorma = 0;
        try {
            final Cursor c = database.rawQuery("SELECT idNorma FROM " + Constantes.REGRA + " WHERE _id =?",
                    new String[]{String.valueOf(idRegra)});

            if (c != null) {
                c.moveToFirst();
                idNorma = c.getInt(0);
                c.close();
            }
            return idNorma;
        } catch (SQLiteException e) {
            Log.e("Model.getIdNorma", e.toString(), e);
            return 0;
        }
    }

    public void setPalavraVisualizada(String nomePalavra) {
        database.execSQL("UPDATE " + Constantes.PALAVRA + " SET qtdVisualizacao = qtdVisualizacao + 1 WHERE nome = '" + nomePalavra + "'");
    }

    public String getDificuldade(int idRegra) {
        String dificuldade = "";
        try {
            final Cursor c = database.rawQuery("SELECT dificuldade FROM " + Constantes.REGRA + " WHERE _id =?",
                    new String[]{String.valueOf(idRegra)});

            if (c != null) {
                c.moveToFirst();
                dificuldade = c.getString(0);
                c.close();
            }
            return dificuldade;
        } catch (SQLiteException e) {
//            Log.e("Model.getDificuldade", e.toString(), e);
            return "";
        }
    }

    public void updatePontuacao(int pontuacao) {
        database.execSQL("UPDATE " + Constantes.JOGO + " SET pontuacaoTotal = pontuacaoTotal + "+ pontuacao);
    }

    public void updateAcertos(int qtdAcertos) {
        database.execSQL("UPDATE " + Constantes.JOGO + " SET qtdAcertosTotal = qtdAcertosTotal + "+ qtdAcertos);
    }

    public void updateTempoTreino(int duracaoEmSegundos) {
        database.execSQL("UPDATE " + Constantes.JOGO + " SET tempoTotalTreino = tempoTotalTreino + "+ duracaoEmSegundos);
    }

    public Jogo getRegistroJogo() {
//        Log.d("Model", "getRegistroJogo()");
        try {
            Jogo jogo = new Jogo();

            final Cursor c = database.rawQuery("SELECT pontuacaoTotal, tempoTotalTreino, qtdAcertosTotal FROM " + Constantes.JOGO,
                    null);

            if (c != null) {
                c.moveToFirst();
                if(c.isAfterLast() == false) {
                    jogo.setPontuacaoTotal(c.getInt(0));
                    jogo.setTempoTotalTreino(c.getInt(1));
                    jogo.setQtdAcertosTotal(c.getInt(2));
                }
                c.close();
            }
            return jogo;
        } catch (SQLiteException e) {
//            Log.e("Model.getRegistroJogo", e.toString(), e);
            return null;
        }
    }
}
