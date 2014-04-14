package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Jogo {
    int pontuacaoTotal;
    int tempoTotal;
    int qtdAcertosTotal;

    public Jogo() {
    }

    public Jogo(int pontuacaoTotal, int tempoTotal, int qtdAcertosTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
        this.tempoTotal = tempoTotal;
        this.qtdAcertosTotal = qtdAcertosTotal;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public int getQtdAcertosTotal() {
        return qtdAcertosTotal;
    }

    public void setQtdAcertosTotal(int qtdAcertosTotal) {
        this.qtdAcertosTotal = qtdAcertosTotal;
    }
}
