package br.rj.cefet.joe.app.util;

/**
 * Created by Luan on 12/04/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import br.rj.cefet.joe.app.R;

public class PopulaTabelas {

    private final Context fContext;

    public PopulaTabelas(Context context) {
        fContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        //Get xml resource file
        Resources res = fContext.getResources();

        //Open xml file
        XmlResourceParser _xml = res.getXml(R.xml.popula_base);
        if (_xml != null) {
            try {
                int eventType = _xml.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if ((eventType == XmlPullParser.START_TAG)) {
                        String tagXml = _xml.getName();

                        if (tagXml.equals(Constantes.JOGO)) {
                            insereJogo(_xml, db);
                        } else if (tagXml.equals(Constantes.MODO_JOGO)) {
                            insereModoJogo(_xml, db);
                        } else if (tagXml.equals(Constantes.NORMA)) {
                            insereNorma(_xml, db);
                        } else if (tagXml.equals(Constantes.REGRA)) {
                            insereRegra(_xml, db);
                        } else if (tagXml.equals(Constantes.DICA_REGRA)) {
                            insereDicaRegra(_xml, db);
                        } else if (tagXml.equals(Constantes.PALAVRA)) {
                            inserePalavra(_xml, db);
                        }
                    }
                    eventType = _xml.next();
                }
            }
            //Catch errors
            catch (XmlPullParserException e) {
                Log.e("XmlPullParserException", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("IOException", e.getMessage(), e);
            } finally {
                //Close the xml file
                _xml.close();
            }
        }
//        super.close();
    }

    /* Update database to latest version */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private void insereJogo(XmlResourceParser _xml, SQLiteDatabase db) {
        String _pontuacaoTotal = _xml.getAttributeValue(null, "pontuacaoTotal");
        String _qtdAcertosTotal = _xml.getAttributeValue(null, "qtdAcertosTotal");
        String _tempoTotalTreino = _xml.getAttributeValue(null, "tempoTotalTreino");

        ContentValues _Values = new ContentValues();
        _Values.put("pontuacaoTotal", _pontuacaoTotal);
        _Values.put("qtdAcertosTotal", _qtdAcertosTotal);
        _Values.put("tempoTotalTreino", _tempoTotalTreino);

        db.insert(Constantes.JOGO, null, _Values);
    }

    private void insereModoJogo(XmlResourceParser _xml, SQLiteDatabase db) {
        String _tipo = _xml.getAttributeValue(null, "tipo");

        ContentValues _Values = new ContentValues();
        _Values.put("tipo", _tipo);

        db.insert(Constantes.MODO_JOGO, null, _Values);
    }

    private void insereNorma(XmlResourceParser _xml, SQLiteDatabase db) {
        String _titulo = _xml.getAttributeValue(null, "titulo");
        String _observacao = _xml.getAttributeValue(null, "observacao");

        ContentValues _Values = new ContentValues();
        _Values.put("titulo", _titulo);
        _Values.put("observacao", _observacao);

        db.insert(Constantes.NORMA, null, _Values);
    }

    private void insereRegra(XmlResourceParser _xml, SQLiteDatabase db) {
        String _palavraChave = _xml.getAttributeValue(null, "palavraChave");
        String _descricao = _xml.getAttributeValue(null, "descricao");
        String _dificuldade = _xml.getAttributeValue(null, "dificuldade");
        String _idNorma = _xml.getAttributeValue(null, "idNorma");

        ContentValues _Values = new ContentValues();
        _Values.put("palavraChave", _palavraChave);
        _Values.put("descricao", _descricao);
        _Values.put("dificuldade", _dificuldade);
        _Values.put("idNorma", _idNorma);

        long insert = db.insert(Constantes.REGRA, null, _Values);
    }

    private void insereDicaRegra(XmlResourceParser _xml, SQLiteDatabase db) {
        String _texto = _xml.getAttributeValue(null, "texto");
        String _idRegra = _xml.getAttributeValue(null, "idRegra");

        ContentValues _Values = new ContentValues();
        _Values.put("texto", _texto);
        _Values.put("idRegra", _idRegra);

        db.insert(Constantes.DICA_REGRA, null, _Values);
    }

    private void inserePalavra(XmlResourceParser _xml, SQLiteDatabase db) {
        String _nome = _xml.getAttributeValue(null, "nome");
        String _audio = _xml.getAttributeValue(null, "audio");
        String _uso = _xml.getAttributeValue(null, "uso");
        String _qtdVisualizacao = _xml.getAttributeValue(null, "qtdVisualizacao");
        String _idModoJogo = _xml.getAttributeValue(null, "idModoJogo");
        String _idRegra = _xml.getAttributeValue(null, "idRegra");

        ContentValues _Values = new ContentValues();
        _Values.put("nome", _nome);
        _Values.put("audio", _audio);
        _Values.put("uso", _uso);
        _Values.put("qtdVisualizacao", _qtdVisualizacao);
        _Values.put("idModoJogo", _idModoJogo);
        _Values.put("idRegra", _idRegra);

        long insert = db.insert(Constantes.PALAVRA, null, _Values);
    }
}
