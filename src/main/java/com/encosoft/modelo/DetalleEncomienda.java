package com.encosoft.modelo;

public class DetalleEncomienda {

    private int id;
    private int idencomienda;
    private int idproducto;
    private String descripcion;
    private int cantidad;
    private double preciounitario;
    private int peso;
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdencomienda() {
        return idencomienda;
    }

    public void setIdencomienda(int idencomienda) {
        this.idencomienda = idencomienda;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetalleEncomienda{" + "id=" + id + ", idencomienda=" + idencomienda + ", idproducto=" + idproducto + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", preciounitario=" + preciounitario + ", peso=" + peso + ", estado=" + estado + '}';
    }

}
