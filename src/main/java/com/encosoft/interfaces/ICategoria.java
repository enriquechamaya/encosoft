/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.modelo.Categoria;
import java.util.List;

/**
 *
 * @author echamaya
 */
public interface ICategoria extends ICrud<Categoria> {
     List<Categoria> listarTodos();
}
