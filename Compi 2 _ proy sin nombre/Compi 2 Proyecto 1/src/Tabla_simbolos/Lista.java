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
public class Lista extends Estructura {

    public boolean masDeUnNivel = false;
    public ArrayList<Object> arr = new ArrayList<>();

    public Lista(Auxiliar au) {
        super(au);
    }

    @Override
    public int size() {
        return arr.size();
    }

    public boolean set(Object o, int n) {
        n -= 1;
        if (o instanceof Simbolo_prim) {
            o = new Vector((Simbolo_prim) o, aux);
        }
        if (arr.size() > n && n >= 0) {
            arr.set(n, o);
            return true;
        }
        if (n >= arr.size()) {
            for (int a = arr.size(); a < n; a++) {
                arr.add(new Simbolo_prim(Tipos.nulo, ""));
            }
            arr.add(o);
        }

        return false;
    }

    public Object acceso1(int n) {
        n -= 1;
        if (arr.size() > n && n >= 0) {
            Lista lst = new Lista(aux);
            lst.agregar(arr.get(n));
            return lst;
        }
        return null;
    }

    public Object acceso2(int n) {
        n -= 1;
        if (arr.size() > n && n >= 0) {
            Object o = arr.get(n);
            if (o instanceof Estructura) {
                return ((Estructura) o);
                //return ((Estructura) o).copear();
            } else {
                return ((Simbolo_prim) o);
                //return ((Simbolo_prim) o).copear();
            }
        }
        return null;
    }

    @Override
    public Estructura copear() {
        Lista lst = new Lista(aux);
        for (Object o : arr) {
            if (o instanceof Estructura) {
                lst.agregar(((Estructura) o).copear());
            } else {
                lst.agregar(((Simbolo_prim) o).copear());
            }
        }
        return lst;
    }

    public void agregar(Object o) {
        if (o instanceof Lista) {
            this.masDeUnNivel = true;
        }
        if (o instanceof Simbolo_prim) {
            Vector v = new Vector(aux);
            v.agregar((Simbolo_prim) o);
            o = v;
        }
        arr.add(o);
    }

    @Override
    public String toString() {
        String st = "{";
        boolean nvez = false;
        for (Object o : arr) {
            if (nvez) {
                st += ", ";
            }
            if (o instanceof Simbolo_prim) {
                st += o.toString();
            } else if (o instanceof Vector && ((Vector) o).arr.size() == 1) {
                st += o.toString();
            } else {
                // st += o.getClass().getSimpleName();
                st += o.toString();
            }
            nvez = true;
        }

        return st + "}";
    }

}
