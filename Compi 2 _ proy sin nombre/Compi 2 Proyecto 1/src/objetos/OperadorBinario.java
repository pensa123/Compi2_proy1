/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Array;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Lista;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;

/**
 *
 * @author ferna
 */
public class OperadorBinario extends Nodo {

    //TODO FALTA POTENCIA Y MODULO
    public enum Operando {

        comparacion, desigualdad, or, and, mayorque, menorque, mayorigual, menorigual, mas, menos, por, div, potencia, modulo
    };

    Auxiliar au;
    public Operando operador;

    public OperadorBinario(int f, int c, Nodo hijoI, Nodo hijoD, Operando op) {
        super(f, c, hijoI, hijoD);
        operador = op;
    }

    @Override
    public void dibujar(Dibujador d, String padre) {
        d.st += this.hashCode() + "[label=\"" + this.getClass().getSimpleName() + " -" + operador + "-\" ]; \n";
        d.st += padre + " ->  " + this.hashCode() + "; \n";
        dibHijos(d);
    }

    public boolean esnumerico(Simbolo_prim s) {
        return s.tp == Tipos.numerico || s.tp == Tipos.entero;
    }

    @Override
    public Object ejecutar(Tabla_Sim ts, Auxiliar aux) {
        /* if (aux.aiuda == 30000) {
         System.gc();
         aux.aiuda = 0;

         aux.aiuda2++;
         }
         aux.aiuda++;*/

        au = aux;
        Object o1, o2;
        Simbolo_prim s1, s2, sr;

        o1 = hijos.get(0).ejecutar(ts, aux);
        o2 = hijos.get(1).ejecutar(ts, aux);

        if (o1 == null || o2 == null) {
            return aux.error("Solo se esperan operaciones de matrices y de vectores. ", fila, columna);
        }

        //System.out.println("(" + (aux.aiuda++) +")" + o1.getClass().getSimpleName() + " " + operador + " " + o2.getClass().getSimpleName());
        if (o1 instanceof Array || o1 instanceof Lista
                || o2 instanceof Array || o2 instanceof Lista) {
            if (operador == Operando.mas) {

                return new Simbolo_prim(Tipos.cadena, o1.toString() + o2.toString());
            }
        }

        if (o1 instanceof Simbolo_prim) {
            return ejec_sp((Simbolo_prim) o1, o2);
        } else if (o1 instanceof Vector) {
            return ejec_vc((Vector) o1, o2);
        } else if (o1 instanceof Matriz) {  /*aun falta el instanceof de matriz */

            return ejec_mt((Matriz) o1, o2);
        }

        return aux.error("Solo se esperan operaciones de matrices y de vectores. ", fila, columna);
    }

    public Object ejec_mt(Matriz m1, Object o2) {
        if (o2 instanceof Vector) {
            Simbolo_prim sp = au.dev_sp(o2);
            if (sp == null) {
                return au.error("Solo se puede operar una matriz con un vector de una posicion o otra matriz con el mismo tamanio", fila, columna);
            }
            o2 = sp;
        } else if (o2 instanceof Matriz) {
            Matriz m2 = (Matriz) o2;
            if (m1.filas != m2.filas || m1.columnas != m2.columnas) {
                au.error("Solo se pueden ejecutar matrices si tienen las mismas dimensiones", fila, columna);
            }
            Matriz m = new Matriz(au);
            Vector v = new Vector(au);
            for (int a = 0; a < m1.arr.size(); a++) {
                Simbolo_prim sp1 = m1.arr.get(a), sp2 = m2.arr.get(a);
                Simbolo_prim sr = this.ejec_sp_sp(sp1, sp2);

                if (sr == null) {
                    return sr;
                }
                v.agregar(sr);
            }
            m.set(v.arr, m1.filas, m1.columnas);
            return m;
        }

        if (o2 instanceof Simbolo_prim) {
            System.out.println("matriz -  simbolo");
            Matriz m = new Matriz(au);
            Vector v = new Vector(au);
            for (Simbolo_prim s : m1.arr) {
                Simbolo_prim sr = ejec_sp_sp(s, (Simbolo_prim) o2);
                if (sr == null) {
                    return null;
                }
                v.agregar(sr);
            }
            m.set(v.arr, m1.filas, m1.columnas);
            return m;
        }
        return au.error("Matriz solo se puede operar con matrices o vectores de una posicion", fila, columna);
    }

