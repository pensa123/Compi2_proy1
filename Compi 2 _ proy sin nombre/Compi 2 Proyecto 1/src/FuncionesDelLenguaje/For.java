/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class For extends Nodo {
    
    public String st;
    
    public For(int f, int c, Nodo hijoI, Nodo hijoD, String s) {
        super(f, c, hijoI, hijoD);
        st = s;
    }
    
    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " [" + st + "]  \" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }
    
    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        
        Object o1 = hijos.get(0).ejecutar(ts, aux);
        Nodo n = hijos.get(1);
        
        if (o1 instanceof Simbolo_prim) {
            Simbolo_prim sp = (Simbolo_prim) o1;
            Vector v = new Vector();
            v.update(0, sp);
            ejecVec(v, ts, aux, n);
        } else if (o1 instanceof Vector) {
            ejecVec((Vector) o1, ts, aux, n);
        } else if (o1 instanceof Matriz) {
            ejecMat((Matriz) o1, ts, aux, n);
        } else {
            //falta comparar si son las demas estructuras sino es error alv :D
        }
        
        return null;
    }
    
    public void ejecArrSP(ArrayList<Simbolo_prim> arr, Tabla_Sim ts, Auxiliar aux, Nodo n) {
        for (Simbolo_prim sp : arr) {
            Tabla_Sim ts2 = new Tabla_Sim(ts);
            ts2.esciclo = true;
            ts2.agregar_var(st, sp);
            n.ejecutar(ts2, aux);
            if (ts2.haybreak) {
                break;
            }
        }
    }
    
    public void ejecMat(Matriz m, Tabla_Sim ts, Auxiliar aux, Nodo n) {
        this.ejecArrSP(m.arr, ts, aux, n);
    }
    
    public void ejecVec(Vector v, Tabla_Sim ts, Auxiliar aux, Nodo n) {
        this.ejecArrSP(v.arr, ts, aux, n);
    }
    
}
