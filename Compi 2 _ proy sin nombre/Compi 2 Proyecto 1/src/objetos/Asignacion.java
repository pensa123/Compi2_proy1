/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

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
public class Asignacion extends Nodo {

    public Asignacion(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {

        Nodo n = hijos.get(0);
        Object sp = hijos.get(1).ejecutar(ts, aux);

        switch (n.getClass().getSimpleName()) {
            case "Iden":
                String s = ((Iden) n).nombre;
                ts.agregar_var(s, sp);
                break;
            case "Var_acceso":
                /*
                 que se necesita: 
                 saber el tipo de la variable, este se encuentra n[0].getClass().getSimpleName()
                 dependiendo de que tipo sea o si no esta establecido sera la forma de proceder :( 
                 se debe obtener un arreglo con los accesos que estan solicitando  
                 */
                n.ejecutar(ts, aux);
                Var_acceso vacc = (Var_acceso) n;
                Estructura e = vacc.est;
                ArrayList<Integer> arrInt = vacc.arrint;
                if (e == null || e instanceof Vector) {
                    if (vacc.hayAccesoDoble) {
                        return aux.error("en vectores no se acepta el acceso doble [[]]", fila, columna);
                    } else {
                        /* boolean todook = true;
                         if (arrInt.size() != 1) {

                         for (int a = 1; a < arrInt.size(); a++) {
                         if (arrInt.get(a) != 1) {
                         System.out.println("error, valor fuera del rango :( ");
                         todook = false;
                         break;
                         }
                         }
                         }*/

                        if (vacc.n2enadelanteSon1) {
                            if (e == null) {

                            }
                            if (sp instanceof Vector) {
                                if (((Vector) sp).tamanio == 1) {
                                    sp = ((Vector) sp).arr.get(0);
                                    //no se pone return null por que se utiliza en el if de abajo. 
                                }
                            }
                            if (sp instanceof Simbolo_prim) {
                                if (arrInt.get(0) >= 1) {
                                    ts.agregar_var(vacc.nest, (Simbolo_prim) sp, arrInt.get(0));
                                    return null;
                                } else {
                                    return aux.error("a que el indice de un vector debe ser >= 1", fila, columna);
                                }
                            }
                        }
                    }
                } else if (e instanceof Matriz) {
                    Matriz m1 = (Matriz) e;
                    if (vacc.acceso_matriz) {
                        if (vacc.forma == 1) {
                            Simbolo_prim sp1 = aux.dev_sp(sp);
                            if (sp1 == null) {
                                return aux.error("se esperaba un vector de una posicion o un simbolo ", fila, columna);
                            }
                            m1.update(vacc.arrint.get(0), vacc.arrint.get(1), sp1);
                            return null;
                        }
                        boolean modo = vacc.forma == 2;
                        if (!(sp instanceof Vector)) {
                            return aux.error("Se esperaba un vector en la forma [" + (modo ? "e," : ",e") + "]", fila, columna);
                        }
                        Vector v = (Vector) sp;
                        if ((modo ? m1.columnas : m1.filas) == v.arr.size() || v.arr.size() == 1) {
                            m1.update(vacc.arrint.get(0), v.arr, modo, aux);
                            
                            System.out.println("---------------------------");
                            System.out.println(m1.toString());
                            System.out.println("---------------------------");
                            
                            return null;
                        }
                        return aux.error("en el modo " + (modo ? "e," : ",e") + " el tamanio del vector y las " + (modo ? "Columna" : "FIlas") + " debe coincidir.", fila, columna);
                    }
                    if (vacc.n2enadelanteSon1) {
                        Simbolo_prim sp1 = aux.dev_sp(sp);
                        if (sp1 == null) {
                            return aux.error("se esperaba un vector de una posicion o un simbolo ", fila, columna);
                        }
                        m1.update(arrInt.get(0), sp1);
                        return null;
                    }
                }

                System.out.println("asignacion, falta vefificar " + e.getClass().getSimpleName());
                //TODO falta agregar para las demas (matriz, lista o arreglo); 
                break;
            default:
                System.out.println("TODO accion en la clase \"EJECUTAR\" _  en el switch  \"" + n.getClass().getSimpleName() + "\"");
                break;

        }
        return null;
    }

}
