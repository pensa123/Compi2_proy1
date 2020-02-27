/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class Asignacion extends Nodo {

    public Asignacion(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
    }

    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {

        Nodo n = hijos.get(0);
        Simbolo_prim sp = (Simbolo_prim) hijos.get(1).ejecutar(ts, aux);

        switch (n.getClass().getSimpleName()) {
            case "Iden":
                String s = ((Iden)n).nombre;
                ts.agregar_var(s, sp);
                break;
            default:
                System.out.println("TODO accion en ejecutar _ \"" + n.getClass().getSimpleName() + "\"");
                break;

        }
        return null;
    }

}
