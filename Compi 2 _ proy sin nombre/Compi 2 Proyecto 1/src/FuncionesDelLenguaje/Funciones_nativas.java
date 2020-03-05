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
import Tabla_simbolos.Matriz;
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

    int fila, columna;

    public Object selFunc(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos, String func, int f, int c) {
        fila = f;
        columna = c;
        switch (func.toLowerCase()) {
            case "print":
                return Print(ts, aux, hijos);
            case "c":
                return c(ts, aux, hijos);
            case "matrix":
                return matrix(ts, aux, hijos);
            case "typeof":
                return typeof(ts, aux, hijos);
            case "length":
                return length(ts, aux, hijos);
            case "ncol":
                return nalgo(ts, aux, hijos, true);
            case "nrow":
                return nalgo(ts, aux, hijos, false);
            case "stringlength":
                return stringlength(ts, aux, hijos);
            case "remove":
                return remove(ts, aux, hijos);
        }
        return null;
    }

    public Object remove(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        if (hijos.size() != 2) {
            return aux.error("En la funcion remove se requieren dos parametros de tipo cadena ", fila, columna);
        }
        Object o1 = hijos.get(0).ejecutar(ts, aux), o2 = hijos.get(1).ejecutar(ts, aux);
        Simbolo_prim sp1 = aux.dev_sp(o1), sp2 = aux.dev_sp(o2);
        if (sp1 == null || sp2 == null) {
            return aux.error("En la funcion remove se requieren dos parametros de tipo cadena ", fila, columna);
        }
        if (sp1.tp != Tipos.cadena || sp2.tp != Tipos.cadena) {
            return aux.error("En la funcion remove se requieren dos parametros de tipo cadena ", fila, columna);
        }
        String s1 = sp1.valor.toString(), s2 = sp2.valor.toString();
        System.out.println(s1.replace(s2, ""));
        return new Simbolo_prim(Tipos.cadena, s1.replace(s2, ""));
    }

    public Object stringlength(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        if (hijos.size() != 1) {
            return aux.error("En la funcion stringlength() se esparaba solo un parametro. ", fila, columna);
        }
        Object o = hijos.get(0).ejecutar(ts, aux);
        Simbolo_prim sp = aux.dev_sp(o);
        if (sp == null) {
            return aux.error("stringlength espera un vector de una poscion de tipo cadena. ", fila, columna);
        }
        if (sp.tp != Tipos.cadena) {
            return aux.error("stringlength espera un vector de una poscion de tipo cadena. ", fila, columna);
        }

        return new Simbolo_prim(Tipos.entero, sp.valor.toString().length());
    }

    public Object nalgo(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos, boolean ncol) {
        if (hijos.size() != 1) {
            return aux.error("en la funcion " + (ncol ? "ncol" : "nrow") + " se esperaba solo un argumento. ", fila, columna);
        }
        Object o = hijos.get(0).ejecutar(ts, aux);
        if (o instanceof Matriz) {
            Matriz m = (Matriz) o;
            return new Simbolo_prim(Tipos.numerico, (ncol ? m.columnas : m.filas));
        }
        return null;
    }

    public Object length(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        if (hijos.size() != 1) {
            return aux.error("Se esperaba solo un parametro en la funcion length()", fila, columna);
        }
        Simbolo_prim sp = null;
        Object o = hijos.get(0).ejecutar(ts, aux);
        if (o instanceof Simbolo_prim) {
            sp = new Simbolo_prim(Tipos.entero, 1);
        } else if (o instanceof Vector) {
            sp = new Simbolo_prim(Tipos.entero, ((Vector) o).tamanio);
        } else if (o instanceof Matriz) {
            sp = new Simbolo_prim(Tipos.entero, ((Matriz) o).tamanio);
        }//TODO falta agregar lista y arreglos :D
        return sp;
    }

    public Object typeof(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        if (hijos.size() != 1) {
            return aux.error("En la funcion typeof se espera solo un parametro.", fila, columna);
        }
        Simbolo_prim sp = null;
        Object o = hijos.get(0).ejecutar(ts, aux);
        if (o instanceof Simbolo_prim) {
            sp = new Simbolo_prim(Tipos.cadena, ((Simbolo_prim) o).tp.toString());
        } else if (o instanceof Matriz) {
            sp = new Simbolo_prim(Tipos.cadena, ((Matriz) o).tp.toString());
        } else if (o instanceof Vector) {
            sp = new Simbolo_prim(Tipos.cadena, ((Vector) o).tp.toString());
        } else if (o instanceof Estructura) {
            sp = new Simbolo_prim(Tipos.cadena, o.getClass().getSimpleName());
        }
        return sp;
    }

    public Simbolo_prim esSim(Object o) {
        if (o instanceof Simbolo_prim) {
            return (Simbolo_prim) o;
        } else if (o instanceof Vector) {
            Vector v = (Vector) o;
            if (v.arr.size() == 1) {
                return v.arr.get(0);
            }
        }
        return null;
    }

    public Object matrix(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> hijos) {
        if (hijos.size() != 3) {
            return aux.error("En la funcion matrix(data, nrow, ncolumn) se esperan 3 parametros", fila, columna);
        }
        Object o1 = hijos.get(0).ejecutar(ts, aux),
                o2 = hijos.get(1).ejecutar(ts, aux),
                o3 = hijos.get(2).ejecutar(ts, aux);

        Vector v;
        if (o1 instanceof Vector) {
            v = (Vector) o1;
        } else if (o1 instanceof Simbolo_prim) {
            Simbolo_prim sp = (Simbolo_prim) o1;
            v = new Vector();
            v.agregar(sp);
        } else {
            return aux.error("data en la funcion matrix debe de ser un vector ", fila, columna);
        }
        Simbolo_prim s1 = esSim(o2), s2 = esSim(o3);
        if (s1 == null || s2 == null) {
            return aux.error("row y column de funcion matrix deben de ser enteros. ", fila, columna);
        }
        if (aux.esEntero(s1) && aux.esEntero(s2)) {
            Matriz m = new Matriz();
            m.set(v.arr, (int) Double.parseDouble(s1.valor + ""), (int) Double.parseDouble(s2.valor + ""));
            return m;
        }
        return aux.error("row y column de funcion matrix deben de ser enteros. ", fila, columna);
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
        if (hijos.size() == 0) {
            return aux.error("En la funcion c se espera uno o mas parametros", fila, columna);
        }
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

    public Object Print(Tabla_Sim ts, Auxiliar aux, ArrayList<Nodo> arr) {
        if (arr.size() != 1) {
            return aux.error("En la funcion print() se espera solo un parametro.", fila, columna);
        }
        Nodo n = arr.get(0);
        Object ob = n.ejecutar(ts, aux);
        if (ob == null) {
            if (n instanceof Iden) {
                aux.error(" identificador " + ((Iden) n).nombre + " no encontrado", n.fila, n.columna);
            } else {
                aux.error(n.getClass().getSimpleName() + " devolvio null", n.fila, n.columna);
                System.out.println(n.getClass().getName());
            }
            return null;
        }
        aux.agregar(ob.toString());
        return null;
    }
}
