/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.dtos.ListarUsuariosDTO;
import com.encosoft.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Saul
 */
public interface IUsuario extends ICrud<Usuario>{
      List<ListarUsuariosDTO> listarPorUsuario(String descripcion);
   

}
