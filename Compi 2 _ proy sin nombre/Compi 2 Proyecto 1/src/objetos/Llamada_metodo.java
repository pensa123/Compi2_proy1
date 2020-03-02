/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Llamada_metodo extends Nodo {

    String nombre = "";

    public Llamada_metodo(int f, int c, ArrayList<Nodo> hj, String s) {
        super(f, c, hj);
        nombre = s;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + nombre + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

}
