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

    public Auxiliar aux;

    public Estructura(Auxiliar au) {
        aux = au;
    }
    public contenedorEnum.Tipos tp = contenedorEnum.Tipos.nulo;


    public Estructura copear() {
        return null;
    }

    public int size() {
        return 0;
    }
}
