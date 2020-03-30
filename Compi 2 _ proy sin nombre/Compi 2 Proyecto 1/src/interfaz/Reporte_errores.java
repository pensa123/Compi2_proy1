/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Estructura;
import Tabla_simbolos.MiError;
import Tabla_simbolos.Tabla_Sim;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 *
 * @author ferna
 */
public class Reporte_errores {

    File ff;

    public void generarPdf(Auxiliar au) {

        String st;
        st = "<style>\n"
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
                + "<h1 style=\"text-align: center \" >Reporte de errrores.</h1>\n"
                + "<hr/>\n"
                + "\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "  <div class=\"table\">\n"
                + "    <div class=\"table-head\">\n"
                + "      <ul class=\"row\">\n"
                + "        <li class=\"fieldname\" >Tipo</li>\n"
                + "        <li class=\"fieldname\" >Fila</li>\n"
                + "        <li class=\"fieldname\" >Columna</li>\n"
                + "        <li class=\"fieldname\" >Error</li>\n"
                + "      </ul>\n"
                + "    </div>\n"
                + "    <div class=\"table-body\">";

        st += getTablaSimRep(au);

        st += "	 \n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>";

        crearPdf(st);
    }

    private void crearPdf(String texto) {
        String st = new File(".").getAbsolutePath() + "\\reporte_errores.html";

        File f = new File(st);
        ff = f;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(texto);
            bw.flush();
            bw.close();

            // Desktop.getDesktop().open(f);
        } catch (Exception ex) {
            //System.out.println("error 1");
            //System.out.println(ex.toString());
        }
    }

    public void abrirElPdf() {
        String st = new File(".").getAbsolutePath() + "\\reporte_errores.html";

        File f = new File(st);
        ff = f;
        try {
            Desktop.getDesktop().open(f);
        } catch (Exception ex) {

        }
    }

    private String getTablaSimRep(Auxiliar au) {
        String st = "";

        for (MiError mi : au.arrErr) {

            st += "      <ul class=\"row\">\n"
                    + "        <li class=\"data\">" + mi.tipo + "</li>\n"
                    + "        <li class=\"data\">" + mi.fila + "</li>\n"
                    + "        <li class=\"data\">" + mi.columna + "</li>\n"
                    + "        <li class=\"data\">" + mi.descripcion + "</li>\n"
                    + "      </ul>\n";

        }
        return st;
    }

}
