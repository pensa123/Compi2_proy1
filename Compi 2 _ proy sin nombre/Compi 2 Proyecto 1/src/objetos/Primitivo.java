/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class Primitivo extends Nodo {



    public Tipos tipo;
    public Object valor;

    public Primitivo(int f, int c, Tipos tp, Object val, boolean def) {
        super(f, c);
        tipo = tp;

        switch (tp) {
            case cadena:
                valor = "";
                break;
            case booleano:
                valor = false;
                break;
            case entero:
            case numerico:
                valor = 0;
                break;
        }
    }

    public Primitivo(int f, int c, Tipos tp, Object val) {
        super(f, c);
        tipo = tp;
        valor = val;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {

        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " " + tipo + " " + valor + "\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Simbolo_prim sp = new Simbolo_prim(tipo , valor);
        return sp;
    }

}
