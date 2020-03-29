/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Paren extends Nodo {
    
    public Paren(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
    }
    
    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        if (hijos.size() == 1) {
            return hijos.get(0).ejecutar(ts, aux);
        }
        
        return aux.error("no se esperaba una lista de expresiones. ", fila, columna);
    }
    
   /* @Override
    public void dibujar(Dibujador d, String padre) {
        if (hijos.size() == 1) {
            hijos.get(0).dibujar(d, padre);
        }
    }*/
    
}
