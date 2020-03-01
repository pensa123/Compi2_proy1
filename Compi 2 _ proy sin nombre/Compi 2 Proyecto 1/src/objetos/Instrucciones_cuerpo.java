/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Instrucciones_cuerpo extends Nodo {

    public Instrucciones_cuerpo(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        for (Nodo n : hijos) {
            n.ejecutar(ts, aux);
        }
        return null;
    }

}
