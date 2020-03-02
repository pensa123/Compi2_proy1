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

/**
 *
 * @author ferna
 */
public class Iden extends Nodo {

    public String nombre;

    public boolean vdef = false;

    public Iden(int f, int c, String nom) {
        super(f, c);
        nombre = nom;
    }

    public Iden(int f, int c, String nom, Nodo hijo) {
        super(f, c, hijo);
        nombre = nom;
        vdef = hijo != null;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {

        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " --" + nombre + "-- " + "\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        if(vdef){
            this.dibHijos(d);
        }
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        return ts.obtener_var(nombre);
    }

}
