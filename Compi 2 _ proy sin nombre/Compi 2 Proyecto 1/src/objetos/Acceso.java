/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;

/**
 *
 * @author ferna
 */
public class Acceso extends Nodo {

    public boolean accesoDoble;
    public Simbolo_prim sp;

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

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Object o = hijos.get(0).ejecutar(ts, aux);
        sp = aux.dev_sp(o);
        return null;
    }
}
