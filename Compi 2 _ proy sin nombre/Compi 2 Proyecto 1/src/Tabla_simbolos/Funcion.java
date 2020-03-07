/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.Nodo;
import java.util.ArrayList;
import objetos.Iden;

/**
 *
 * @author ferna
 */
public class Funcion {

    public String nombre;
    public Nodo inst_cuerpo = null;
    public ArrayList<Nodo> vars = new ArrayList<>();
    public int nvars = 0;

    public Funcion(String nm, Nodo ic, ArrayList<Nodo> arr) {
        nombre = nm;
        inst_cuerpo = ic;
        vars = arr;
        nvars = arr.size();
    }

    public Object ejecutar(Tabla_Sim ts, Auxiliar aux, ArrayList<Object> arro, int fila, int columna, Tabla_Sim estoy) {
        //TODOS agregar parametros ahora solo vamos a hacer funciones sin parametros :D
        ts.esfuncion = true;
        if (nvars != arro.size()) {
            return aux.error("No coinciden la candidad de parametros en funcion " + nombre, fila, columna);
        }

        for (int a = 0; a < nvars; a++) {
            //todo falta esto ->  if(arro.get(a) instanceof defualt)

            String st = ((Iden) vars.get(a)).nombre;
            ts.agregar_var(st, arro.get(a));

        }

        inst_cuerpo.ejecutar(ts, aux);
        if (ts.hayreturn) {
            return ts.ret;
        }
        //se debe de verificar si se debe de retornar algo :D
        return null;
    }
}
