/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import java.awt.TextArea;
import java.util.ArrayList;

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

    public Auxiliar(TextArea txt1, TextArea txt2, Tabla_Sim ts) {
        tx = txt1;
        txterr = txt2;
        global = ts;
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
            if (((Vector) o).tamanio == 1) {
                sp = ((Vector) o).obtener();
            }
        }
        return sp;
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
}
