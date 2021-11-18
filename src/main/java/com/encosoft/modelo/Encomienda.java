package com.encosoft.modelo;

import java.util.List;

public class Encomienda {

    private int id;
    private int idagencia;
    private int idtipodocumento;
    private String numerodocumento;
    private String receptorapepat;
    private String receptorapemat;
    private String receptornombres;
    private int idcliente;
    private String fecha;
    private double preciototal;
    private List<DetalleEncomienda> detalleEncomiendas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(int idagencia) {
        this.idagencia = idagencia;
    }

    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getReceptorapepat() {
        return receptorapepat;
    }

    public void setReceptorapepat(String receptorapepat) {
        this.receptorapepat = receptorapepat;
    }

    public String getReceptorapemat() {
        return receptorapemat;
    }

    public void setReceptorapemat(String receptorapemat) {
        this.receptorapemat = receptorapemat;
    }

    public String getReceptornombres() {
        return receptornombres;
    }

    public void setReceptornombres(String receptornombres) {
        this.receptornombres = receptornombres;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }

    public List<DetalleEncomienda> getDetalleEncomiendas() {
        return detalleEncomiendas;
    }

    public void setDetalleEncomiendas(List<DetalleEncomienda> detalleEncomiendas) {
        this.detalleEncomiendas = detalleEncomiendas;
    }

    @Override
    public String toString() {
        return "Encomienda{" + "id=" + id + ", idagencia=" + idagencia + ", idtipodocumento=" + idtipodocumento + ", numerodocumento=" + numerodocumento + ", receptorapepat=" + receptorapepat + ", receptorapemat=" + receptorapemat + ", receptornombres=" + receptornombres + ", idcliente=" + idcliente + ", fecha=" + fecha + ", preciototal=" + preciototal + '}';
    }

}