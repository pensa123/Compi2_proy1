/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import Tabla_simbolos.Array;
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
public class Asignacion extends Nodo {

    public Asignacion(int f, int c, Nodo hijoI, Nodo hijoD) {
        super(f, c, hijoI, hijoD);
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {

        Nodo n = hijos.get(0);
        Object sp = hijos.get(1).ejecutar(ts, aux);
        if (sp == null) {
            return aux.error("Se esta tratando de asignar un valor nulo. ", fila, columna);
        }
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
                Var_acceso vacc = (Var_acceso) n;

                vacc.mostrarErrores = false;
                vacc.ejecutar(ts, aux);
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
                        if (sp instanceof Simbolo_prim) {
                            Vector v = new Vector();
                            v.agregar((Simbolo_prim) sp);
                            sp = v;
                        }
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
                } else if (e instanceof Lista) {
                    return setLista((Lista) e, 0, aux, vacc, sp);
                } else if (e instanceof Array) {
                    return setArr((Array) e, aux, vacc, sp);
                }

                System.out.println("asignacion, falta verificar " + e.getClass().getSimpleName());
                //TODO falta agregar para las demas (matriz, lista o arreglo); 
                break;
            default:
                System.out.println("TODO accion en la clase \"EJECUTAR\" _  en el switch  \"" + n.getClass().getSimpleName() + "\"");
                break;

        }
        return null;
    }

    public ArrayList<Integer> copyArr(ArrayList<Integer> arri) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (Integer i : arri) {
            arr.add(i);
        }
        return arr;
    }

    public Object setArr(Array arr, Auxiliar aux, Var_acceso vac, Object ins) {
        if (arr.arrD.size() > vac.arrint.size()) {
            return aux.error("Indice fuera del rango", fila, columna);
        }
        ArrayList<Integer> arri2 = this.copyArr(vac.arrint);
        ArrayList<Integer> arriS = new ArrayList<>();
        for (int a = 0; a < arr.arrD.size(); a++) {
            arriS.add(arri2.remove(0));
            vac.arrint.remove(0);
            Nodo n = vac.arrNodo_paerrores.remove(0);
            if (vac.arrEsAccesoDoble.remove(0)) {
                return aux.error("No se puede acceder a un arreglo con acceso dobles, se requiere el simple [] ", n.fila, n.columna);
            }
            vac.ultimoAccesoDoble--;
        }
        Object o = arr.obtener(arriS);
        if (o == null) {
            return aux.error("Indice fuera de rango", fila, columna);
        }
        if (arri2.size() == 0) {
            if (ins instanceof Array) {
                return aux.error("no se pueden insertar arreglos en arreglos.", fila, columna);
            }
            arr.Update(arriS, ins);
            return o;
        }
        if (o instanceof Simbolo_prim) {
            o = new Vector((Simbolo_prim) o);
        }

        if (o instanceof Vector) {
            return this.setVec((Vector) o, 0, aux, vac, ins);
        } else if (o instanceof Lista) {
            return this.setLista((Lista) o, 0, aux, vac, ins);
        }
        return null;
    }

    public Object setVec(Vector vec, int n, Auxiliar aux, Var_acceso vac, Object ins) {
        if (!(n < vac.arrint.size())) {
            return null;
        }
        int fila = vac.arrNodo_paerrores.get(n).fila;
        int columna = vac.arrNodo_paerrores.get(n).columna;
        if (vac.ultimoAccesoDoble >= n) {
            return aux.error("No se pude obtener un acceso doble despues de uno normal", fila, columna);
        }
        for (int a = n + 1; a < vac.arrint.size(); a++) {
            if (vac.arrint.get(a) != 1) {
                return aux.error("Indice fuera de rango " + vac.arrint.get(a), vac.arrNodo_paerrores.get(a).fila, vac.arrNodo_paerrores.get(a).columna);
            }
        }
        if (ins instanceof Vector) {
            if (((Vector) ins).arr.size() == 1) {
                vec.update(vac.arrint.get(n) - 1, ((Vector) ins).arr.get(0));
                return null;
            }
        } else if (ins instanceof Simbolo_prim) {
            vec.update(vac.arrint.get(n) - 1, (Simbolo_prim) ins);
            return null;
        }

        return aux.error("En la posicion de un vector solo se aceptan simbolos. ", fila, columna);
    }

    public Object setLista(Lista lst, int n, Auxiliar aux, Var_acceso vac, Object ins) {
        if (!(n < vac.arrint.size())) {
            return null;
        }
        int fila = vac.arrNodo_paerrores.get(n).fila;
        int columna = vac.arrNodo_paerrores.get(n).columna;
        Object o = null;
        if (vac.arrEsAccesoDoble.get(n)) {
            if (n + 1 == vac.arrint.size()) {
                if (ins instanceof Vector || ins instanceof Lista || ins instanceof Simbolo_prim) {
                    lst.set(ins, vac.arrint.get(n));
                    return null;
                }
                return aux.error("Listas solo acepta listas, vectores y primarios", fila, columna);
            }
            o = lst.acceso2(vac.arrint.get(n));
            if (o instanceof Lista) {
                return setLista((Lista) o, n + 1, aux, vac, ins);
            } else if (o instanceof Vector) {
                return setVec((Vector) o, n + 1, aux, vac, ins);
            }

        } else {
            if (vac.ultimoAccesoDoble >= n) {
                return aux.error("No se pude obtener un acceso doble despues de uno normal", fila, columna);
            }
            for (int a = n + 1; a < vac.arrint.size(); a++) {
                if (vac.arrint.get(a) != 1) {
                    return aux.error("Indice fuera de rango " + vac.arrint.get(a), vac.arrNodo_paerrores.get(a).fila, vac.arrNodo_paerrores.get(a).columna);
                }
            }
            if (ins instanceof Vector) {
                if (((Vector) ins).arr.size() == 1) {
                    lst.set(ins, vac.arrint.get(n));
                } else {
                    return aux.error("con el acceso [] solo se aceptan primitvos o vectores o listas de un solo elemento. ", fila, columna);
                }
            } else if (ins instanceof Lista) {
                if (((Lista) ins).arr.size() == 1) {
                    lst.set(ins, vac.arrint.get(n));
                } else {
                    return aux.error("con el acceso [] solo se aceptan primitvos o vectores o listas de un solo elemento. ", fila, columna);
                }
            } else if (ins instanceof Simbolo_prim) {
                lst.set(ins, vac.arrint.get(n));
            } else {
                return aux.error("con el acceso [] solo se aceptan primitvos o vectores o listas de un solo elemento. ", fila, columna);
            }
            return null;
        }
        return null;
    }
}
