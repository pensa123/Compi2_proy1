/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
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
        Object o1 = hijos.get(0).ejecutar(ts, aux);
        Object o2 = aux.ayuda_bool(o1);
        if (!(o2 instanceof Boolean)) {
            System.out.println("error, esto deberia se un booleano");
        }

        if ((boolean) o2) {
            hijos.get(1).ejecutar(ts, aux);
        } else if(hijos.size() == 3){
            hijos.get(2).ejecutar(ts, aux);
        }

        return null;
    }

}
