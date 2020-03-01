/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Var_acceso extends Nodo {

    public boolean acceso_matriz;

    public Estructura est;
    public String nest = "";

    public ArrayList<Integer> arrint = new ArrayList<>();
    public ArrayList<Boolean> arrEsAccesoDoble = new ArrayList<>();
    public boolean hayAccesoDoble = false;

    //de ambas formas en nodo(0) es el identificador :D 
    //el izquierdo siempre sera un identificador y el derecho un acceso a matriz :D 
    public Var_acceso(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
        acceso_matriz = true;
    }

    //si es de este modo el tiene el acceso a un arreglo de hijos 
    public Var_acceso(int f, int c, ArrayList<Nodo> hj) {
        super(f, c, hj);
        acceso_matriz = false;
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {

        Nodo n = hijos.get(0);

        if (n.getClass().getSimpleName().equals("Iden")) {
            Iden i = (Iden) n;
            est = ts.obtener_var(i.nombre);
            nest = i.nombre;
        } else {
            //TODO aqui podria estar una llamada a funcion pero no se si se va a implementar jejejje salu3
        }

        if (this.acceso_matriz) {

        } else {
            for (int a = 1; a < hijos.size(); a++) {
                Acceso ac = (Acceso) hijos.get(a);
                ac.ejecutar(ts, aux);

                //TODO tal vez hay que quitar numerico pero ya despues miramos
                if (ac.sp.tp == Tipos.entero || ac.sp.tp == Tipos.numerico) {
                    this.arrint.add((int) Double.parseDouble(ac.sp.valor.toString()));
                    this.arrEsAccesoDoble.add(ac.accesoDoble);
                    this.hayAccesoDoble = this.hayAccesoDoble || ac.accesoDoble;
                } else {
                    System.out.println("reportar error :D");
                }
            }
        }

        return null;
    }

}
