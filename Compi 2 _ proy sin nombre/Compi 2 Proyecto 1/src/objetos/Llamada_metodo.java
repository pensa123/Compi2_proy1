/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import java.util.ArrayList;

import FuncionesDelLenguaje.Funciones_nativas;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class Llamada_metodo extends Nodo {

    String nombre = "";
    Funciones_nativas f;
    ArrayList<String> nats = new ArrayList<>();

    public Llamada_metodo(int f, int c, ArrayList<Nodo> hj, String s) {
        super(f, c, hj);
        nombre = s;
        nats.add("print");
        nats.add("c");
        nats.add("matrix");
        nats.add("typeof");
        nats.add("length");
        nats.add("nrow");
        nats.add("ncol");
        this.f = new Funciones_nativas(f, c);
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + nombre + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        int auxn = nats.indexOf(nombre.toLowerCase());

        if (auxn != -1) {
            return f.selFunc(ts, aux, hijos, nombre);
        }

        return null;
    }
}
