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

        Simbolo_prim sp = (Simbolo_prim) hijos.get(0).ejecutar(ts, aux);
        if (o == Op.neg) {
            if (sp.tp == Tipos.entero || sp.tp == Tipos.numerico) {
                sp.valor = -1 * (Double.parseDouble(sp.valor + ""));
            } else {
                System.out.println("TODO marcar un error de tipos aqui");
            }
        } else {
            if (sp.tp == Tipos.booleano) {
                sp.valor = !(boolean) sp.valor;
            } else {
                System.out.println("marcar un error de tipos aqui");
            }
        }

        return sp;
    }

}
