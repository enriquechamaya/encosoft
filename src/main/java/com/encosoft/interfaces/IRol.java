/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.modelo.Rol;
import java.util.List;

/**
 *
 * @author Saul
 */
public interface IRol  extends ICrud<Rol>{
      
     List<Rol> listarPorDescripcion(String descripcion);
}
