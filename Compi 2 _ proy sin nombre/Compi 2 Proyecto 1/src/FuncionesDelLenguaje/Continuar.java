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
public class Continuar extends Nodo {

    public Continuar(int f, int c) {
        super(f, c);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        if (ts.seencontroContinue()) {
            ts.setcontinues();
        } else {
            aux.error("no se esperaba un continue", fila, columna);
        }
        return null;
    }
}