    public Object ejec_vc(Vector v1, Object o2) {
        if (o2 instanceof Simbolo_prim) {
            int a = 0;
            Vector v = new Vector(au);
            for (Simbolo_prim s1 : v1.arr) {
                Simbolo_prim sr = ejec_sp_sp(s1, (Simbolo_prim) (o2));
                if (sr == null) {
                    return null;
                }
                v.update(a++, sr);
            }
            return v;
        } else if (o2 instanceof Vector) {
            Vector v = new Vector(au), v2 = (Vector) o2;
            if (v1.tamanio == 1) {
                return ejec_sp(v1.arr.get(0), v2);
            } else if (v2.tamanio == 1) {
                return ejec_vc(v1, v2.arr.get(0));
            } else if (v1.tamanio == v2.tamanio) {
                for (int a = 0; a < v1.tamanio; a++) {
                    Simbolo_prim s1 = v1.arr.get(a);
                    Simbolo_prim s2 = v2.arr.get(a);
                    Simbolo_prim sr = ejec_sp_sp(s1, s2);
                    if (sr == null) {
                        return null;
                    }
                    v.update(a, sr);
                }
                return v;
            } else {
                System.out.println("error, al operar 2 vectores tienen que ser del mismo tamaño o uno de solo una posicion");
            }
        } else if (o2 instanceof Matriz) {
            Simbolo_prim sp = au.dev_sp(v1);
            if (sp == null) {
                return au.error("Matriz solo se puede operar con un vector de una posicion o con una matriz de las mismas dimensiones ", fila, columna);
            }
            return this.ejec_sp(sp, o2);
        }
        System.out.println("error, vector solo se puede operar con vector o con un primitivo");
        return null;
    }

    public Object ejec_sp(Simbolo_prim s1, Object o2) {
        if (o2 instanceof Simbolo_prim) {
            return ejec_sp_sp((Simbolo_prim) s1, (Simbolo_prim) o2);
        } else if (o2 instanceof Vector) {
            int a = 0;
            Vector v2 = (Vector) o2;
            Vector v = new Vector(au);
            for (Simbolo_prim s2 : v2.arr) {
                Simbolo_prim sr = ejec_sp_sp(s1, s2);
                if (sr == null) {
                    return null;
                }
                v.update(a++, sr);
            }
            return v;
        } else if (o2 instanceof Matriz) {  /*aun falta el instanceof de matriz */

            Matriz m2 = (Matriz) o2;
            System.out.println("matriz -  simbolo");
            Matriz m = new Matriz(au);
            Vector v = new Vector(au);
            for (Simbolo_prim s : m2.arr) {
                Simbolo_prim sr = ejec_sp_sp(s1, s);
                if (sr == null) {
                    return null;
                }
                v.agregar(sr);
            }
            m.set(v.arr, m2.filas, m2.columnas);
            return m;

        }

        return au.error("operando no valido", fila, columna);
    }

