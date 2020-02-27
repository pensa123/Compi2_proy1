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
public class AccesoMatriz extends Nodo {

    //1 = e,e  2 = e,   3 = ,e 
    public int forma;

    public AccesoMatriz(int f, int c, Nodo hijo, boolean esizq) {
        super(f, c, hijo);
        forma = esizq ? 2 : 3;
    }

    public AccesoMatriz(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
        forma = 1;
    }

    public void dibujar(Dibujador d, String padre) {

        String st = forma == 1 ? "[E,E]" : forma == 2 ? "E," : ",E";
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " " + st + " \" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

}
