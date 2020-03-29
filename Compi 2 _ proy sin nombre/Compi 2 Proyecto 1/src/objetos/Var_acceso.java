/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
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
public class Var_acceso extends Nodo {

    public boolean mostrarErrores = true;
    public boolean acceso_matriz;

    public Estructura est;
    public String nest = "";

    public int forma;
    public boolean n2enadelanteSon1 = true;
    public ArrayList<Integer> arrint = new ArrayList<>();
    public ArrayList<Boolean> arrEsAccesoDoble = new ArrayList<>();
    public ArrayList<Nodo> arrNodo_paerrores = new ArrayList<>();
    public boolean hayAccesoDoble = false;
    public int ultimoAccesoDoble = -1;

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
            Object o = n.ejecutar(ts, aux);
            if (o instanceof Estructura) {
                est = (Estructura) o;
            }
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
            this.forma = am.forma;
            Simbolo_prim s1 = aux.dev_sp(am.hijos.get(0).ejecutar(ts, aux));
            if (am.forma == 1) {
                Simbolo_prim s2 = aux.dev_sp(am.hijos.get(1).ejecutar(ts, aux));
                if (s1 == null || s2 == null) {
                    return aux.error("En matriz[n,n] se esperan dos enteros", am.fila, am.columna);
                }
                if (!(aux.esEntero(s1) && aux.esEntero(s2))) {
                    return aux.error("En matriz[n,n] se esperan dos enteros", am.fila, am.columna);
                }

                int n1 = (int) Double.parseDouble(s1.valor + "");
                int n2 = (int) Double.parseDouble(s2.valor + "");

                arrint.add(n1);
                arrint.add(n2);

                Object ret = mat.obtener(n1, n2);
                if (ret == null) {
                    return aux.error("Indice [ " + n1 + " , " + n2 + "] fuera de rango ", fila, columna);
                } else {
                    return ret;
                }
            }
            //2 = e,   3 = ,e 
            boolean bol = am.forma == 2;
            if (s1 == null) {
                return aux.error("En matriz[" + (bol ? "e," : ",e") + "] se espera entero", am.fila, am.columna);
            }
            if (!(aux.esEntero(s1))) {
                return aux.error("En matriz[" + (bol ? "e," : ",e") + "] se espera entero", am.fila, am.columna);
            }
            arrint.add((int) Double.parseDouble(s1.valor + ""));
            int nn = (int) Double.parseDouble(s1.valor + "");
            Object ret = mat.obtener(nn, bol);
            return ret == null ? aux.error("[" + (bol ? nn + "," : "," + nn) + "] fuera de rango ", am.fila, am.columna) : ret;
        } else {
            for (int a = 1; a < hijos.size(); a++) {
                Acceso ac = (Acceso) hijos.get(a);
                ac.ejecutar(ts, aux);
                arrNodo_paerrores.add(ac);
                //TODO tal vez hay que quitar numerico pero ya despues miramos
                if (ac.sp != null) {
                    if (ac.sp.tp == Tipos.entero || ac.sp.tp == Tipos.numerico) {
                        int auxint = (int) Double.parseDouble(ac.sp.valor.toString());
                        this.arrint.add(auxint);
                        this.arrEsAccesoDoble.add(ac.accesoDoble);
                        this.hayAccesoDoble = this.hayAccesoDoble || ac.accesoDoble;

                        if (ac.accesoDoble) {
                            this.ultimoAccesoDoble = a - 1;
                        }

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
        if (est instanceof Array) {
            return mostrarErrores ? sacarDatosArr((Array) est, aux) : null;
        } else if (est instanceof Lista) {
            return sacarDatosLista((Lista) est, 0, aux);
            //TODOS hay que agregar el if instanceof array :D
        } else if (this.n2enadelanteSon1) {
            if (this.hayAccesoDoble) {
                return this.hayAccesoDoble ? aux.error("No se aceptan accesos dobles en accesos a vectores o matrices", fila, columna) : null;
            }
            if (est instanceof Vector) {
                Object ret = ((Vector) est).obtener(arrint.get(0));
                if (ret == null) {
                    return this.mostrarErrores ? aux.error("Indice fuera de rango " + arrint.get(0), fila, columna) : null;
                } else {
                    return ret;
                }
            } else if (est instanceof Matriz) {
                Object ret = ((Matriz) est).obtener(arrint.get(0));
                if (ret == null) {
                    return this.mostrarErrores ? aux.error("Indice fuera de rango " + arrint.get(0), fila, columna) : null;
                } else {
                    return ret;
                }
            }
        } else {
            return aux.error("Valor fuera de rango. ", fila, columna);
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

    public Object sacarDatosArr(Array arr, Auxiliar aux) {
        if (arr.arrD.size() > arrint.size()) {
            return mostrarErrores ? aux.error("Indice fuera del rango", fila, columna) : null;
        }
        ArrayList<Integer> arri2 = this.copyArr(arrint);
        ArrayList<Integer> arriS = new ArrayList<>();
        for (int a = 0; a < arr.arrD.size(); a++) {
            arriS.add(arri2.remove(0));
            this.arrint.remove(0);
            Nodo n = this.arrNodo_paerrores.remove(0);
            if (this.arrEsAccesoDoble.remove(0)) {
                return aux.error("No se puede acceder a un arreglo con acceso dobles, se requiere el simple [] ", n.fila, n.columna);
            }
            this.ultimoAccesoDoble--;
        }

        Object o = arr.obtener(arriS);

        if (o == null) {
            return mostrarErrores ? aux.error("Indice fuera de rango", fila, columna) : null;
        }

        if (arri2.size() == 0) {
            return o;
        }
        if (o instanceof Simbolo_prim) {
            o = new Vector((Simbolo_prim) o, aux);
        }

        if (o instanceof Vector) {
            return this.sacarDatoVec((Vector) o, 0, aux);
        } else if (o instanceof Lista) {
            return this.sacarDatosLista((Lista) o, 0, aux);
        }
        return null;
    }

    public Object sacarDatoVec(Vector vec, int n, Auxiliar aux) {
        if (!(n < this.arrint.size())) {
            return null;
        }
        int fila = this.arrNodo_paerrores.get(n).fila;
        int columna = this.arrNodo_paerrores.get(n).columna;
        if (ultimoAccesoDoble >= n) {
            return mostrarErrores ? aux.error("no se puede hacer un acceso doble sobre un vector.", fila, columna) : null;
        }
        Object o = vec.obtener(arrint.get(n));
        if (n + 1 == arrint.size()) {
            if (o == null) {
                return mostrarErrores ? aux.error("Indice fuera de rango " + arrint, fila, columna) : null;
            }
            return o;
        }
        if (arrint.get(n) == 1) {
            //return o == null ? sacarDatoVec(vec, n + 1, aux) : o;
            return sacarDatoVec(vec, n + 1, aux);
        }

        if (o instanceof Simbolo_prim) {
            Vector v = new Vector(aux);
            v.agregar((Simbolo_prim) o);
            o = v;
        }

        if (o instanceof Vector) {
            return sacarDatoVec((Vector) o, n + 1, aux);
        }
        return mostrarErrores ? aux.error("Indice fuera de rango " + arrint, fila, columna) : null;
    }

    public Object sacarDatosLista(Lista lst, int n, Auxiliar aux) {
        if (!(n < this.arrint.size())) {
            return null;
        }
        int fila = this.arrNodo_paerrores.get(n).fila;
        int columna = this.arrNodo_paerrores.get(n).columna;
        Object o = null;
        if (this.arrEsAccesoDoble.get(n)) {
            o = lst.acceso2(arrint.get(n));
        } else {
            o = lst.acceso1(arrint.get(n));
        }
        if (o == null) {
            return mostrarErrores ? aux.error("Indice " + arrint.get(n) + " fuera de rango", fila, columna) : null;
        }
        if (n + 1 == arrint.size()) {
            return o;
        }

        if (o instanceof Lista) {
            return sacarDatosLista((Lista) o, n + 1, aux);
        } else if (o instanceof Vector) {
            return sacarDatoVec((Vector) o, n + 1, aux);
        } else if (o instanceof Simbolo_prim) {
            Vector v = new Vector(aux);
            v.agregar((Simbolo_prim) o);
            return sacarDatoVec(v, n + 1, aux);
        }
        return null;
    }
}
