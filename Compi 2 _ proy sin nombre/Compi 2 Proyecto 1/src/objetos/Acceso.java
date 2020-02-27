/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;

/**
 *
 * @author ferna
 */
public class Acceso extends Nodo {

    public boolean accesoDoble;

    public Acceso(int f, int c, Nodo hijo, boolean accDoble) {
        super(f, c, hijo);
        accesoDoble = accDoble;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " " + (accesoDoble ? "[[]]" : "[]") + "   \" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

}
