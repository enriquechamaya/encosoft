package com.encosoft.dtos;

/**
 * @author echamaya
 */
public class ListarEncomiendasDTO {

    private int id;
    private String agencia;
    private String cliente;
    private String receptor;
    private String fecha;
    private String precio;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ListarEncomiendasDTO{" + "id=" + id + ", agencia=" + agencia + ", cliente=" + cliente + ", receptor=" + receptor + ", fecha=" + fecha + ", precio=" + precio + ", estado=" + estado + '}';
    }
}
