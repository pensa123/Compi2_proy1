/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Var_acceso extends Nodo {

    public boolean acceso_matriz;
    //de ambas formas en nodo(0) es el identificador :D 

    //el izquierdo siempre sera un identificador y el derecho un acceso a matriz :D 
    public Var_acceso(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);

        acceso_matriz = true;
    }

    //si es de este modo el tiene el acceso a un arreglo de hijos 
    public Var_acceso(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);

        acceso_matriz = false;
    }

}
