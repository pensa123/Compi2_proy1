/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import static java.lang.System.currentTimeMillis;

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
          /*  if (aux.aiuda == 30000) {
                System.gc();
                aux.aiuda = 0;
                
                aux.aiuda2++;
            }
            System.out.println("hola");*/
            Object o = null;
            aux.aiuda++;
            //System.out.println("hola que tal como estas " + aux.aiuda++);
            if (hijos.size() == 1) {
                if (hijos.get(0) != null) {
                    o = hijos.get(0).ejecutar(ts, aux);
                    if (o == null) {
                        return aux.error("Return devolvio null", fila, columna);
                    }
                }
            }
            ts.setReturn(o);
            return null;
        }
        return aux.error("No se esperaba un return ya que no se esta dentro de una funcion. ", fila, columna);
    }

}
