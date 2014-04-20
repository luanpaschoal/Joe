package br.rj.cefet.joe.app.model.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class DicaRegra {
    String texto;

    Regra regra = new Regra();

    public DicaRegra() {
    }

    public DicaRegra(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
    }
}
