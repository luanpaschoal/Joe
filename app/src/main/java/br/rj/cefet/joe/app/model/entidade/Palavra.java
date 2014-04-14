package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Palavra {
    String nome;
    String audio;
    String dificuldade;
    String uso;
    int qtdVisualizacao;
    int qtdErros;

    private ModoJogo modoJogo = new ModoJogo();
    private Regra regra = new Regra();
    private Mensagem mensagem = new Mensagem();

    public Palavra(String nome, String audio, String dificuldade, String uso, int qtdVisualizacao, int qtdErros) {
        this.nome = nome;
        this.audio = audio;
        this.dificuldade = dificuldade;
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

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
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

    public ModoJogo getModoJogo() {
        return modoJogo;
    }

    public void setModoJogo(ModoJogo modoJogo) {
        this.modoJogo = modoJogo;
    }

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
