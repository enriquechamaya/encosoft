package controaldor;

import com.encosoft.controlador.ControlEncomienda;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.modelo.Encomienda;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echamaya
 */
public class ControlEncomiendaTest {

    public static void main(String[] args) {

        Encomienda e = new Encomienda();
        e.setIdagencia(1);
        e.setIdtipodocumento(1);
        e.setNumerodocumento("11111111");
        e.setReceptorapepat("ENRIQUE");
        e.setReceptorapemat("VALDIVIA");
        e.setReceptornombres("FLORES");
        e.setIdcliente(1);
        e.setFecha("2021-11-13 14:30:59");
        e.setPreciototal(753.7);

        List<DetalleEncomienda> listaDetalle = new ArrayList<>();
        DetalleEncomienda de = new DetalleEncomienda();
        de.setIdproducto(1);
        de.setDescripcion("PRODUCTOS FRAGILES");
        de.setCantidad(2);
        de.setPreciounitario(150.50);
        de.setPeso(10);

        DetalleEncomienda de2 = new DetalleEncomienda();
        de2.setIdproducto(2);
        de2.setDescripcion("PRODUCTOS BEBIBLES");
        de2.setCantidad(3);
        de2.setPreciounitario(150.90);
        de2.setPeso(5);

        listaDetalle.add(de);
        listaDetalle.add(de2);

        e.setDetalleEncomiendas(listaDetalle);

        ControlEncomienda ce = new ControlEncomienda();
        Boolean resultado = ce.insertar(e);
        if (resultado) {
            System.out.println("encomienda registrada");
        } else {
            System.out.println("error al registra encomienda");
        }
    }
}
