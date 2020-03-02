/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.Lista;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.util.ArrayList;
import objetos.Iden;

/**
 *
 * @author ferna
 */
public class Funciones_nativas {

    public Object selFunc(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos, String func) {
        switch (func.toLowerCase()) {
            case "print":
                return Print(ts, aux, hijos.get(0));
            case "c":
                return c(ts, aux, hijos);
        }
        return null;
    }

    public int st(int n, int n2) {
        return n > n2 ? n : n2;
    }

    public int stp(int n, Tipos tp) {
        switch (tp) {
            case cadena:
                return st(n, 3);
            case numerico:
                return st(n, 1);
            case entero:
                return st(n, 1);
            case booleano:
                return st(n, 0);
        }
        return n;
    }

    public Object c(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        System.out.println("llego a la funcion c :OOOOo");
        ArrayList<Object> arro = new ArrayList<>();
        int tipo = 0;
        for (Nodo n : hijos) {
            Object o = n.ejecutar(ts, aux);
            if (o instanceof Lista) {
                tipo = st(tipo, 4);
                arro.add(o);
            } else if (o instanceof Vector) {
                Vector v2 = (Vector) o;
                tipo = stp(tipo, v2.tp);
                for (Simbolo_prim s : v2.arr) {
                    arro.add(s);
                }
            } else { //es simbolo
                arro.add(o);
                tipo = stp(tipo, ((Simbolo_prim) o).tp);
            }
        }

        if (tipo != 4) {
            Vector v = new Vector();
            for (Object oo : arro) {
                Simbolo_prim s = (Simbolo_prim) oo;
                v.agregar(s);
            }
            return v;
        }

        return null;
    }

    public Object Print(Tabla_Sim ts, Auxiliar aux, Nodo n) {

        Object ob = n.ejecutar(ts, aux);
        if (ob == null) {
            if (n instanceof Iden) {
                aux.error(" identificador " + ((Iden) n).nombre + " no encontrado", n.fila, n.columna);
            } else {
                aux.error(n.getClass().getSimpleName() + " devolvio null", n.fila, n.columna);
            }
            return null;
        }
        aux.agregar(ob.toString());
        return null;
    }
}
