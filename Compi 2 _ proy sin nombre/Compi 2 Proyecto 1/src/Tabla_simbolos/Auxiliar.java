/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum.Tipos;
import FuncionesDelLenguaje.Funciones_nativas;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ferna
 */
public class Auxiliar {

    public TextArea tx;
    public TextArea txterr;
    public String st = "";
    public String error = "";
    public ArrayList<Error> arrErr = new ArrayList<>();
    public Tabla_Sim global;

    public HashMap<String, Funcion> hfun = new HashMap<String, Funcion>();

    public ArrayList<String> nats = new ArrayList<>();
    public Funciones_nativas f = new Funciones_nativas();

    public Auxiliar(TextArea txt1, TextArea txt2, Tabla_Sim ts) {
        tx = txt1;
        txterr = txt2;
        global = ts;

        nats.add("print");
        nats.add("c");
        nats.add("matrix");
        nats.add("typeof");
        nats.add("length");
        nats.add("nrow");
        nats.add("ncol");
        nats.add("remove");
        nats.add("stringlength");
        nats.add("tolowercase");
        nats.add("touppercase");
        nats.add("round");
        nats.add("trunk");
        nats.add("mean");
        nats.add("median");
        nats.add("mode");
        nats.add("list");
        nats.add("array");
    }

    public void agregar(String ss) {
        st += ss + "\n";
        tx.setText(st);
        System.out.println(ss);
    }

    public Object error(String st, int fila, int columna) {
        arrErr.add(new Error(fila, columna, st));
        error += "F " + fila + " c " + columna + " " + st + "\n";
        txterr.setText(error);
        System.out.println("Error: " + st);
        return null;
    }

    public Object ayuda_bool(Object o1) {
        if (o1 instanceof Vector) {
            Vector v1 = (Vector) o1;
            return ayuda_bool(v1.arr.get(0));
        } else if (o1 instanceof Simbolo_prim) {
            Simbolo_prim s1 = (Simbolo_prim) o1;
            return s1.tp == Tipos.booleano ? (boolean) s1.valor : null;
        }
        return null;
    }

    public Simbolo_prim copiar_sp(Simbolo_prim sp) {
        return new Simbolo_prim(sp.tp, sp.valor);
    }

    public Simbolo_prim dev_sp(Object o) {
        if (o == null) {
            return null;
        }
        Simbolo_prim sp = null;
        if (o instanceof Simbolo_prim) {
            sp = (Simbolo_prim) o;
        } else if (o instanceof Vector) {
            if (((Vector) o).arr.size() == 1) {
                sp = ((Vector) o).obtener();
            }
        }
        return this.copiar_sp(sp);
    }

    public boolean esnum(Simbolo_prim sp) {
        if (sp == null) {
            return false;
        }
        return sp.tp == Tipos.entero || sp.tp == Tipos.numerico;
    }

    public boolean esEntero(Simbolo_prim sp) {
        if (sp.tp == Tipos.entero) {
            return true;
        }
        if (sp.tp == Tipos.numerico) {
            return (Double.parseDouble(sp.valor + "") == (int) Double.parseDouble(sp.valor + ""));
        }
        return false;
    }

    public boolean addFunc(String id, Funcion f) {
        if (nats.indexOf(id.toLowerCase()) != -1) {
            return false;
        }
        if (hfun.containsKey(id.toLowerCase())) {
            return false;
        }
        hfun.put(id.toLowerCase(), f);
        return true;
    }

    public boolean hayfun(String id) {
        return hfun.get(id.toLowerCase()) != null;
    }

    public Object ejecFun(String id, ArrayList<Object> arro, int fila, int columna, Tabla_Sim estoy) {

        Funcion f = hfun.get(id.toLowerCase());

        if (f == null) {
            return null;
        }

        Tabla_Sim tabla_fun = new Tabla_Sim(global);

        return f.ejecutar(tabla_fun, this, arro, fila, columna, estoy);

    }
}
