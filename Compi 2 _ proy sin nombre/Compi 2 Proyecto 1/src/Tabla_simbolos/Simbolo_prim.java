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

    public Simbolo_prim(Tipos tprim) {
        tp = tprim;
        switch (tprim) {
            case cadena:
                valor = "";
                break;
            case booleano:
                valor = false;
                break;
            case entero:
                valor = 0;
                break;
            case numerico:
                valor = 0;
                break;
        }
    }

    @Override
    public String toString() {
        if (tp == Tipos.entero) {
            return (int) Double.parseDouble(valor + "") + "";
        }
        return valor + "";
    }

    public Simbolo_prim copear() {
        return new Simbolo_prim(tp, valor);
    }
}
