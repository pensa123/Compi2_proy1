/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum.Tipos;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Vector extends Estructura {

    public Tipos tp;
    ArrayList<Simbolo_prim> arr = new ArrayList<Simbolo_prim>();

    public Vector() {
    }

    public Vector(Tipos t) {
        tp = t;
    }

    public Simbolo_prim obtener() {
        return obtener(0);
    }

    public Simbolo_prim obtener(int n) {
        return arr.size() > n ? arr.get(n) : null;
    }

    @Override
    public String imprimir() {
        String st = "";
        for (Simbolo_prim n : arr) {
            st += n.tp + ":" + n.valor + "\t";
        }
        st += "\n";
        return st;
    }

    public void update(int n, Simbolo_prim sp) {

        if (n == -1) {
            for (int a = 0; a < arr.size(); a++) {
                Simbolo_prim sn = new Simbolo_prim(sp.tp);
                sn.valor = sp.valor;
                arr.set(a, sn);
            }
        } else if (n < arr.size()) {
            arr.set(n, sp);
        } else if (n == arr.size()) {
            arr.add(sp);
        } else {
            Simbolo_prim sn;
            for (; n != arr.size();) {
                sn = new Simbolo_prim(sp.tp);
                arr.add(sn);
            }
            arr.add(sp);
            int a = 2;

        }
    }

    @Override
    public String toString() {
        String st = "[";
        boolean nvez = false;
        for (Simbolo_prim s : arr) {
            if (nvez) {
                st += ", ";
            }
            st += s.toString();
            nvez = true;
        }
        return st + "]";
    }

    public void casteo(Tipos aCast) {
        int n = (tp.compareTo(aCast) < 0) ? parse(aCast, tp) : parse(tp, aCast);
        /*System.out.println("ayuuuuuuuuuuuudaaaa");
         System.out.println("cadena - cadena " +   Tipos.cadena.compareTo(Tipos.cadena) );
         System.out.println("cadena - numerico " +   Tipos.cadena.compareTo(Tipos.numerico) );
         System.out.println("cadena - entero " +   Tipos.cadena.compareTo(Tipos.entero) );
         System.out.println("cadena - booleano " +   Tipos.cadena.compareTo(Tipos.booleano) );
         System.out.println("numerico - entero" +   Tipos.numerico.compareTo(Tipos.entero) );*/
    }

    public int parse(Tipos pasarDe, Tipos pasarA) {
        System.out.println("pasar De " + pasarDe + "    a   " + pasarA);
        for (int a = 0; a < arr.size(); a++) {
            arr.set(a, parseando_ando(arr.get(a), pasarDe, pasarA));
        }
        return 1;
    }

    public Simbolo_prim parseando_ando(Simbolo_prim s, Tipos de, Tipos hacia) {
        if(s.tp != de){
            return s; 
        }
        System.out.println(de + "  ->  " + hacia);
        s.tp = hacia;
        if(hacia == Tipos.cadena){
            return s; 
        }
        if(de == Tipos.booleano){
            s.valor = (boolean)s.valor ? 1 : 0; 
        }

        return s;
    }
}
