package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Palavra {
    private String nome;
    private String audio;
    private String uso;
    private int qtdVisualizacao;
    private int qtdErros;
    private int idModoJogo;
    private int idRegra;

    public Palavra(String nome, String audio, String uso, int qtdVisualizacao, int qtdErros) {
        this.nome = nome;
        this.audio = audio;
        this.uso = uso;
        this.qtdVisualizacao = qtdVisualizacao;
        this.qtdErros = qtdErros;
    }

    public Palavra() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public int getQtdVisualizacao() {
        return qtdVisualizacao;
    }

    public void setQtdVisualizacao(int qtdVisualizacao) {
        this.qtdVisualizacao = qtdVisualizacao;
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public void setQtdErros(int qtdErros) {
        this.qtdErros = qtdErros;
    }

    public int getIdModoJogo() {
        return idModoJogo;
    }

    public void setIdModoJogo(int idModoJogo) {
        this.idModoJogo = idModoJogo;
    }

    public int getIdRegra() {
        return idRegra;
    }

    public void setIdRegra(int idRegra) {
        this.idRegra = idRegra;
    }
}
