/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.interfaces;

import com.encosoft.dtos.ListarDetalleEncomiendasDTO;
import com.encosoft.modelo.DetalleEncomienda;
import java.util.List;

/**
 *
 * @author echamaya
 */
public interface IDetalleEncomienda extends ICrud<DetalleEncomienda> {

    List<ListarDetalleEncomiendasDTO> listarDetalleEncomiendaPersonalizado(int idEncomienda);
}
