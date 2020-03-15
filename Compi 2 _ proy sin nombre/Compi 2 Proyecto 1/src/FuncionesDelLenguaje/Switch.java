/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.util.ArrayList;
import objetos.Case;
import objetos.Default;

/**
 *
 * @author ferna
 */
public class Switch extends Nodo {

    //nodo de la exp y el nodo de Instrucciones_cuerpo :D
    public Switch(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Object o1 = hijos.get(0).ejecutar(ts, aux);

        Tabla_Sim ts2 = new Tabla_Sim(ts, "switch");
        ts2.esswitch = true;

        Simbolo_prim sp = null;
        if (o1 instanceof Simbolo_prim) {
            sp = (Simbolo_prim) o1;
        } else if (o1 instanceof Vector) {
            sp = ((Vector) o1).arr.get(0);
        } else if (o1 instanceof Matriz) {
            sp = ((Matriz) o1).arr.get(0);
        } else {
            return aux.error("En el switch se espera un simbolo primario, un vector o una matriz", fila, columna);
        }

        ArrayList<Nodo> arrn = hijos.get(1).hijos;

        boolean coincidioCase = false;
        int def = -1;
        for (int a = 0; a < arrn.size(); a++) {
            if (ts2.haybreak) {
                break;
            }
            Nodo n = arrn.get(a);
            if (n instanceof Default) {
                if (def != -1) {
                    return aux.error("Solo puede haber un default por swtich", n.fila, n.columna);
                }
                def = a;
            }
            if (coincidioCase) {
                if (n instanceof Case) {
                    continue;
                }
                n.ejecutar(ts2, aux);
            } else {
                if (!(n instanceof Case)) {
                    continue;
                }
                Object o2 = n.ejecutar(ts2, aux);
                Simbolo_prim sp2 = null;
                if (o2 instanceof Simbolo_prim) {
                    sp2 = (Simbolo_prim) o2;
                } else if (o2 instanceof Matriz) {
                    sp2 = ((Matriz) o2).arr.get(0);
                } else if (o2 instanceof Vector) {
                    sp2 = ((Vector) o2).arr.get(0);
                }
                coincidioCase = comparar(sp, sp2);
            }
        }
        if (!coincidioCase) {
            for (int a = def; a < arrn.size(); a++) {
                if (ts2.haybreak) {
                    break;
                }
                Nodo n = arrn.get(a);
                if (n instanceof Case) {
                    continue;
                }
                n.ejecutar(ts2, aux);
            }
        }

        return null;
    }

    public boolean esNumerico(Simbolo_prim sp) {
        return sp.tp == Tipos.numerico || sp.tp == Tipos.entero;
    }

    public boolean comparar(Simbolo_prim sp1, Simbolo_prim sp2) {
        if (esNumerico(sp1) && esNumerico(sp2)) {
            return Double.parseDouble(sp1.toString()) == Double.parseDouble(sp2.toString());
        }
        if (sp1.tp == sp2.tp) {
            return sp1.toString().equals(sp2.toString());
        }
        return false;
    }

}
