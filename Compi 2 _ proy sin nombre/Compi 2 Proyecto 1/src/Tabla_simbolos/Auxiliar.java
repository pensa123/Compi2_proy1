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

    public boolean haybreak = false, haycontinue = false, hayreturn = false;
    public Object ret;

    public Auxiliar(TextArea txt1, TextArea txt2) {
        tx = txt1;
        txterr = txt2;
    }

    public void agregar(String ss) {
        st += ss + "\n";
        tx.setText(st);
        System.out.println(ss);
    }

    public void error(String st, int fila, int columna) {
        arrErr.add(new Error(fila, columna, st));
        error += "F " + fila + " c " + columna + " " + st;
        txterr.setText(error);
        System.out.println("Error: " + st);
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

}
