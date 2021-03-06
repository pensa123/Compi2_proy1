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

    boolean tienetipo = false;
    public ArrayList<Simbolo_prim> arr = new ArrayList<Simbolo_prim>();
    public int tamanio = 0;

    @Override
    public int size() {
        return arr.size();
    }

    public Vector(Auxiliar au) {
        super(au);
        tienetipo = false;
    }

    public Vector(Simbolo_prim sp, Auxiliar au) {
        super(au);
        this.agregar(sp);
    }

    public Vector(Tipos tp, Auxiliar au) {
        super(au);
        this.tp = tp;
        tienetipo = true;
    }

    public Simbolo_prim obtener() {
        return obtener(1);
    }

    public Simbolo_prim obtener(int n) {
        n -= 1;
        if (arr.size() > n && n >= 0) {
            return arr.get(n);
        }
        System.out.println((n + 1) + " indice fuera del rango.");
        return null;
    }

    @Override
    public Estructura copear() {
        Vector v2 = new Vector(aux);
        int a = 0;
        for (Simbolo_prim s : arr) {
            Simbolo_prim s1 = new Simbolo_prim(s.tp, s.valor);
            v2.update(a++, s1);
        }
        return v2;
    }

    
    public void agregar(Simbolo_prim sp) {
        update(arr.size(), sp);
    }

    public void update(int n, Estructura e) {
        if (e instanceof Vector) {
            if (e.size() == 1) {
                update(n, ((Vector) e).arr.get(0));
            }
        }
    }

    public void update(int n, Simbolo_prim sp) {
        if (sp == null) {
            return;
        }
        if (!tienetipo) {
            this.tp = sp.tp;
            tienetipo = true;
        }
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
        tamanio = arr.size();
        if (tp != sp.tp) {
            casteo(sp.tp);
        }
    }

    @Override
    public String toString() {
        if (arr.size() == 1) {
            return arr.get(0).toString();
        }

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
        System.out.println(s.toString() + "  " + de + "  ->  " + hacia);
        s.tp = hacia;
        if (hacia == Tipos.cadena) {
            return s;
        }
        if (de == Tipos.booleano) {
            s.valor = (boolean) s.valor ? 1 : 0;
        }

        if (de == Tipos.nulo) {
            s.valor = s.getDef(hacia);
        }
        return s;
    }
}
