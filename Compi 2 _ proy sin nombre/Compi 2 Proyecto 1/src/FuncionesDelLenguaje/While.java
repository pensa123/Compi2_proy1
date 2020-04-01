/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class While extends Nodo {

    public While(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Nodo n = hijos.get(0), n1 = hijos.get(1);
        Object o1, o2;
        o1 = n.ejecutar(ts, aux);
        o2 = aux.ayuda_bool(o1);
        if (!(o2 instanceof Boolean)) {
            aux.error("Se esperaba un valor booleano", n.fila, n.columna);
        }

        while ((boolean) o2) {
            Tabla_Sim ts2 = new Tabla_Sim(ts, "While", aux);
            ts2.esciclo = true;
            n1.ejecutar(ts2, aux);
            if (ts2.haybreak || ts2.hayreturn) {
                break;
            }
            o1 = n.ejecutar(ts, aux);
            o2 = aux.ayuda_bool(o1);
            if (!(o2 instanceof Boolean)) {
                aux.error("Se esperaba un valor booleano", n.fila, n.columna);
                return null;
            }
        }

        return null;
    }
}
