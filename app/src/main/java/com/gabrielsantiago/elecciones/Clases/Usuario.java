package com.gabrielsantiago.elecciones.Clases;

public class Usuario {

    private String nif;
    private int haVotado;

    public Usuario(String nif) {
        this.nif = nif;
        this.haVotado = 0;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getHaVotado() {
        return haVotado;
    }

    public void setHaVotado(int haVotado) {
        this.haVotado = haVotado;
    }
}
