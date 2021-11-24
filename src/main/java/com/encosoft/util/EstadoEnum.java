package com.encosoft.util;

/**
 * @author echamaya
 */
public enum EstadoEnum {
    ACTIVO(1, "ACTIVO"), INACTIVO(0, "INACTIVO");

    private EstadoEnum(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    private int id;
    private String descripcion;

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

}
