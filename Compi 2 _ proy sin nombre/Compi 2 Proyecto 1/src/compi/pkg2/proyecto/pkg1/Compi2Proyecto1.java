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
import Tabla_simbolos.Array;
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
        /*ArrayList<Integer> arrd = new ArrayList<>();
         arrd.add(3);
         arrd.add(3);
         arrd.add(3);
         arrd.add(3);
         ArrayList<Object> arro = new ArrayList<>();
         arro.add(new Simbolo_prim(Tipos.cadena, "hola"));
         arro.add(new Simbolo_prim(Tipos.cadena, "como"));
         arro.add(new Simbolo_prim(Tipos.cadena, "estas"));
         Array arr = new Array(arro, arrd);
         System.out.println(arr.toString());

         */
        //arr.imp();

        //System.out.println( 2.1 % 0.5);
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
        ini.nueva_pestana("print(\"unarios\"); \n"
                + "x[5] = true; \n"
                + "y = !x; \n"
                + "print(x);\n"
                + "print(y);\n"
                + "print(\"ternarios\");\n"
                + "\n"
                + "z = x | y ? \"abc\" : \"def\"; ");

        ini.nueva_pestana("print(\"Esto prueba el while, do while, if, for continue\");\n"
                + "print(\"tambien matrices y vectores\"); \n"
                + "x = 1; \n"
                + "y = 2; \n"
                + "while(y < 10){\n"
                + "	x = c(x , y);\n"
                + "	y = y + 1;\n"
                + "}\n"
                + "do{\n"
                + "	x = c(x, y);\n"
                + "	y = y + 1; \n"
                + "}while(y <= 20);\n"
                + "print(x);\n"
                + "mat = matrix(x , 5 , 4);\n"
                + "print(mat);\n"
                + "print(mat[14]);\n"
                + "print(mat[4,3]);\n"
                + "print(mat[4,]);\n"
                + "print(mat[,3]);\n"
                + ".x = mat[4,]; \n"
                + "print(.x[3][1][1]);\n"
                + "cadena = \"[\";\n"
                + "for(i in mat){\n"
                + "   cadena = cadena + i;\n"
                + "   if(i == 20){\n"
                + "   	continue;	\n"
                + "   }\n"
                + "   cadena = cadena + \", \";\n"
                + "}\n"
                + "cadena = cadena + \"]\";\n"
                + "print(cadena[1]);\n"
                + "for(i in .x){\n"
                + "	print(i);	\n"
                + "}\n"
                + "print(\"suma de mat[3,] y mat[4,]\");\n"
                + "print(mat[3,] + mat[4,]);\n"
                + "mat2 = matrix(c(1 , 0), 5 , 4); \n"
                + "print(Mat2);\n"
                + "print(mat + mat2);\n"
                + "mat[1,] = mat2[1,];\n"
                + "mat[,1] = mat2[,1]; \n"
                + "print(mat);");

        ini.nueva_pestana("mat = matrix(c(true), 3 , 2);\n"
                + "print(mat);\n"
                + "mat[1,1] = 2; \n"
                + "mat[2] = 3;\n"
                + "print(mat);\n"
                + "mat[,1] = c(4,5,6);\n"
                + "print(mat);\n"
                + "mat[1,] = c(9,10);\n"
                + "print(mat);");

        /*        ini.nueva_pestana("Vector = c(12,13,15,16,24,15,17,19,17,15)\n"
         + "print(vector);\n"
         + "print(mean(vector));\n"
         + "print(mode(vector));\n"
         + "print(median(vector));\n"
         + "\n"
         + "print(mean(vector, 14));\n"
         + "print(mode(vector, 14));\n"
         + "print(median(vector, 14));");*/
        ini.nueva_pestana("factorial = function(n){ \n"
                + "    if(n <= 0){ \n"
                + "        return(1); \n"
                + "    } \n"
                + "    \n"
                + "    return(n*factorial(n-1));\n"
                + "}\n"
                + "\n"
                + "potencia = function(base, exp) {\n"
                + "\n"
                + "    if( exp == 0 ){\n"
                + "        return (1);\n"
                + "    }\n"
                + "    return(base*potencia(base,exp-1))\n"
                + "}\n"
                + "\n"
                + "fibonacci = function(n){\n"
                + "    if(n == 1 | n==2){\n"
                + "        return (1);\n"
                + "    }\n"
                + "\n"
                + "    return (fibonacci(n-1)+fibonacci(n-2))\n"
                + "}\n"
                + "\n"
                + "ackermann = function(m,n) {\n"
                + "    if (m == 0) {\n"
                + "        return (n + 1);\n"
                + "    } else if (m > 0 & n == 0) {\n"
                + "        return (ackermann(m - 1, 1));\n"
                + "    } else {\n"
                + "        return (ackermann(m - 1, ackermann(m, n - 1)));\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "print(\"Factorial: \"+factorial(5));\n"
                + "print(\"Potencia: \"+potencia(2,10));\n"
                + "print(\"Fibonacci:\"+fibonacci(10));\n"
                + "print(\"Ackermann:\"+ackermann(3,6));");

        ini.nueva_pestana("Lista2 = list(\"hola mundo\", 43, TRUE, 32.3)\n"
                + "print(lista2);\n"
                + "print(lista2[1]);\n"
                + "lista2[1] = \"hola\";\n"
                + "print(lista2[1]);");

        ini.nueva_pestana("Lista1 = list(\"hola mundo\");\n"
                + "lista2 = list(42,true, 32.3);\n"
                + "lista3 = c(lista1, lista2);\n"
                + "print(lista3);");

        ini.nueva_pestana("arr = array(c(5, list(7,8,9 , c(1,2))), c(2,3,3))\n"
                + "print(arr);\n"
                + "print(\"--------------------\");\n"
                + "print(arr[1][3][1][1]);");

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
