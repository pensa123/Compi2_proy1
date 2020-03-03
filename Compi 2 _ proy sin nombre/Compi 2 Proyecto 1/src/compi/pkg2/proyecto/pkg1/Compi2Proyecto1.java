/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compi.pkg2.proyecto.pkg1;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import GramaticaJavaCC.Gramatica;
import GramaticaJavaCC.ParseException;
import GramaticaJavaCC.TokenMgrError;
import Lexico.Lexer;
import Sintactico.Sint;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import interfaz.Inicio;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;

import objetos.OperadorBinario;
import objetos.OperadorBinario.Operando;
import objetos.OperadorUnario;
import objetos.OperadorUnario.Op;

import objetos.Primitivo;

/**
 *
 * @author ferna
 */
public class Compi2Proyecto1 {

    /**
     * @param args the command line arguments
     */
    ArrayList<String> toPruebas = new ArrayList<String>();

    public static void main(String[] args) {

        mgrafico();

        /*String st = "\\n";
         st = st.replace("\\n", "\n");
         System.out.println("---"+ st + "----------");*/
        //Compi2Proyecto1 c2 = new Compi2Proyecto1();
    }

    public static void mgrafico() {
        Inicio ini = new Inicio();
        ini.setVisible(true);

        probando(ini);
    }

    public static void probando(Inicio ini) {
        ini.nueva_pestana("x[5] = 5\n"
                + "x[2][1][1][1] =  true\n"
                + "print(x)\n");

        ini.nueva_pestana("x[5] = 8; \n"
                + "x = x+2; \n"
                + "print(x);\n"
                + "print(x+x);");

        ini.nueva_pestana("x = 2; \n"
                + "if(x > 0){\n"
                + "	print(\"esto es\");\n"
                + "	print(\"verdadero\");\n"
                + "}else{\n"
                + "	print(\"esto es\");\n"
                + "	print(\"falso\");\n"
                + "}");

        ini.nueva_pestana("print(\"probando ambitos\");\n"
                + "x = 2; \n"
                + "if( 2== 2){\n"
                + "	x = 3; \n"
                + "	y = 2; \n"
                + "}\n"
                + "print(x); \n"
                + "print(y);");

        ini.nueva_pestana("x = 0;\n"
                + "if(x== 0){\n"
                + "	print(\"funciona el if\");\n"
                + "}\n"
                + "while(x < 10){\n"
                + "	print(\"va por \" + x);\n"
                + "	x = x+1; \n"
                + "	if(x == 5){\n"
                + "		continue;\n"
                + "		print(\"no deberia de llegar aqui\");\n"
                + "	}\n"
                + "}\n"
                + "print(\"funciona el while\");\n"
                + "\n"
                + "do{\n"
                + "	print(\"va por \" + x);\n"
                + "	x = x-1;\n"
                + "	if(x == 6){\n"
                + "		break;\n"
                + "		print(\"tampoco deberia de imprimir esto\");	\n"
                + "	} \n"
                + "}while(x >= 0)\n"
                + "print(\"funciona el do while\");");

        ini.nueva_pestana("print(\"unarios\"); \n"
                + "x[5] = true; \n"
                + "y = !x; \n"
                + "print(x);\n"
                + "print(y);\n"
                + "print(\"ternarios\");\n"
                + "\n"
                + "z = x || y ? \"abc\" : \"def\"; ");

        ini.nueva_pestana("x[5] = true; \n"
                + "x = !x; \n"
                + "x[1] = 8; \n"
                + "x[3] = 2; \n"
                + "print(x);\n"
                + "for(i in x){\n"
                + "	if(i == 2){\n"
                + "		break	\n"
                + "	}\n"
                + "	print(i);\n"
                + "}");

        ini.nueva_pestana("f = function(){\n"
                + "	print(\"hola\");\n"
                + "	}\n"
                + "f();");

        ini.nueva_pestana("print(\"prueba metodos\")\n"
                + "saludo = function(){\n"
                + "	print(\"hola\");\n"
                + "	}\n"
                + "saludo();");

        ini.nueva_pestana("x = c(1,2,3,4,5);\n"
                + "print(x);\n"
                + "mat = matrix(x, 5 , 4);\n"
                + "print(mat);\n"
                + "print(mat[1]);\n"
                + "print(mat[1,1]);\n"
                + "print(mat[1,]);\n"
                + "print(mat[,1]);");

    }

