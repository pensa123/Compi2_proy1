/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Array extends Estructura {
    
    public ArrayList<Object> arr = new ArrayList<>();
    public ArrayList<Integer> arrD = new ArrayList<>();
    public int cantidad = 0;
    
    @Override
    public Estructura copear() {
        Array arrA = new Array();
        arrA.cantidad = cantidad;
        arrA.arrD = this.copyArr(arrD);
        for (Object o : arr) {
            if (o instanceof Estructura) {
                arrA.arr.add(((Estructura) o).copear());
            } else if (o instanceof Simbolo_prim) {
                arrA.arr.add(((Simbolo_prim) o).copear());
            }
        }
        return arrA;
    }
    
    private Array() {
        
    }
    
    public Object obtener(ArrayList<Integer> arri) {
        int n = this.mapeoLexico(arri);
        return n == -1 ? null : arr.get(n);        
    }
    
    public Array(ArrayList<Object> arro, ArrayList<Integer> arri) {
        setPrim(arro, arri);
    }
    
    public boolean verif() {
        int c = 1;
        if (arrD == null || arrD.size() == 0) {
            return false;
        }
        for (Integer i : arrD) {
            if (i < 1) {
                return false;
            }
            c *= i;
        }
        cantidad = c;
        return true;
    }
    
    public ArrayList<Integer> copyArr(ArrayList<Integer> arri) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (Integer i : arri) {
            arr.add(i);
        }
        return arr;
    }
    
    public ArrayList<Integer> concatArrayAtras(ArrayList<Integer> arri, int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(n);
        for (Integer i : arri) {
            arr.add(i);
        }
        return arr;
    }
    
    public void setPrim(ArrayList<Object> arro, ArrayList<Integer> arri) {
        arrD = copyArr(arri);
        if (!verif() || arro == null || arro.size() == 0) {
            System.out.println("mal mal muy mal");
            return;
        }
        int c = 0;
        for (int a = 0; a < cantidad; a++) {
            arr.add(arro.get(c++));
            if (c == arro.size()) {
                c = 0;
            }
        }
    }
    
    public void imp() {
        System.out.println("imprimiendo ando.");
        System.out.println(arrD);
        System.out.println("imp_inv");
        imp_inv(arrD.size() - 1, "", 0);
        System.out.println("normalito");
        
        imp(0, "", 0, new ArrayList<>());
        
    }
    
    public void imp_inv(int n, String st, int n2) {
        if (n == -1) {
            System.out.println(st + " " + n2);
            return;
        }
        if (n != arrD.size() - 1) {
            n2 = n2 * (arrD.get(n));
        }
        for (int a = 1; a <= arrD.get(n); a++) {
            imp_inv(n - 1, "[" + (a) + "]" + st, n2 + a - 1);
        }
    }
    
    public int mapeoLexico(ArrayList<Integer> arrI) {
        if (arrI.size() != arrD.size()) {
            return -1;
        }
        
        int c = 0;
        
        for (int a = arrI.size() - 1; a != -1; a--) {
            if (a != arrD.size() - 1) {
                c = c * (arrD.get(a));
            }
            if (0 < arrI.get(a) && arrI.get(a) <= arrD.get(a)) {
                c += (arrI.get(a) - 1);
            } else {
                return -1;
            }
        }
        return c;
    }
    
    public void imp(int n, String st, int n2, ArrayList<Integer> arri) {
        
        if (!(n < arrD.size())) {
            //System.out.println(st + " " + n2);
            int aux = mapeoLexico(arri);
            System.out.println(arri + "   " + aux + " " + arr.get(aux).toString());
            return;
        }
        
        ArrayList<Integer> arri2;
        
        for (int a = 1; a <= arrD.get(n); a++) {
            arri2 = this.copyArr(arri);
            arri2.add(a);
            imp(n + 1, st + "[" + (a) + "]", n2, arri2);
        }
    }
    
    public String ayudamdd(ArrayList<Integer> arri, int n, String st) {
        if (n == 1) {
            if (!st.equals("")) {
                return "," + st + "\n" + comoMatriz(this.copyArr(arri));
            }
            return comoMatriz(this.copyArr(arri));
        }
        String st2 = "";
        for (int a = 0; a < arrD.get(n); a++) {
            ArrayList<Integer> arri2 = this.concatArrayAtras(arri, 1 + a);
            st2 += ayudamdd(arri2, n - 1, "," + (a + 1) + st);
        }
        
        return st2;
    }
    
    public String comoMatriz(ArrayList<Integer> arri) {
        
        int columnas = arrD.get(1);
        int filas = arrD.get(0);
        
        String st = "", esp = "";
        int n = 4 + 2;
        for (int a = 0; a < 4; a++) {
            st += " ";
        }
        for (int a = 0; a < columnas; a++) {
            st += "[," + (a + 1) + "] ";
        }
        st += "\n";
        for (int i = 0; i < filas; i++) {
            st += "[" + (i + 1) + ",]  ";
            for (int j = 0; j < columnas; j++) {
                ArrayList<Integer> arri2 = this.copyArr(arri);
                arri2 = this.concatArrayAtras(arri2, j + 1);
                arri2 = this.concatArrayAtras(arri2, i + 1);
                int indice = this.mapeoLexico(arri2);
                //               System.out.println(indice);
                st += impesp(arr.get(indice).toString(), n + 2);
            }
            st += "\n";
        }
        return st;
    }
    
    public String impesp(String s, int n) {
        
        for (int a = 0; a < n - s.length(); a++) {
            s += " ";
        }
        return s;
    }
    
    @Override
    public String toString() {
        String st = "";
        if (arrD.size() == 1) {
            st = "[";
            boolean nvez = false;
            for (Object o : arr) {
                if (nvez) {
                    st += ", ";
                }
                st += o.toString();
                nvez = true;
            }
            return st + "]";
        }
        return ayudamdd(new ArrayList<>(), this.arrD.size() - 1, "");
    }
    
}
