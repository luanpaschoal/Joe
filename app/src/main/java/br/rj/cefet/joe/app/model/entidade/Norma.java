package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class Norma {
    String titulo;
    String observacao;

    public Norma() {
    }

    public Norma(String titulo, String observacao) {
        this.titulo = titulo;
        this.observacao = observacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
