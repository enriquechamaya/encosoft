package controaldor;

import com.encosoft.controlador.ControlCategorias;
import com.encosoft.modelo.Categoria;

/**
 * @author echamaya
 */
public class ControlCategoriasTest {

    public static void main(String[] args) {

        Categoria c = new Categoria();
        c.setDescripcion("BEBIDAS");

        ControlCategorias cc = new ControlCategorias();
        Boolean resultado = cc.insertar(c);
        if (resultado) {
            System.out.println("insertado");
        } else {
            System.out.println("error");
        }
    }

}
