/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class Case extends Nodo {
    
    public Case(int f, int c, Nodo hijo) {
        super(f, c, hijo);
    }
    
    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        return hijos.get(0).ejecutar(ts, aux);
    }
    
}
