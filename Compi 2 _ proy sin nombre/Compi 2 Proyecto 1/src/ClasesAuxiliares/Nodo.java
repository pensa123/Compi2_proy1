/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public abstract class Nodo {

    public ArrayList<Nodo> hijos = new ArrayList<Nodo>();
    public int fila;
    public int columna;
    public boolean tieneHijos;

    public void saludo() {
        System.out.println("hola");
    }

    public Nodo(int f, int c) {
        fila = f;
        columna = c;
    }

    public Nodo(int f, int c, Nodo hijo) {
        fila = f;
        columna = c;
        hijos = new ArrayList<Nodo>();
        hijos.add(hijo);
        this.tieneHijos = true;
    }

    public Nodo(int f, int c, Nodo hijoI, Nodo hijoD) {
        fila = f;
        columna = c;
        hijos = new ArrayList<Nodo>();
        hijos.add(hijoI);
        hijos.add(hijoD);
        this.tieneHijos = true;
    }

    public Nodo(int f, int c, ArrayList<Nodo> hj) {
        fila = f;
        columna = c;
        hijos = hj;
        this.tieneHijos = true;
    }

    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        System.out.println("TODO funcion ejecutar en " + this.getClass().getName() + "  no realizada aun");
        return null;
    }

    public void dibujar(Dibujador d, String padre) {
        //d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " " + fila  + " " + columna + "\" ]; \n";
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + "\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + ";\n";
        dibHijos(d);
    }

    public void dibHijos(Dibujador d) {
        if (tieneHijos && hijos != null) {
            for (Nodo n : hijos) {
                if (n == null) {
                    continue;
                }
                n.dibujar(d, this.hashCode() + "");
            }
        }
    }

}
