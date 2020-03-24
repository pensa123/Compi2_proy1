/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Funcion;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Asignacion_funcion extends Nodo {

    String nombre = "";
    boolean mal = false;

    public Asignacion_funcion(int f, int c, ArrayList<Nodo> hj, String st) {
        super(f, c, hj);
        nombre = st;
    }

    public Asignacion_funcion(int f, int c, ArrayList<Nodo> hj, String st, boolean tamal) {
        super(f, c, hj);
        nombre = st;
        mal = tamal;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        if (mal) {
            return;
        }
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + nombre + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        if (mal) {
            return null;
        }
        ArrayList<Nodo> vars = new ArrayList<Nodo>();
        for (int a = 0; a < hijos.size() - 1; a++) {
            vars.add(hijos.get(a));
        }

        for (Nodo n : vars) {
            if (n instanceof Iden) {
                continue;
            }
            if (n instanceof e_e) {
                if (n.hijos.get(0) instanceof Iden) {
                    continue;
                }
            }
            if (n instanceof Default) {
                return aux.error("Al declarar una funcion no se puede poner default, este es solo para llamadas de funciones. ", n.fila, n.columna);
            } else {
                return aux.error("No se puede poner una operacion como parametro. ", n.fila, n.columna);
            }

        }

        Nodo ic = hijos.get(hijos.size() - 1);
        Funcion f = new Funcion(nombre, ic, vars);

        if (aux.addFunc(nombre, f)) {
            return null;
        }
        aux.error("Ya existe una funcion con el nombre " + nombre, fila, columna);
        return null;
    }
}
