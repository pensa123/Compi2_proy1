/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import java.awt.TextArea;

/**
 *
 * @author ferna
 */
public class Auxiliar {

    public TextArea tx;
    public String st = "";

    public void agregar(String ss) {
        st += ss;
        tx.setText(st);
    }
}
