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
public class Asignacion_funcion extends Nodo {

    String nombre = "";

    public Asignacion_funcion(int f, int c, ArrayList<Nodo> hj, String st) {
        super(f, c, hj);
        nombre = st;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + nombre + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

}
