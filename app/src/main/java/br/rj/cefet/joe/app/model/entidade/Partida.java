package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */

//o registro de cada partida não entrará no banco, ficará no controller.
//essa classe sera excluída.
public class Partida {
    int tempoLimite = 120;
    int qtdPalavrasJogadas;
    int duracao;

    int qtdErrosHifen;
    int qtdAcertosHifen;

    int qtdErrosAcentuacao;
    int qtdAcertosAcentuacao;

    private Jogo Jogo = new Jogo();
    private ModoJogo modoJogo = new ModoJogo();

    public Partida() {
    }

    public int getTempoLimite() {
        return tempoLimite;
    }

    public void setTempoLimite(int tempoLimite) {
        this.tempoLimite = tempoLimite;
    }

    public int getQtdPalavrasJogadas() {
        return qtdPalavrasJogadas;
    }

    public void setQtdPalavrasJogadas(int qtdPalavrasJogadas) {
        this.qtdPalavrasJogadas = qtdPalavrasJogadas;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getQtdErrosHifen() {
        return qtdErrosHifen;
    }

    public void setQtdErrosHifen(int qtdErrosHifen) {
        this.qtdErrosHifen = qtdErrosHifen;
    }

    public int getQtdAcertosHifen() {
        return qtdAcertosHifen;
    }

    public void setQtdAcertosHifen(int qtdAcertosHifen) {
        this.qtdAcertosHifen = qtdAcertosHifen;
    }

    public int getQtdErrosAcentuacao() {
        return qtdErrosAcentuacao;
    }

    public void setQtdErrosAcentuacao(int qtdErrosAcentuacao) {
        this.qtdErrosAcentuacao = qtdErrosAcentuacao;
    }

    public int getQtdAcertosAcentuacao() {
        return qtdAcertosAcentuacao;
    }

    public void setQtdAcertosAcentuacao(int qtdAcertosAcentuacao) {
        this.qtdAcertosAcentuacao = qtdAcertosAcentuacao;
    }

    public Jogo getJogo() {
        return Jogo;
    }

    public void setJogo(Jogo jogo) {
        Jogo = jogo;
    }

    public ModoJogo getModoJogo() {
        return modoJogo;
    }

    public void setModoJogo(ModoJogo modoJogo) {
        this.modoJogo = modoJogo;
    }
}
