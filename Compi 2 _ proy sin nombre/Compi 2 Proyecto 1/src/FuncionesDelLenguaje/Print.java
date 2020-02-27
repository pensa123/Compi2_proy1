/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesDelLenguaje;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;

/**
 *
 * @author ferna
 */
public class Print extends Nodo {

    public Print(int f, int c, Nodo hijo) {
        super(f, c, hijo);
        //   this.tipo = Tipos.PRINT;
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        Object ob =  this.hijos.get(0).ejecutar(ts, aux);

        //hay que usar esto para comparar si es simbolo o si es una estructura o algo así :D 
        //ob instanceof Simbolo_prim
        
        
        if (ob == null) {
            System.out.println("reportar error aqui jeje :D");
            return null;
        }
        aux.st += ob.toString();
        System.out.println(ob.toString());
        return null;
    }

}
