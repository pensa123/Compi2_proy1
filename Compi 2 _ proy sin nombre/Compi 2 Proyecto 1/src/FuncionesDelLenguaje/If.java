/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class If extends Nodo {

    //solo va a tener entre 2 y tres hijos
    //dos si es solo un if(algo){algo}
    //tres si es if(algo){algo)else{algo}
    //o si tiene else if :D
    public If(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Nodo n = hijos.get(0);
        Object o1 = n.ejecutar(ts, aux);
        Object o2 = aux.ayuda_bool(o1);
        if (!(o2 instanceof Boolean)) {
            aux.error("Se esperaba un valor booleano", n.fila, n.columna);
        }

        if ((boolean) o2) {
            n = hijos.get(1);
        } else if (hijos.size() == 3) {
            n = hijos.get(2);
        } else {
            return null;
        }
        Tabla_Sim ts2 = new Tabla_Sim(ts);
        n.ejecutar(ts2, aux);
        return null;
    }

}
