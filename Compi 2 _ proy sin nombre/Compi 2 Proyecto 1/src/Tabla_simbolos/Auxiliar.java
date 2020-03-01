/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import java.awt.TextArea;

/**
 *
 * @author ferna
 */
public class Auxiliar {
    
    public TextArea tx;
    public String st = "";
    
    public void agregar(String ss) {
        st += ss + "\n";
        tx.setText(st);
    }
    
    public void agregarError(String st, int fila, int columna) {
        
    }
    
    public Object ayuda_bool(Object o1) {
        if (o1 instanceof Vector) {
            Vector v1 = (Vector) o1;
            return ayuda_bool(v1.arr.get(0));
        } else if (o1 instanceof Simbolo_prim) {
            Simbolo_prim s1 = (Simbolo_prim) o1;
            return s1.tp == Tipos.booleano ? (boolean) s1.valor : null;
        }
        return null;
    }
    
}
