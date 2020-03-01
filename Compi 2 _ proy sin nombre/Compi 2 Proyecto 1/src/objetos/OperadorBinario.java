/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class OperadorBinario extends Nodo {

    //TODO FALTA POTENCIA Y MODULO
    public enum Operando {

        comparacion, desigualdad, or, and, mayorque, menorque, mayorigual, menorigual, mas, menos, por, div
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
        au = aux;
        Object o1, o2;
        Simbolo_prim s1, s2, sr;

        o1 = hijos.get(0).ejecutar(ts, aux);
        o2 = hijos.get(1).ejecutar(ts, aux);

        if (o1 instanceof Simbolo_prim) {
            return ejec_sp((Simbolo_prim) o1, o2);
        } else if (o1 instanceof Vector) {
            return ejec_vc((Vector) o1, o2);
        } else {  /*aun falta el instanceof de matriz */

        }

        System.out.println("no se ha echo operacion entre " + o1.getClass().getSimpleName() + " y " + o2.getClass().getSimpleName());

        return "";
    }

    public Object ejec_vc(Vector v1, Object o2) {
        if (o2 instanceof Simbolo_prim) {
            int a = 0;
            Vector v = new Vector(v1.tp);
            for (Simbolo_prim s1 : v1.arr) {
                v.update(a++, ejec_sp_sp(s1, (Simbolo_prim) (o2)));
            }
            return v;
        } else if (o2 instanceof Vector) {
            Vector v = new Vector(v1.tp), v2 = (Vector) o2;
            if (v1.tamanio == 1) {
                return ejec_sp(v1.arr.get(0), v2);
            } else if (v2.tamanio == 1) {
                return ejec_vc(v1, v2.arr.get(0));
            } else if (v1.tamanio == v2.tamanio) {
                for (int a = 0; a < v1.tamanio; a++) {
                    Simbolo_prim s1 = v1.arr.get(a);
                    Simbolo_prim s2 = v2.arr.get(a);
                    v.update(a, ejec_sp_sp(s1, s2));
                }
                return v;
            } else {
                System.out.println("error, al operar 2 vectores tienen que ser del mismo tamaño o uno de solo una posicion");
            }
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
            Vector v = new Vector(v2.tp);
            for (Simbolo_prim s2 : v2.arr) {
                v.update(a++, ejec_sp_sp(s1, s2));
            }
            return v;
        } else {  /*aun falta el instanceof de matriz */

        }

        return null;
    }

    public Simbolo_prim ejec_sp_sp(Simbolo_prim s1, Simbolo_prim s2) {
        Simbolo_prim sr;
        boolean b = false;

        boolean esnumerico = this.esnumerico(s1) && this.esnumerico(s2);
        boolean esint = s1.tp == s2.tp && s1.tp == Tipos.entero;
        boolean esbool = s1.tp == s2.tp && s1.tp == Tipos.booleano;
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
                        sr = new Simbolo_prim(Tipos.entero, ((int) (Double.parseDouble(s1.valor + "") / Double.parseDouble(s2.valor + ""))));
                    } else {
                        sr = new Simbolo_prim(Tipos.numerico, Double.parseDouble(s1.valor + "") / Double.parseDouble(s2.valor + ""));
                    }
                } else {
                    error = 1;
                }
                break;
            //todo falta potencia y modulo

            case comparacion:
            case desigualdad:
                if (esnumerico) {
                    b = Double.parseDouble(s1.valor + "") == Double.parseDouble(s2.valor + "");
                } else if (s1.tp == s2.tp) {
                    b = (s1.valor + "").equals(s2.valor + "");
                } else {
                    error = 5;
                }
                sr = new Simbolo_prim(Tipos.booleano, operador == Operando.comparacion ? b : !b);
                break;

            case mayorque:  //>
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") > Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;
            case mayorigual:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") >= Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;
            case menorque:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") < Double.parseDouble(s2.valor + ""));
                } else {
                    error = 1;
                }
                break;
            case menorigual:
                if (esnumerico) {
                    sr = new Simbolo_prim(Tipos.booleano, Double.parseDouble(s1.valor + "") <= Double.parseDouble(s2.valor + ""));
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
                break;
            case 2:
                System.out.println("los operadores deben de ser booleanos");
                break;
            case 3:
                System.out.println("Los operadores deben de ser numericos o al menos debe de haber una cadena.");
                break;
            case 4:
                System.out.println("no se puede dividir entre 0");
                break;
            case 5:
                System.out.println("no se puede comparar diferentes tipos a menos que sean numericos");
                break;
        }

        return sr;

    }

}
