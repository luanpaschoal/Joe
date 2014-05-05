package br.rj.cefet.joe.app.util;

/**
 * Created by Luan on 12/04/2014.
 */
public final class Constantes {

    public static final String NOME_DB = "joe_db";
    public static final int DB_VERSION = 11;

    public static final String JOGO = "Jogo";
    public static final String MODO_JOGO = "ModoJogo";
    public static final String NORMA = "Norma";
    public static final String REGRA = "Regra";
    public static final String DICA_REGRA = "DicaRegra";
    public static final String PALAVRA = "Palavra";

    public static final String MODO_JOGAR = "Jogando";
    public static final String MODO_TREINAR = "Treinando";

    public static final String REGRA_FACIL = "fácil";
    public static final String REGRA_DIFICIL = "difícil";

    public static final int ID_JOGAR = 1;
    public static final int ID_TREINAR = 2;

    //tempo em Milissegundos, atualmente equivalente a 300 segundos (5 minutos)
    public static final int TEMPO_PARTIDA = 300000;
    public static final int QTD_PALAVRAS_PARTIDA = 20;

    //atualmente equivalente a 1 segundo, intervalo usado na contagem regressiva.
    public static final int TEMPO_INTERVALO = 1000;
    public static final int PONTOS_FACIL = 5;
    public static final int PONTOS_DIFICIL = 10;

    public static final int ID_HIFEN = 1;
    public static final int ID_ACENTUACAO = 2;

    public static final int INFORMACAO = 0;
    public static final int AVISO = 1;
    public static final int ERRO = 2;
}
