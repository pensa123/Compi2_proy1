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

}
