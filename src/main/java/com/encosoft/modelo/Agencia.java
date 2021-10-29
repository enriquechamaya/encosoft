package com.encosoft.modelo;

public class Agencia {

    private int id;
    private String descripcion;
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Agencia{" + "id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }

}
