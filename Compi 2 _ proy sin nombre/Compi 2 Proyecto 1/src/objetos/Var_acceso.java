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
import Tabla_simbolos.Lista;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Var_acceso extends Nodo {

    public boolean acceso_matriz;

    public Estructura est;
    public String nest = "";

    public boolean n2enadelanteSon1 = true;
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
            if (!(est instanceof Matriz)) {
                return aux.error("Para este accecso es necesaria una matriz ", fila, columna);
            }
            Matriz mat = (Matriz) est;
            AccesoMatriz am = (AccesoMatriz) hijos.get(1);
            //1 = e,e  2 = e,   3 = ,e 

            System.out.println(am.forma);
            Simbolo_prim s1 = aux.dev_sp(am.hijos.get(0).ejecutar(ts, aux));
            if (am.forma == 1) {
                Simbolo_prim s2 = aux.dev_sp(am.hijos.get(1).ejecutar(ts, aux));
                if (s1 == null || s2 == null) {
                    return aux.error("En matriz[n,n] se esperan dos enteros", am.fila, am.columna);
                }
                if (!(aux.esEntero(s1) && aux.esEntero(s2))) {
                    return aux.error("En matriz[n,n] se esperan dos enteros", am.fila, am.columna);
                }
                return mat.obtener((int) Double.parseDouble(s1.valor + ""), (int) Double.parseDouble(s2.valor + ""));
            } else if (am.forma == 2) {

            } else if (am.forma == 3) {

            }
            return null;
        } else {
            for (int a = 1; a < hijos.size(); a++) {
                Acceso ac = (Acceso) hijos.get(a);
                ac.ejecutar(ts, aux);

                //TODO tal vez hay que quitar numerico pero ya despues miramos
                if (ac.sp != null) {
                    if (ac.sp.tp == Tipos.entero || ac.sp.tp == Tipos.numerico) {
                        int auxint = (int) Double.parseDouble(ac.sp.valor.toString());
                        this.arrint.add(auxint);
                        this.arrEsAccesoDoble.add(ac.accesoDoble);
                        this.hayAccesoDoble = this.hayAccesoDoble || ac.accesoDoble;

                        if (auxint != 1 && a > 1) {
                            this.n2enadelanteSon1 = false;
                        }

                    } else {
                        return aux.error("Se esperaba un entero en [] ", ac.fila, ac.columna);
                    }
                } else {
                    return aux.error("Se espera que en [] vengan vectores de una posicion o enteros", ac.fila, ac.columna);
                }
            }
        }
        if (est instanceof Lista) {
            //TODOS hay que agregar el if instanceof array :D
        } else if (this.n2enadelanteSon1) {
            if (est instanceof Vector) {
                return ((Vector) est).obtener(arrint.get(0));
            } else if (est instanceof Matriz) {
                return ((Matriz) est).obtener(arrint.get(0));
            }
        } else {
            return aux.error("Valor fuera de rango. ", fila, columna);
        }
        return null;
    }

}
