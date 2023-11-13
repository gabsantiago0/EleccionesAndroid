package com.gabrielsantiago.elecciones.Clases;

public class Candidato {
    private String nombre;
    private int idDrawable;
    private String partido;

    public Candidato(String nombre, int idDrawable, String partido) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.partido = partido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }
}
