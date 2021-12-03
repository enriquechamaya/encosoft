package com.encosoft.modelo;

public class Usuario {

    private int id;
    private int idrol;
    private int idagencia;
    private String usuario;
    private String contrasena;
    private int estado;


 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public int getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(int idagencia) {
        this.idagencia = idagencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", idrol=" + idrol + ", idagencia=" + idagencia + ", usuario=" + usuario + ", contrasena=" + contrasena + ", estado=" + estado + '}';
    }

    

}
