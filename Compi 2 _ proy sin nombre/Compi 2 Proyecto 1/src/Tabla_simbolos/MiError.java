/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

/**
 *
 * @author ferna
 */
public class MiError {

    public int fila;
    public int columna;
    public String descripcion;
    public String tipo = ""; //0 lexico, 1 sintactico, 2 ejecucion
    
    
    public MiError(int f, int c, String d , String tp) {
        fila = f;
        columna = c;
        descripcion = d;
        tipo = tp; 
    }

}
