package br.rj.cefet.joe.app.negocio.entidade;

/**
 * Created by Luan on 05/04/2014.
 */
public class ModoJogo {
    String tipo;

    public ModoJogo() {
    }

    public ModoJogo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
