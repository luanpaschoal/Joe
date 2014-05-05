package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Jogo {
    int pontuacaoTotal;
    int tempoTotalTreino;
    int qtdAcertosTotal;

    public Jogo() {
    }

    public Jogo(int pontuacaoTotal, int tempoTotalTreino, int qtdAcertosTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
        this.tempoTotalTreino = tempoTotalTreino;
        this.qtdAcertosTotal = qtdAcertosTotal;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public int getTempoTotalTreino() {
        return tempoTotalTreino;
    }

    public void setTempoTotalTreino(int tempoTotalTreino) {
        this.tempoTotalTreino = tempoTotalTreino;
    }

    public int getQtdAcertosTotal() {
        return qtdAcertosTotal;
    }

    public void setQtdAcertosTotal(int qtdAcertosTotal) {
        this.qtdAcertosTotal = qtdAcertosTotal;
    }
}
