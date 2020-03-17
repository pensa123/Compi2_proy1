/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import java.util.ArrayList;

import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;

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

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        for (Nodo n : hijos) {
            if (n instanceof e_e) {
                return aux.error("la forma identificador = expresion solo se puede usar en declaracion de funciones. ", n.fila, n.columna);
            }
        }

        int auxn = aux.nats.indexOf(nombre.toLowerCase());

        if (auxn != -1) {
            return aux.f.selFunc(ts, aux, hijos, nombre, fila, columna);
        }

        ArrayList<Object> arro = new ArrayList<>();

        for (Nodo n : hijos) {
            Object oaux = n.ejecutar(ts, aux);
            if (oaux == null) {
                return aux.error("Parametro vacio en", fila, columna);
            }
            arro.add(oaux);
        }

        if (aux.hayfun(nombre)) {
            return aux.ejecFun(nombre, arro, fila, columna, ts);
        }

        return aux.error(nombre + " funcion no declarada. ", fila, columna);
    }
}
