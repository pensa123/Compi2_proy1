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
public class Retorno extends Nodo {

    public Retorno(int f, int c, Nodo hijo) {
        super(f, c, hijo);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        if (ts.seencontroReturn()) {
            Object o = hijos.get(0).ejecutar(ts, aux);
            if (o == null) {
                return aux.error("Return devolvio null", fila, columna);
            }
            ts.setReturn(o);
            System.out.println("si hay return");
            return null;
        }
        return aux.error("No se esperaba un return ya que no se esta dentro de una funcion. ", fila, columna);
    }

}
