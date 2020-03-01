/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ferna
 */
public class Tabla_Sim {

    HashMap<String, Estructura> hvar = new HashMap<String, Estructura>();

    public Tabla_Sim padre;
    public ArrayList<Tabla_Sim> hijos = new ArrayList<Tabla_Sim>();

    public Tabla_Sim() {

    }

    public Tabla_Sim(Tabla_Sim padre) {
        this.padre = padre;
        padre.hijos.add(this);
    }

    //TODO falta agregar las demas asignaciones a variable pero estas se haran cuando se tenga la funcion c o array o list o alguna de esas jejej salu3
    public void agregar_var(String id, Object o) {
        if (o instanceof Estructura) {
            agregar_var(id, (Estructura) o);
        } else if (o instanceof Simbolo_prim) {
            agregar_var(id, (Simbolo_prim) o);
        }
    }

    //TODO falta ver lo de los ambitos. 
    public void agregar_var(String id, Estructura e) {
        hvar.put(id, e);
    }

    public void agregar_var(String id, Simbolo_prim sp) {
        Vector v = new Vector(sp.tp);
        v.update(0, sp);
        agregar_var(id , v);
    }

    public void agregar_var(String id, Simbolo_prim sp, int n) {
        n -= 1;
        Estructura e = hvar.get(id);
        if (e == null) {
            Vector v = new Vector(sp.tp);
            v.update(n, sp);
            hvar.put(id, v);
            // System.out.println("esta vacia jeje");
        } else if (e.getClass().getSimpleName().equals("Vector")) {
            Vector v = (Vector) e;
            v.update(n, sp);
            if (v.tp != sp.tp) {
                v.casteo(sp.tp);
            }
        }
    }

    public Estructura obtener_var(String id) {
        Estructura e = hvar.get(id);
        if (e == null && padre == null) {
            return null;
        }
        return e == null ? padre.obtener_var(id) : e;
    }

}
