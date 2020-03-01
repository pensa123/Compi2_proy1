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

        if (n < arr.size()) {
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
}
