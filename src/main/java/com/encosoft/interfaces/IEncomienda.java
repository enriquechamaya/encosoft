/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.dtos.ListarEncomiendasDTO;
import com.encosoft.modelo.Encomienda;
import java.util.List;

/**
 *
 * @author echamaya
 */
public interface IEncomienda extends ICrud<Encomienda>{
      
    List<ListarEncomiendasDTO> listarEncomiendasPersonalizado(String cliente, String receptor, String idEncomienda);
}
