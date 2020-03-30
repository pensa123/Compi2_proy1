/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum.Tipos;

/**
 *
 * @author ferna
 */
public class Simbolo_prim {

    public String id;
    public Object valor;

    public Tipos tp;

    public Simbolo_prim(Tipos tprim, Object val) {
        tp = tprim;
        valor = val;
    }

    public Object getDef(Tipos tp2) {
        switch (tp2) {
            case cadena:
                return "null";
            case booleano:
                return false;
            case entero:
                return 0;
            case numerico:
                return 0;
        }
        return "NULL";
    }

    public Simbolo_prim(Tipos tprim) {
        tp = tprim;
        valor = this.getDef(tp);
    }

    @Override
    public String toString() {
        /*if (tp == Tipos.entero) {
         return valor.toString().replace(".0", "");
         } else if (tp == Tipos.numerico) {
         return Double.parseDouble(valor + "") + "";
         } else*/
        if (tp == Tipos.nulo) {
            return "NULL";
        }
        return valor + "";
    }

    public Simbolo_prim copear() {
        return new Simbolo_prim(tp, valor);
    }
}
