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
public class Error {

    public int fila;
    public int columna;
    public String descripcion;

    public Error(int f, int c, String d) {
        fila = f;
        columna = c;
        descripcion = d;
    }

}
