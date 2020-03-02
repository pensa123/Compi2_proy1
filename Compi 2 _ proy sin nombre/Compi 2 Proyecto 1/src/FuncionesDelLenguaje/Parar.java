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
public class Parar extends Nodo {

    public Parar(int f, int c) {
        super(f, c);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        if (ts.seencontroBreak()) {
            ts.setbreaks();
        }
        return null;
    }
}
