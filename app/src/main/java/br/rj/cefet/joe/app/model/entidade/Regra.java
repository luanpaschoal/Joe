package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Regra {
    String palavraChave;
    String descricao;
    String dificuldade;
    int pontos;

    private Norma norma = new Norma();

    public Regra() {
    }

    public Regra(String palavraChave, String descricao, String dificuldade, int pontos) {
        this.palavraChave = palavraChave;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.pontos = pontos;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Norma getNorma() {
        return norma;
    }

    public void setNorma(Norma norma) {
        this.norma = norma;
    }
}
