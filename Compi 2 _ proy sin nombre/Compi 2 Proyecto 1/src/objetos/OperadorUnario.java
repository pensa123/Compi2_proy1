/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;

/**
 *
 * @author ferna
 */
public class OperadorUnario extends Nodo {

    public OperadorUnario(int f, int c, Nodo hijo, Op oo) {
        super(f, c, hijo);
        o = oo;
    }

    public enum Op {

        neg, not
    };

    public Op o;

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + o + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Object o1 = hijos.get(0).ejecutar(ts, aux);

        if (o1 instanceof Simbolo_prim) {
            return ejec_sp((Simbolo_prim) o1, aux);
        } else if (o1 instanceof Vector) {
            return ejec_vc((Vector) o1, aux);
        }
        //TODO falta hacer el de la matriz :D
        return null;
    }

    public Vector ejec_vc(Vector v1, Auxiliar aux) {

        Vector v2 = new Vector(aux);
        int a = 0;
        for (Simbolo_prim s : v1.arr) {
            v2.update(a++, ejec_sp(s, aux));
        }

        return v2;
    }

    public Simbolo_prim ejec_sp(Simbolo_prim sp, Auxiliar aux) {
        sp = aux.copiar_sp(sp);
        if (o == Op.neg) {
            if (sp.tp == Tipos.entero || sp.tp == Tipos.numerico) {
                sp.valor = -1 * (Double.parseDouble(sp.valor + ""));
            } else {
                aux.error("Error de tipos, se esperaba un numerico ", fila, columna);
            }
        } else {
            if (sp.tp == Tipos.booleano) {
                sp.valor = !(boolean) sp.valor;
            } else {
                aux.error("Error de tipos, se esperaba un booleano ", fila, columna);
            }
        }

        return sp;
    }

}
