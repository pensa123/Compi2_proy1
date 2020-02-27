/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class If   extends Nodo{

    //solo va a tener entre 2 y tres hijos
    //dos si es solo un if(algo){algo}
    //tres si es if(algo){algo)else{algo}
    //o si tiene else if :D
    
    public If(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
    }
}
