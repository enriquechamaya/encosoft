/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.modelo.Productos;

import java.util.List;

public interface IProductos extends ICrud<Productos> {

    List<Productos> listarPorDescripcion(String descripcion);
}
