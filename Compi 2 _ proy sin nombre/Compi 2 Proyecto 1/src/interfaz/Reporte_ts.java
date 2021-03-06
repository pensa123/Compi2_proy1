/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Tabla_simbolos.Array;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.Matriz;
import Tabla_simbolos.Tabla_Sim;
import Tabla_simbolos.Vector;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author ferna
 */
public class Reporte_ts {

    File ff;
    BufferedWriter bw;

    public void generarPdf(Tabla_Sim ts, boolean todo) throws IOException {

        String st = new File(".").getAbsolutePath() + "\\ts.html";
        File f = new File(st);
        ff = f;

        bw = new BufferedWriter(new FileWriter(ff));
        //bw.write(texto);

        bw.write("");
        bw.append("<style>\n"
                + "\n"
                + "	*{\n"
                + "  margin:0;\n"
                + "  padding:0;\n"
                + "  box-sizing:border-box;\n"
                + "  list-style:none;\n"
                + "}\n"
                + "\n"
                + ".container{\n"
                + "  height:500px;\n"
                + "  width:800px;\n"
                + "  margin: 0 auto;\n"
                + "  margin-top: 50px;\n"
                + "  border:2px solid transparent;\n"
                + "  box-shadow: 0 0 10px rgba(0,0,0,0.6);\n"
                + "  overflow-y:scroll;\n"
                + "  font-family: 'Roboto', sans-serif;\n"
                + "}\n"
                + "\n"
                + ".table{\n"
                + "  height:100%;\n"
                + "  width:100%;\n"
                + "}\n"
                + "/*--table head--*/\n"
                + ".table .table-head{\n"
                + "  position: fixed;\n"
                + "  z-index:1;\n"
                + "  width: 780;\n"
                + "  background-color: #C4E3EB;\n"
                + "  font-weight: 700;\n"
                + "}\n"
                + ".table .table-head .row{\n"
                + "  display:flex;\n"
                + "  flex-direction: row;\n"
                + "}\n"
                + "/*--table body--*/\n"
                + ".table .table-body .row{\n"
                + "  display: flex;\n"
                + "  flex-direction:row;\n"
                + "  transform:translateY(calc(22px + 24px));\n"
                + "  \n"
                + "}\n"
                + ".table .table-body .row:nth-child(even){\n"
                + "  background-color: #EBF5F8;\n"
                + "}\n"
                + "/*-- others --*/\n"
                + ".fieldname, .data{\n"
                + "  padding: 12px 0 12px 15px;\n"
                + "  flex:1;\n"
                + "}\n"
                + "@media (max-width:580px){\n"
                + "  .container{\n"
                + "  height:350px;\n"
                + "  width:400px;\n"
                + "  margin: 0 auto;\n"
                + "  margin-top: 50px;\n"
                + "  border:2px solid black;\n"
                + "  overflow-y:scroll;\n"
                + "  font-size:0.9em;\n"
                + "}\n"
                + "  .table .table-head .row{\n"
                + "  display:flex;\n"
                + "  flex-direction: row;\n"
                + "}\n"
                + "  .table .table-head{\n"
                + "  position: fixed;\n"
                + "  z-index:1;\n"
                + "  width: 380px;\n"
                + "  background-color: #C4E3EB;\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "@media (max-width:430px){\n"
                + "  .container{\n"
                + "  height:350px;\n"
                + "  width:100%;\n"
                + "  margin-top: 50px;\n"
                + "  overflow-y:scroll;\n"
                + "  font-size:0.9em;\n"
                + "}\n"
                + "  .table .table-head .row{\n"
                + "  display:flex;\n"
                + "  flex-direction: row;\n"
                + "}\n"
                + "  .table .table-head{\n"
                + "  position: fixed;\n"
                + "  z-index:1;\n"
                + "  width: 95%;\n"
                + "  background-color: #C4E3EB;\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "</style>\n"
                + "\n"
                + "\n"
                + "<br/>\n"
                + "<hr/>\n"
                + "<h1 style=\"text-align: center \" >Reporte tabla de simbolos.</h1>\n"
                + "<hr/>\n"
                + "\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "  <div class=\"table\">\n"
                + "    <div class=\"table-head\">\n"
                + "      <ul class=\"row\">\n"
                + "        <li class=\"fieldname\" >Ambito</li>\n"
                + "        <li class=\"fieldname\" >Nombre de variable</li>\n"
                + "        <li class=\"fieldname\" >Tipo</li>\n"
                + "        <li class=\"fieldname\" >Valor</li>\n"
                + "      </ul>\n"
                + "    </div>\n"
                + "    <div class=\"table-body\">");

        getTablaSimRep(ts, todo);

        bw.append("	 \n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>");

        crearPdf(st);
    }

    private void crearPdf(String texto) throws IOException {

        bw.flush();
        bw.close();

        // Desktop.getDesktop().open(f);
    }

    public void abrirElPdf() {
        String st = new File(".").getAbsolutePath() + "\\ts.html";

        File f = new File(st);
        ff = f;
        try {
            Desktop.getDesktop().open(f);
        } catch (Exception ex) {

        }
    }

    private String getTablaSimRep(Tabla_Sim ts, boolean todo) throws IOException {

        for (Map.Entry<String, Estructura> entry : ts.hvar.entrySet()) {
            Estructura est = entry.getValue();

            String tipo = est.getClass().getSimpleName();

            if (est instanceof Vector || est instanceof Matriz) {
                tipo += "=>" + est.tp;
            } else if (est instanceof Array) {
                Array arr = (Array) est;
                if (arr.tienelista) {
                    tipo += "=>Lista";
                } else {
                    tipo += "=>" + est.tp;
                }
            }

            bw.append("      <ul class=\"row\">\n"
                    + "        <li class=\"data\">" + ts.nombre + "</li>\n" //ambito
                    + "        <li class=\"data\">" + entry.getKey() + "</li>\n" //nombre de variable. 
                    + "        <li class=\"data\">" + tipo + "</li>\n" //tipo de la estructura
                    + "        <li class=\"data\">" + est.toString().replace("\n", "<br/>") + "</li>\n" //Valor de la estructura
                    + "      </ul>\n");

        }
        if (todo) {
            for (Tabla_Sim ts2 : ts.hijos) {
                getTablaSimRep(ts2, todo);
            }
        }
        return "";
    }

}
