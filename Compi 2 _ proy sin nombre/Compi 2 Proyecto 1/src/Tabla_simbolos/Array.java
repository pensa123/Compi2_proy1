/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum;
import ClasesAuxiliares.contenedorEnum.Tipos;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class Array extends Estructura {

    public ArrayList<Object> arr = new ArrayList<>();
    public ArrayList<Integer> arrD = new ArrayList<>();
    public int cantidad = 0;
    public boolean tienelista = false;

    @Override
    public int size() {
        return arr.size();
    }

    @Override
    public Estructura copear() {
        Array arrA = new Array(aux);
        arrA.tp = tp;
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

    private Array(Auxiliar au) {
        super(au);
    }

    public void update(int n, Object o) {

        if (0 <= n && n < arr.size()) {
            actualizar(n, o);
        }
    }

    public void Update(ArrayList<Integer> arri, Object o) {
        int n = this.mapeoLexico(arri);
        if (n == -1) {
            return;
        }
        actualizar(n, o);
    }

    public void actualizar(int n, Object o) {
        if (o instanceof Simbolo_prim) {
            o = new Vector((Simbolo_prim) o, aux);
        }
        if (o instanceof Estructura) {
            if (o instanceof Array || o instanceof Matriz) {
                System.out.println("se deberia de reportar un error.");
                return;
            }
            o = ((Estructura) o).copear();

            if (((Estructura) o).size() != 1) {
                System.out.println("debe de retornar un error ya que solo se pueden meter estructuras con una sola posicion");
            }

            if (this.tienelista) {
                if (o instanceof Lista) {
                    arr.set(n, o);
                } else {
                    Lista lst = new Lista(aux);
                    lst.agregar(o);
                    arr.set(n, lst);
                }
            } else {
                if (o instanceof Lista) {
                    pasarDatosALista();
                    arr.set(n, o);
                } else {
                    arr.set(n, o);
                    if (((Vector) o).tp != tp) {
                        casteo(((Vector) o).tp);
                    }
                }
            }

        }
    }

    public void casteo(Tipos aCast) {
        int n = (tp.compareTo(aCast) < 0) ? parse(aCast, tp) : parse(tp, aCast);
    }

    public int parse(Tipos pasarDe, Tipos pasarA) {
        tp = pasarA;
        System.out.println("pasar De " + pasarDe + "    a   " + pasarA);
        for (int a = 0; a < arr.size(); a++) {
            arr.set(a, parseando_ando(arr.get(a), pasarDe, pasarA));
        }
        return 1;
    }

    public Object parseando_ando(Object o, Tipos de, Tipos hacia) {
        if (((Estructura) o).tp == hacia) {
            return o;
        }
        System.out.println(de + "  ->  " + hacia);
        Simbolo_prim s = ((Vector) o).arr.get(0);
        s.tp = hacia;

        if (hacia == Tipos.cadena) {
            return new Vector(s, aux);
        }

        if (de == Tipos.booleano) {
            s.valor = (boolean) s.valor ? 1 : 0;
        }

        if (de == Tipos.nulo) {
            s.valor = s.getDef(hacia);
        }
        return new Vector(s, aux);
    }

    public void pasarDatosALista() {
        int c = 0;
        for (Object o : arr) {
            Lista lst = new Lista(aux);
            lst.agregar(o);
            arr.set(c++, lst);
        }
    }

    public Object obtener(ArrayList<Integer> arri) {
        int n = this.mapeoLexico(arri);
        return n == -1 ? null : arr.get(n);
    }

    public Array(ArrayList<Object> arro, ArrayList<Integer> arri, boolean esLista, Auxiliar au) {
        super(au);
        setPrim(arro, arri, esLista);
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

    public void setPrim(ArrayList<Object> arro, ArrayList<Integer> arri, boolean esLista) {
        tp = Tipos.nulo;
        arrD = copyArr(arri);
        if (!verif() || arro == null || arro.size() == 0) {
            System.out.println("mal mal muy mal");
            return;
        }

        this.tienelista = esLista;

        int c = 0;
        for (int a = 0; a < cantidad; a++) {
            Object oaux = arro.get(c++);
            if (oaux instanceof Simbolo_prim) {
                oaux = new Vector((Simbolo_prim) oaux, aux);
            }

            if (oaux instanceof Lista) {
                this.tienelista = true;
            } else {
                //System.out.println(tp + " " + ((Estructura) oaux).tp + " " + tp.compareTo(((Estructura) oaux).tp));
                if (tp.compareTo(((Estructura) oaux).tp) > 0) {
                    tp = ((Estructura) oaux).tp;
                }
            }

            if (esLista) {
                if (oaux instanceof Vector) {
                    Lista lst = new Lista(aux);
                    for (Simbolo_prim sp : ((Vector) oaux).arr) {
                        lst.agregar(sp);
                    }
                    oaux = lst;
                }
            }

            arr.add(oaux);
            if (c == arro.size()) {
                c = 0;
            }
        }

        if (this.tienelista) {
            System.out.println("pasar todos a lista");
        } else {
            System.out.println("paras todos a " + tp);
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
            c = (a != arrD.size() - 1) ? c * (arrD.get(a)) : c;
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

    /*
        [4,4,4,4]
    
    
        
    
    
    */
    
    
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
                Object o = arr.get(indice);
                String stt = "";
                if (o instanceof Simbolo_prim) {
                    stt = o.toString();
                } else if (o instanceof Vector) {
                    stt = ((Vector) o).arr.size() == 1 ? o.toString() : o.getClass().getSimpleName();
                } else if (o instanceof Lista) {
                    stt = ((Lista) o).arr.size() == 1 ? o.toString() : o.getClass().getSimpleName();
                }
                st += impesp(stt, n + 2);
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
