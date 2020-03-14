/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_simbolos;

import ClasesAuxiliares.contenedorEnum;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferna
 */
public abstract class Estructura {

    public contenedorEnum.Tipos tp;

    public String imprimir() {
        return "";
    }

    public Estructura copear() {
        return null;
    }
}
