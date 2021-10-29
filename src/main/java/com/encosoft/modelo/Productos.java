package com.encosoft.modelo;

public class Productos {

    private int id;
    private int idcategoria;
    private String descripcion;
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
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
        return "Productos{" + "id=" + id + ", idcategoria=" + idcategoria + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }

}
