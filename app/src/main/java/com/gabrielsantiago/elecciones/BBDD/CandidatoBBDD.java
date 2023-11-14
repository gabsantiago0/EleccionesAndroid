package com.gabrielsantiago.elecciones.BBDD;

public class CandidatoBBDD {

    private int codCandidato;
    private  int codPartido;
    private String nombre;
    private int votos;

    public CandidatoBBDD(int codCandidato, int codPartido, String nombre, int votos) {
        this.codCandidato = codCandidato;
        this.codPartido = codPartido;
        this.nombre = nombre;
        this.votos = votos;
    }

    public int getCodCandidato() {
        return codCandidato;
    }

    public void setCodCandidato(int codCandidato) {
        this.codCandidato = codCandidato;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public void setCodPartido(int codPartido) {
        this.codPartido = codPartido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
