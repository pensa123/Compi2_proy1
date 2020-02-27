/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparadores;

import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Ternario extends Nodo {

    public Ternario(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {

        Simbolo_prim s = (Simbolo_prim) hijos.get(0).ejecutar(ts, aux);
        if (s.tp != Tipos.booleano) {
            //TODO falta lo que dice abajo jejejeje
            System.out.println("reportar un error y devolver nulo :D");
        }
        return (boolean) s.valor ? hijos.get(1).ejecutar(ts, aux) : hijos.get(2).ejecutar(ts, aux);

    }

}
