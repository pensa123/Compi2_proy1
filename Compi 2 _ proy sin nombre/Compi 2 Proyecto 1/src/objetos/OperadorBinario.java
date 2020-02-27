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

        Simbolo_prim s1, s2, sr;

        s1 = (Simbolo_prim) hijos.get(0).ejecutar(ts, aux);
        s2 = (Simbolo_prim) hijos.get(1).ejecutar(ts, aux);
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
                    sr = new Simbolo_prim(Tipos.cadena, s1.valor + "" + s2.valor);
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
