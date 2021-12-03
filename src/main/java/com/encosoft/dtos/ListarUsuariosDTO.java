package com.encosoft.dtos;

import com.encosoft.modelo.Usuario;

/**
 * @author echamaya
 */
public class ListarUsuariosDTO extends Usuario {

    private String rol;
    private String agencia;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

}
