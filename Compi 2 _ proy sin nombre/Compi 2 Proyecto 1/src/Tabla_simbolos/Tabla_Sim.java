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

    public HashMap<String, Estructura> hvar = new HashMap<String, Estructura>();

    public Auxiliar aux;
    public Tabla_Sim padre;
    public ArrayList<Tabla_Sim> hijos = new ArrayList<Tabla_Sim>();

    public boolean esciclo = false, esfuncion = false, esswitch = false;

    public boolean haybreak = false, haycontinue = false, hayreturn = false;

    public boolean esFor = false;
    public Estructura estFor = null;
    public String stFor = "";
    public int nFor = 0;

    public Object ret;

    public String nombre;

    public Tabla_Sim(String nombre, Auxiliar au) {
        aux = au;
        this.nombre = nombre;
    }
    
    public Tabla_Sim(Tabla_Sim padre, String nombre, Auxiliar au) {
        au = aux;
        this.padre = padre;
        this.nombre = padre.nombre + "->" + nombre;
        padre.hijos.add(this);
    }

    public void setReturn(Object o) {
        this.hayreturn = true;
        ret = o;
        if (!esfuncion) {
            padre.setReturn(o);
        }
    }

    public void setcontinues() {
        this.haycontinue = true;
        if (!esciclo) {
            padre.setcontinues();;
        }
    }

    public void setbreaks() {
        this.haybreak = true;
        if (!(esciclo || esswitch)) {
            padre.setbreaks();
        }
    }

    public boolean seencontroReturn() {
        if (this.esfuncion) {
            return true;
        }
        return padre == null ? false : padre.seencontroReturn();
    }

    public boolean seencontroContinue() {
        if (esciclo) {
            return true;
        }
        return padre == null ? false : padre.seencontroContinue();
    }

    public boolean seencontroBreak() {
        if (esciclo || esswitch) {
            return true;
        }
        return padre == null ? false : padre.seencontroBreak();
    }

    public boolean agregar_en_el_ambito(String id, Object o) {
        Estructura e = this.hvar.get(id.toLowerCase());
        if (e != null) {
            return false;
        }

        if (o instanceof Simbolo_prim) {
            o = new Vector((Simbolo_prim) o, aux);
        }
        if (o instanceof Estructura) {
            hvar.put(id.toLowerCase(), ((Estructura) o).copear());
            return true;
        }

        return false;
    }

    public void agregar_var(String id, Object o) {
        if (o instanceof Estructura) {
            agregar_var(id, ((Estructura) o).copear());
        } else if (o instanceof Simbolo_prim) {
            agregar_var_sp(id, (Simbolo_prim) o);
        }
    }

    public boolean try_agregar_var(String id, Estructura e) {
        if (this.esFor) {
            insFor(id, e);
        }
        if (hvar.containsKey(id.toLowerCase())) {
            hvar.put(id.toLowerCase(), e);
            return true;
        }
        return padre == null ? false : padre.try_agregar_var(id, e);
    }

    public void insFor(String id, Estructura e) {
        if (id.equalsIgnoreCase(this.stFor)) {
            System.out.println("vamos bien vamos bien");
            System.out.println(estFor.getClass().getSimpleName());

            if (this.estFor instanceof Vector) {
                Vector v = (Vector) estFor;
                v.update(nFor, e);
                System.out.println(estFor.toString());
            } else if (this.estFor instanceof Lista) {
                Lista lst = (Lista) estFor;
                lst.set(e, nFor + 1);
            } else if (this.estFor instanceof Matriz) {
                Matriz mat = (Matriz) estFor;
                mat.update(nFor + 1, e);
            } else if (this.estFor instanceof Array) {
                Array arr = (Array) estFor;
                arr.update(nFor, e);
            }

        }
    }

    //TODO falta ver lo de los ambitos. 
    public void agregar_var(String id, Estructura e) {
        if (this.esFor) {
            insFor(id, e);
        }

        if (hvar.containsKey(id.toLowerCase())) {
            hvar.put(id.toLowerCase(), e);
        } else if (padre == null || !padre.try_agregar_var(id, e)) {
            hvar.put(id.toLowerCase(), e);
        }
    }

    public void agregar_var_sp(String id, Simbolo_prim sp) {
        Vector v = new Vector(aux);
        v.update(0, sp);
        agregar_var(id, v);
    }

    public boolean try_agregar_var(String id, Simbolo_prim sp, int n) {
        if (hvar.containsKey(id.toLowerCase())) {
            //hvar.put(id.toLowerCase(), e);
            Vector v = (Vector) hvar.get(id.toLowerCase());
            v.update(n - 1, sp);
            return true;
        }
        return padre == null ? false : padre.try_agregar_var(id, sp, n);
    }

    public void agregar_var(String id, Simbolo_prim sp, int n) {
        if (!try_agregar_var(id, sp, n)) {
            Vector v = new Vector(aux);
            v.update(n - 1, sp);
            hvar.put(id.toLowerCase(), v);
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
