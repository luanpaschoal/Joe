package br.rj.cefet.joe.app.util;

/**
 * Created by Luan on 12/04/2014.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaTabelas extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public CriaTabelas(Context context) {
        super(context, Constantes.NOME_DB, null, Constantes.DB_VERSION);

        super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("CriandoDB", "Criando banco de dados. Versão: " + Constantes.DB_VERSION);
        this.db = db;

        criaTabelaJogo();
        criaTabelaModoJogo();
        criaTabelaNorma();
        criaTabelaRegra();
        criaTabelaDicaRegra();
        criaTabelaPalavra();

        super.close();
    }

    private void criaTabelaJogo() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS Jogo (\n" +
                    "_id INTEGER PRIMARY KEY, \n" +
                    "pontuacaoTotal INTEGER, \n" +
                    "tempoTotal INTEGER, \n" +
                    "qtdAcertosTotal INTEGER);");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela Jogo. Erro:" + e.getMessage());
        }
    }

    private void criaTabelaModoJogo() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS ModoJogo (_id INTEGER PRIMARY KEY, tipo TEXT NOT NULL);");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela ModoJogo. Erro:" + e.getMessage());
        }
    }

    private void criaTabelaNorma() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS Norma (_id INTEGER PRIMARY KEY, \n" +
                    "titulo TEXT NOT NULL, \n" +
                    "observacao TEXT);");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela Norma. Erro:" + e.getMessage());
        }
    }

    private void criaTabelaRegra() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS Regra (_id INTEGER PRIMARY KEY, \n" +
                    "palavraChave TEXT, \n" +
                    "descricao TEXT,\n" +
                    "dificuldade TEXT,\n" +
                    "pontos INTEGER,\n" +
                    "idNorma INTEGER,\n" +
                    "FOREIGN KEY(idNorma) REFERENCES Norma(_id));");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela Regra. Erro:" + e.getMessage());
        }
    }

    private void criaTabelaDicaRegra() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS DicaRegra (_id INTEGER PRIMARY KEY, \n" +
                    "texto TEXT,\n" +
                    "idRegra INTEGER,\n" +
                    "FOREIGN KEY(idRegra) REFERENCES Regra(_id));");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela DicaRegra. Erro:" + e.getMessage());
        }
    }

    private void criaTabelaPalavra() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS Palavra (" +
                    "_id INTEGER PRIMARY KEY, \n" +
                    "nome TEXT NOT NULL, \n" +
                    "audio TEXT, \n" +
                    "uso TEXT,\n" +
                    "qtdVisualizacao INTEGER,\n" +
                    "qtdErros INTEGER,\n" +
                    "idModoJogo INTEGER,\n" +
                    "idRegra INTEGER,\n" +
                    "FOREIGN KEY(idModoJogo) REFERENCES ModoJogo(_id),\n" +
                    "FOREIGN KEY(idRegra) REFERENCES Regra(_id));");
        } catch (Exception e) {
            Log.w("CriaTabelas", "Não foi possível criar a tabela Palavra. Erro:" + e.getMessage());
        }
    }

    /* Update database to latest version */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Crude update, make sure to implement a correct one when needed.

        Log.w("UpgradingDB", "Atualizando banco de dados da versão: " + oldVersion + " para a: "
                + newVersion + ", todos os dados antigos serão excluídos.");
        apagaTabelas(db);
        onCreate(db);
    }

    private void apagaTabelas(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Palavra");
        db.execSQL("DROP TABLE IF EXISTS DicaRegra");
        db.execSQL("DROP TABLE IF EXISTS Regra");
        db.execSQL("DROP TABLE IF EXISTS Norma");
        db.execSQL("DROP TABLE IF EXISTS ModoJogo");
        db.execSQL("DROP TABLE IF EXISTS Jogo");
    }
}
