/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.util;

import java.util.List;

/**
 *
 * @author echamaya
 */
public interface ICrud<T> {

    Boolean insertar(T t);

    Boolean actualizar(T t);

    Boolean eliminar(Object id);

    T obtenerPorId(Object id);

    List<T> listar();

}