    public Compi2Proyecto1() {

        // para ver asociatividad y si esta bien la presedencia, se haran mas pruebas
        // luego.
        /*
         * this.toPruebas.add("print(1 == 2 == 3 )");
         * this.toPruebas.add("print(1 == 2 != 3 )");
         * this.toPruebas.add("print(1 == 2 || 1 != 3 && 1 == 2  )");
         * this.toPruebas.add("print( 1 <= 2  || 2 >=3 ==2  )");
         * this.toPruebas.add("print(!( -1 < 2)  )"); this.toPruebas.add("print(aux)");
         * this.toPruebas.add("print( aux[1]  )");
         * this.toPruebas.add("print( true || false  )");
         * 
         */
        /*
         * this.toPruebas.add("print( aux[1,]  )");
         * this.toPruebas.add("print( aux[,1]  )");
         * this.toPruebas.add("print( aux[1,1]  )");
         * this.toPruebas.add("print( aux[1][2][3]  )");
         */
        //se prueban primitivos y unarios. 
        this.toPruebas.add("print(\" hola  \"); "
                + "         print( 2 ); "
                + "         print( 2.5 ); "
                + "         print( true ); "
                + "         print( - 6  ); "
                + "         print( !true  ); "
                + "");

        this.toPruebas.add("  "
                + "         print( 2 +  3 ); \n"
                + "         print( 2 +  3.5 ); \n"
                + "         print( 2.3 +  3 ); \n"
                + "         print( \"hola\" + 2 ); \n"
                + "         print(2 + \"hola\" ); \n"
                + "         print(true + \"hola\" ); \n"
                + "         print(false + \"hola\" ); \n"
                + "         print( \"hola\" + \" Mundo\" ); \n"
                + "         print( 3 -2 ); \n"
                + "         print( 3 * 2 ); \n"
                + "         print( 3 / 2 ); \n"
                + "         print( 3.1 / 2 ); \n"
                + "         print( 2 == 2 ); \n"
                + "         print( 2 == 2.5 ); \n"
                + "         print( true == true ); \n"
                + "         print( \"hola\" == \"hola\" ); \n"
                + "         print( \"hola\" != \"hola\" ); \n"
                + "         print( \"hola\" != \"hoa\" ); \n"
                + "         print( 1 < 2 ); \n"
                + "         print( 3 > 3 ); \n"
                + "         print( 3 >= 3 ); \n"
                + "         print( 3 <= 3 ); \n"
                + "         print( \"prueba and\" +  true && true ); \n"
                + "         print( true || false); \n"
                + "");

        this.toPruebas.add(" print(\"hola\"   +   (true  || true)); ");
        this.toPruebas.add(" print(  true ? true ? \"v1v2\" : \"v1f2\" : \"f1\"   ); ");

        this.toPruebas.add(" print(  algo[1][1][[1]]   ); ");
        this.toPruebas.add(" prueba = 5 ");

        this.toPruebas.add(" x=2; \n"
                + "x[1][[1]] = 3; \n"
                + "n[1,1] = 1;"
                + "x[1,] = 2;"
                + "x[,1] = 3;");

        this.toPruebas.add("x = \"hola\"; \n "
                + "print( x );\n"
                + "x = 1 + 2 + 3;\n"
                + "print(x);");

        this.toPruebas.add("if(2==2){ print(\"es true\"); }else if(2 == 2) { print(\"segundo es true\"); } else { print(\"no es true\");  } ");

        this.toPruebas.add("while(true){ print(\"hola\");}   \n"
                + "do { print(\"hola\") }while(true)");

        // el primero es para probar el numero de prueba
        // el segundo 0 para los dos 1 para javacc 2 para flex y cup
        this.probar(0, 1);
    }

    void probar(int x, int x1) {

        if (x == 0) {
            for (int a = 0; a < this.toPruebas.size(); a++) {
                String st = toPruebas.get(a);
                int n = a + 1;
                System.out.println("-------------------------------");
                System.out.println(st);
                if (x1 == 0 || x1 == 1) {
                    System.out.println("---------------------------");
                    System.out.println("prueba javacc");
                    if (this.probarJavacc(st, n)) {
                        break;
                    }
                }
                if (x1 == 0 || x1 == 2) {
                    System.out.println("---------------------------");
                    System.out.println("prueba flex y cup");
                    this.probarFlexYCup(st, n);
                    System.out.println("---------------------------");
                }
            }
        } else {
            String st = toPruebas.get(x - 1);
            int n = x;

            System.out.println("-------------------------------");
            System.out.println(st);
            if (x1 == 0 || x1 == 1) {
                System.out.println("---------------------------");
                System.out.println("prueba javacc");
                this.probarJavacc(st, n);

            }
            if (x1 == 0 || x1 == 2) {
                System.out.println("---------------------------");
                System.out.println("prueba flex y cup");
                this.probarFlexYCup(st, n);
                System.out.println("---------------------------");
            }

        }
    }

    boolean probarJavacc(String stPrueba, int n) {
        try {

            Gramatica parser = new Gramatica(new BufferedReader(new StringReader(stPrueba)));
            parser.Analizar();

            System.out.println("-------------------------------");
            System.out.println("Todo bien todo correcto");
            System.out.println("-------------------------------");

            ArrayList arr = parser.arr;

            this.dibujar(arr, "INICIO_JAVACC");

            this.ejecutarJavacc(arr);

            return false;
        } catch (ParseException e) {
            // consola.setText(consola.getText() + e.getMessage() + "\n");
            System.err.println(e.getMessage());
            System.out.println("error en prueba " + n);
        } catch (TokenMgrError e) {
            // consola.setText(consola.getText() + e.getMessage() + "\n");
            System.err.println(e.getMessage());
            System.out.println("error en prueba " + n + " javacc");
        }

        return true;
    }

    void ejecutarJavacc(ArrayList<Nodo> arr) {
        Tabla_Sim ts = new Tabla_Sim();
        Auxiliar aux = new Auxiliar(new TextArea(), new TextArea(), ts);
        for (Nodo n : arr) {
            n.ejecutar(ts, aux);
        }
    }

    void probarFlexYCup(String stPrueba, int n) {

        Lexer lexer = new Lexer(new BufferedReader(new StringReader(stPrueba)));
        Sint s = new Sint(lexer);

        try {
            s.parse();

            ArrayList arr = s.arr;
            this.dibujar(arr, "INICIO_FLEX_Y_CUP");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dibujar(ArrayList<Nodo> arr, String title) {
        Dibujador d = new Dibujador();

        d.st = "digraph G { \n " + title + " ;\n ";

        for (Nodo n : arr) {

            n.dibujar(d, title);
        }

        d.st += "}";
        System.out.println(d.st);
    }
}
