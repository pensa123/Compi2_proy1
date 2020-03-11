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
public class Matriz extends Estructura {

    public ArrayList<Simbolo_prim> arr = new ArrayList<Simbolo_prim>();
    public int filas = 0, columnas = 0;
    public int tamanio = 0;

    public int nm = 4;
    public Tipos tp;

    @Override
    public Estructura copear() {
        Matriz m2 = new Matriz();
        m2.set(arr, filas, columnas);
        return m2;
    }

    public void update(int n, ArrayList<Simbolo_prim> arr, boolean b, Auxiliar au) {
        int n2 = b ? columnas : filas;
        int n1 = b ? filas : columnas;
        if (arr.size() == 1) {
            if (n2 != 1) {
                for (int a = 0; a < n2; a++) {
                    arr.add(au.copiar_sp(arr.get(0)));
                }
                update(n, arr, b, au);
            }
        }
        n -= 1;
        if (n1 > n && n >= 0) {
            for (int a = 0; a < n2; a++) {
                if (b) {
                    this.update(n + 1, a + 1, arr.get(a));
                } else {
                    this.update(a + 1, n + 1, arr.get(a));
                }
                //v.agregar(this.obtener(n + 1, a + 1));
            }
        }
    }

    public Vector obtener(int n, boolean fila) {
        n -= 1;
        Vector v = null;
        if (fila) { //e,
            if (filas > n && n >= 0) {
                v = new Vector();
                for (int a = 0; a < columnas; a++) {
                    v.agregar(this.obtener(n + 1, a + 1));
                }
            }
        } else {    //,e
            if (columnas > n && n >= 0) {
                v = new Vector();
                for (int a = 0; a < filas; a++) {
                    v.agregar(this.obtener(a + 1, n + 1));
                }
            }
        }
        return v;
    }

    public Simbolo_prim obtener(int i, int j) {
        i -= 1;
        j -= 1;
        return obtener(this.mapeoLexicoGrafico(i, j) + 1);
    }

    public Simbolo_prim obtener(int n) {
        n -= 1;
        if (arr.size() > n && n >= 0) {
            return arr.get(n);
        }
        System.out.println((n + 1) + " indice fuera del rango.");
        return null;
    }

    public int mapeoLexicoGrafico(int f, int c) {
        return f + filas * c;
    }

    public int grande(int n, int n2) {
        return n > n2 ? n : n2;
    }

    public void set(ArrayList<Simbolo_prim> arrsp, int f, int c) {
        int aux = 0;
        //int cont = 0, verif = 0;
        filas = f;
        columnas = c;
        tamanio = f * c;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < f; i++) {
                Simbolo_prim sp = arrsp.get(aux++);
                nm = grande(nm, sp.valor.toString().length());
                arr.add(new Simbolo_prim(sp.tp, sp.valor));
                if (aux == arrsp.size()) {
                    aux = 0;
                }
                /* verif = this.mapeoLexicoGrafico(i, j);
                 if (verif != cont) {
                 System.out.println("mal mal muy mal jeje " + verif + " " + cont);
                 }
                 cont++;*/
            }
        }
        tp = arr.get(0).tp;
    }

    @Override
    public String toString() {
        String st = "", esp = "";
        int n = nm + 2;
        for (int a = 0; a < nm; a++) {
            st += " ";
        }
        for (int a = 0; a < columnas; a++) {
            st += "[," + (a + 1) + "] ";
        }
        st += "\n";
        for (int i = 0; i < filas; i++) {
            st += "[" + (i + 1) + ",]  ";
            for (int j = 0; j < columnas; j++) {
                st += impesp(arr.get(this.mapeoLexicoGrafico(i, j)).toString(), n + 2);
            }
            st += "\n";
        }
        return st;
    }

    public String impesp(String s, int n) {

        for (int a = 0; a < n - s.length(); a++) {
            s += " ";
        }
        return s;
    }

    public void update(int i, int j, Simbolo_prim sp) {
        update(this.mapeoLexicoGrafico(i - 1, j - 1) + 1, sp);

    }

    public void update(int n, Simbolo_prim sp) {
        n -= 1;
        if (arr.size() > n && n >= 0) {
            arr.set(n, sp);
            if (tp != sp.tp) {
                casteo(sp.tp);
            }
        }

    }

    public void casteo(Tipos aCast) {
        int n = (tp.compareTo(aCast) < 0) ? parse(aCast, tp) : parse(tp, aCast);
    }

    public int parse(Tipos pasarDe, Tipos pasarA) {
        tp = pasarA; 
        System.out.println("pasar De " + pasarDe + "    a   " + pasarA);
        for (int a = 0; a < arr.size(); a++) {
            arr.set(a, parseando_ando(arr.get(a), pasarDe, pasarA));
        }
        return 1;
    }

    public Simbolo_prim parseando_ando(Simbolo_prim s, Tipos de, Tipos hacia) {
        if (s.tp == hacia) {
            return s;
        }
        System.out.println(de + "  ->  " + hacia);
        s.tp = hacia;
        if (hacia == Tipos.cadena) {
            return s;
        }
        if (de == Tipos.booleano) {
            s.valor = (boolean) s.valor ? 1 : 0;
        }
        
        if( de == Tipos.nulo){
            s.valor = s.getDef(hacia);
        }

        return s;
    }

}
