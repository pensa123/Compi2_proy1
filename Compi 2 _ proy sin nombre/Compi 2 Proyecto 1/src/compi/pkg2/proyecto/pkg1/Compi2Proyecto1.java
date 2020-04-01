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
import FuncionesDelLenguaje.Parar;
import GramaticaFlexYCup.Lexer;
import GramaticaFlexYCup.Sint;
import GramaticaJavaCC.Gramatica;
import GramaticaJavaCC.ParseException;
import GramaticaJavaCC.TokenMgrError;
import Tabla_simbolos.Array;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Simbolo_prim;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import interfaz.Inicio;
import java.awt.Desktop;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

        /*
        
         String st = new File(".").getAbsolutePath() + "\\algo.html";

         File f = new File(st);
         try {
         BufferedWriter bw = new BufferedWriter(new FileWriter(f));
         bw.write("<head>\n"
         + "</head>\n"
         + "<body>\n"
         + "	<h1>Hola</h1>\n"
         + "</body>");
         bw.flush();
         bw.close();

         Desktop.getDesktop().open(f);
         } catch (Exception ex) {
         System.out.println("error 1");
         //System.out.println(ex.toString());
         }
        
         */
        mgrafico();

        /*Auxiliar au = new Auxiliar(new TextArea(), new TextArea(), null, true, new ArrayList<>());

         ArrayList<Integer> arrd = new ArrayList<>();
         arrd.add(3);
         arrd.add(3);
         arrd.add(3);
         arrd.add(3);
         arrd.add(3);
         ArrayList<Object> arro = new ArrayList<>();
         arro.add(new Simbolo_prim(Tipos.cadena, "hola"));
         arro.add(new Simbolo_prim(Tipos.cadena, "como"));
         arro.add(new Simbolo_prim(Tipos.cadena, "estasssssssss"));
         Array arr = new Array(arro, arrd, false, au);
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

        ini.nueva_pestana("print(\"probando cosas de escape de cadenas\"); \n"
                + "\n"
                + "print(\"\\\"hola\\\"\");\n"
                + "\n"
                + "print(\"\\n salto\");\n"
                + "\n"
                + "print(\"\\r retorno de carro\");\n"
                + "\n"
                + "print(\"\\t tabulado\");\n"
                + "print(\"Esto prueba el while, do while, if, for continue\");\n"
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
                + "print(mat);\n"
                + "switch(\"hola\"){\n"
                + "	case \"hola\":\n"
                + "		print(\"hola\");\n"
                + "	     break;\n"
                + "	case \"h2\":\n"
                + "		print(\"h2\");\n"
                + "		break;\n"
                + "	default: \n"
                + "		print(\"def\");\n"
                + "		break;	\n"
                + "}");

        /*   ini.nueva_pestana("Vector = c(12,13,15,16,24,15,17,19,17,15)\n"
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

        /*        ini.nueva_pestana("Lista1 = list(\"hola mundo\");\n"
         + "lista2 = list(42,true, 32.3);\n"
         + "lista3 = c(lista1, lista2);\n"
         + "print(lista3);");

         ini.nueva_pestana("arr = array(c(5, list(7,8,9 , c(1,2))), c(2,3,3))\n"
         + "print(arr);\n"
         + "print(\"--------------------\");\n"
         + "arr[1][1][1] = \"Hola\"; \n"
         + "print(arr[1][1][1]);");*/
        ini.nueva_pestana("print(\"lo que llevo de flex y cup\");\n"
                + "mat = matrix(c(2,3) , 3 , 4);\n"
                + "print(mat);\n"
                + "print(mat[1,]);\n"
                + "print(mat[,1]);\n"
                + "print(mat[1,1]);\n"
                + "print(mat[1]);\n"
                + "arr = array(c(2,3,4,5) , c(2,3,2));\n"
                + "print(arr);\n"
                + "arr[1][1][1] = \"Holaa\";\n"
                + "print(arr[1][1][1]);\n"
                + "f = function(a){\n"
                + "	print(a);\n"
                + "}\n"
                + "x = 2; \n"
                + "f(x);\n"
                + "f2 = function(){\n"
                + "	print(\"no te saludare dos veces\");\n"
                + "	if(2 == 2){\n"
                + "		return(\"hola\"); 	\n"
                + "	}	\n"
                + "	print(\"hola\");\n"
                + "}\n"
                + "f2();\n"
                + "while(true){\n"
                + "	x = x +1;\n"
                + "	if(x == 4){\n"
                + "		continue;\n"
                + "	}\n"
                + "	if(x == 10){\n"
                + "		break;	\n"
                + "	}\n"
                + "	print(x);\n"
                + "}\n"
                + "print(\"VEC\");\n"
                + "st = \"[\";\n"
                + "vec = c(1,2,3,4,5,6);\n"
                + "for( i in vec){\n"
                + "	st = st + i; \n"
                + "	if(i == 6){\n"
                + "		continue;	\n"
                + "	}\n"
                + "	st = st + \", \";\n"
                + "}\n"
                + "print(st + \"]\");");

        /*  ini.nueva_pestana("a = 4; \n"
         + "hola = function(a){\n"
         + "	print(a);	\n"
         + "}\n"
         + "\n"
         + "hola(2);\n"
         + "print(a);");
         */
        ini.nueva_pestana("print(c(true, 1 , 2.5 ,\"hola\"));\n"
                + "for(i in c(true, 1 , 2.5 ,\"hola\")){\n"
                + "	print(typeof(i) + \" \" + i);\n"
                + "}\n"
                + "print(list(true, 1 , 2.5 ,\"hola\"));\n"
                + "for(i in list(true, 1 , 2.5 , \"hola\")){\n"
                + "	print(typeof(i) + \" \" + i);\n"
                + "}\n"
                + "print(matrix(c(true, 2 , 3.4) , 4 , 3));\n"
                + "for(i in matrix(c(true, 2 , 3.4) , 3 , 3)){\n"
                + "	print(typeof(i) + \" \" + i); 	\n"
                + "}\n"
                + "print(array(c(2,3,4) , c(2,3,4)));\n"
                + "for(i in array(c(2,3,4) , c(2,3,4))){\n"
                + "	print(typeof(i) + \" \" + i);	\n"
                + "}");

        ini.nueva_pestana("v = c(1,1 , 1 , 1 , 1 );  \n"
                + "c = 1; \n"
                + "for( a in v){\n"
                + "	a = c;\n"
                + "	c = c +1; 	\n"
                + "}\n"
                + "\n"
                + "print(v); ");
        /*
         ini.nueva_pestana("mat = matrix(2 , 2 , 2); \n"
         + "\n"
         + "\n"
         + "mat[1,1] = \"hola\"; \n"
         + "print(mat);");*/
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

        this.toPruebas.add("");

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
        /*     Tabla_Sim ts = new Tabla_Sim("Global");
         Auxiliar aux = new Auxiliar(new TextArea(), new TextArea(), ts, true);
         for (Nodo n : arr) {
         n.ejecutar(ts, aux);
         }*/
    }

    void probarFlexYCup(String stPrueba, int n) {

        Lexer lexer = new Lexer(new BufferedReader(new StringReader(stPrueba)));
        Sint s = new Sint(lexer);

        try {
            s.parse();

            ArrayList arr = s.miarr;
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
