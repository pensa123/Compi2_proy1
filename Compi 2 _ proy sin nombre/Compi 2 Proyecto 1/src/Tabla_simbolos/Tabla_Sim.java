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

    public boolean esciclo = false, esfuncion = false;

    public boolean haybreak = false, haycontinue = false, hayreturn = false;
    public Object ret;

    public Tabla_Sim() {

    }

    public Tabla_Sim(Tabla_Sim padre) {
        this.padre = padre;
        padre.hijos.add(this);
    }

    public void setcontinues() {
        this.haycontinue = true;
        if (!esciclo) {
            padre.setcontinues();;
        }
    }

    public void setbreaks() {
        this.haybreak = true;
        if (!esciclo) {
            padre.setbreaks();
        }
    }

    public boolean seencontroContinue() {
        if (esciclo) {
            return true;
        }
        return padre == null ? false : padre.seencontroContinue();
    }

    public boolean seencontroBreak() {
        if (esciclo) {
            return true;
        }
        return padre == null ? false : padre.seencontroBreak();
    }

    //TODO falta agregar las demas asignaciones a variable pero estas se haran cuando se tenga la funcion c o array o list o alguna de esas jejej salu3
    public void agregar_var(String id, Object o) {
        if (o instanceof Estructura) {
            agregar_var(id, ((Estructura) o).copear());
        } else if (o instanceof Simbolo_prim) {
            agregar_var(id, (Simbolo_prim) o);
        }
    }

    public boolean try_agregar_var(String id, Estructura e) {
        if (hvar.containsKey(id.toLowerCase())) {
            hvar.put(id.toLowerCase(), e);
        }
        return padre == null ? false : padre.try_agregar_var(id, e);
    }

    //TODO falta ver lo de los ambitos. 
    public void agregar_var(String id, Estructura e) {
        if (hvar.containsKey(id.toLowerCase())) {
            hvar.put(id.toLowerCase(), e);
        }
        if (padre == null || !padre.try_agregar_var(id, e)) {
            hvar.put(id.toLowerCase(), e);
        }
    }

    public void agregar_var(String id, Simbolo_prim sp) {
        Vector v = new Vector(sp.tp);
        v.update(0, sp);
        agregar_var(id, v);
    }

    public void agregar_var(String id, Simbolo_prim sp, int n) {
        n -= 1;
        Estructura e = hvar.get(id.toLowerCase());
        if (e == null) {
            Vector v = new Vector(sp.tp);
            v.update(n, sp);
            hvar.put(id.toLowerCase(), v);
            // System.out.println("esta vacia jeje");
        } else if (e.getClass().getSimpleName().equals("Vector")) {
            Vector v = (Vector) e;
            v.update(n, sp);
        }
    }

    public Estructura obtener_var(String id) {
        Estructura e = hvar.get(id.toLowerCase());
        if (e == null && padre == null) {
            return null;
        }
        return e == null ? padre.obtener_var(id) : e;
    }

}