    public Simbolo_prim ejec_sp_sp(Simbolo_prim s1, Simbolo_prim s2) {
        Simbolo_prim sr;
        boolean b = false;

        boolean esnumerico = this.esnumerico(s1) && this.esnumerico(s2);
        boolean esint = s1.tp == s2.tp && s1.tp == Tipos.entero;
        boolean esbool = s1.tp == s2.tp && s1.tp == Tipos.booleano;
        boolean soncadena = s1.tp == Tipos.cadena && s2.tp == Tipos.cadena;
        int error = 0;
        /*
         * 1 se esperaban operadores numericos, 
         * 2 se esperaban operadores booleanos, 
         * 3 al menos uno de los dos debe de ser de tipo cadena, 
         * 4 no se puede dividir entre 0
         * 5 se esperaban tipos iguales o numericos.
         */
        sr = null;
        switch (this.operador) {
            case mas:
                if (esnumerico) {
                    sr = new Simbolo_prim(esint ? Tipos.entero : Tipos.numerico, Double.parseDouble(s1.valor + "") + Double.parseDouble(s2.valor + ""));
                } else if (s1.tp == Tipos.cadena || s2.tp == Tipos.cadena) {
                    sr = new Simbolo_prim(Tipos.cadena, s1.toString() + s2.toString());
                } else {
                    error = 3;
                }
                break;
            case menos:
                if (esnumerico) {
                    sr = new Simbolo_prim(esint ? Tipos.entero : Tipos.numerico, Double.parseDouble(s1.valor + "") - Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;
            case por:
                if (esnumerico) {
                    sr = new Simbolo_prim(esint ? Tipos.entero : Tipos.numerico, Double.parseDouble(s1.valor + "") * Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;
            case div:
                if (esnumerico) {
                    if ((Double.parseDouble(s2.valor + "")) == 0) {
                        error = 4;
                    } else if (esint) {
                        sr = new Simbolo_prim(Tipos.entero, ((Double.parseDouble(s1.valor + "") / Double.parseDouble(s2.valor + ""))));
                    } else {
                        sr = new Simbolo_prim(Tipos.numerico, Double.parseDouble(s1.valor + "") / Double.parseDouble(s2.valor + ""));
                    }
                } else {
                    error = 1;
                }
                break;
            //todo falta potencia y modulo
            case potencia:
                if (esnumerico) {
                    sr = new Simbolo_prim(esint ? Tipos.entero : Tipos.numerico, Math.pow(Double.parseDouble(s1.valor + ""), Double.parseDouble(s2.valor + "")));
                } else {
                    error = 1;
                }
                break;
            case modulo:
                if (esnumerico) {
                    sr = new Simbolo_prim(esint ? Tipos.entero : Tipos.numerico, Double.parseDouble(s1.valor + "") % Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;

            case comparacion:
            case desigualdad:
                if (esnumerico) {
                    b = Double.parseDouble(s1.valor + "") == Double.parseDouble(s2.valor + "");
                } else if (s1.tp == s2.tp) {
                    b = (s1.toString()).equals(s2.toString());
                } else if (s1.tp == Tipos.nulo || s2.tp == Tipos.nulo) {
                    b = (s1.toString()).equals(s2.toString());
                } else {
                    error = 5;
                }
                sr = new Simbolo_prim(Tipos.booleano, operador == Operando.comparacion ? b : !b);
                break;

            case mayorque:  //>
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") > Double.parseDouble(s2.valor + ""));
                } else if (soncadena) {
                    int n = this.CompareString(s1.toString(), s2.toString());
                    sr = new Simbolo_prim(Tipos.booleano, n > 0);
                } else {
                    error = 1;
                }
                break;
            case mayorigual:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") >= Double.parseDouble(s2.valor + ""));
                } else if (soncadena) {
                    int n = this.CompareString(s1.toString(), s2.toString());
                    sr = new Simbolo_prim(Tipos.booleano, n >= 0);
                } else {
                    error = 1;
                }
                break;
            case menorque:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") < Double.parseDouble(s2.valor + ""));
                } else if (soncadena) {
                    int n = this.CompareString(s1.toString(), s2.toString());
                    sr = new Simbolo_prim(Tipos.booleano, n < 0);
                } else {
                    error = 1;
                }
                break;
            case menorigual:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") <= Double.parseDouble(s2.valor + ""));
                } else if (soncadena) {
                    int n = this.CompareString(s1.toString(), s2.toString());
                    sr = new Simbolo_prim(Tipos.booleano, n <= 0);
                } else {
                    error = 1;
                }
                break;
            case and:
                if (esbool) {
                    sr = new Simbolo_prim(Tipos.booleano, (boolean) s1.valor && (boolean) s2.valor);
                } else {
                    error = 2;
                }
                break;
            case or:
                if (esbool) {
                    sr = new Simbolo_prim(Tipos.booleano, (boolean) s1.valor || (boolean) s2.valor);
                } else {
                    error = 2;
                }
                break;

        }

        /*
         * 1 se esperaban operadores numericos, 
         * 2 se esperaban operadores booleanos, 
         * 3 al menos uno de los dos debe de ser de tipo cadena, 
         * 4 no se puede dividir entre 0
         * 5 se esperaban tipos iguales o numericos.
         */
        switch (error) {
            case 1:
                System.out.println("los operadores deben de ser numericos");
                au.error("Los operadores debe de ser numericos ", fila, columna);
                return null;
            case 2:
                System.out.println("los operadores deben de ser booleanos");
                au.error("Los operadores deben de ser booleanos ", fila, columna);
                return null;
            case 3:
                System.out.println("Los operadores deben de ser numericos o al menos debe de haber una cadena.");
                au.error("Los operadores deben de ser numericos o al menos debe de haber una cadena", fila, columna);
                return null;
            case 4:
                System.out.println("no se puede dividir entre 0");
                au.error("no se puede dividir entre 0", fila, columna);
                return null;
            case 5:
                System.out.println("no se puede comparar diferentes tipos a menos que sean numericos");
                au.error("no se puede comparar diferentes tipos a menos que sean numericos", fila, columna);
                return null;
        }

        return sr;

    }

    // n > 0 si  st1 es mayor
    // n < 0 si  st2 es mayor
    // n == 0 si st1 es igual que est2
    public int CompareString(String st1, String st2) {
        System.out.println(st1 + " <====> " + st2);
        int l1 = st1.length();
        int l2 = st2.length();
        int lmin = Math.min(l1, l2);
        for (int a = 0; a < lmin; a++) {
            int str1_ch = (int) st1.charAt(a);
            int str2_ch = (int) st2.charAt(a);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        } else {
            return 0;
        }

    }

}
