package interfaz;

import ClasesAuxiliares.Dibujador;
import ClasesAuxiliares.Nodo;
import GramaticaJavaCC.Gramatica;
import GramaticaJavaCC.ParseException;
import GramaticaJavaCC.TokenMgrError;
import Tabla_simbolos.Auxiliar;
import Tabla_simbolos.Tabla_Sim;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Inicio extends javax.swing.JFrame {

    public static void main(String args[]) {
        Inicio ini = new Inicio();
        ini.setVisible(true);
    }

    public static LinkedList<String> lisErrores = new LinkedList<String>();
    public static String text = "";

    LinkedList<JButton> listabtn;
    int x, y;
    boolean bandera;
    int indicePestañas = 0;
    public static DefaultTableModel modelo;

    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("EXTREME EDITOR");

        listabtn = new LinkedList<JButton>();
        x = 40;
        y = 10;
        bandera = true;
        this.jTabbedPane1.setOpaque(false);

        modelo = new DefaultTableModel();

        modelo.addColumn("Tipo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fila");
        modelo.addColumn("Columna");

    }

    public static void ParsearXml(String path) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        txtConsola = new java.awt.TextArea();
        txtError = new java.awt.TextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(252, 252, 252));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setForeground(new java.awt.Color(255, 245, 245));

        jButton1.setText("Ejecutar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        txtConsola.setBackground(new java.awt.Color(0, 0, 0));
        txtConsola.setForeground(new java.awt.Color(25, 255, 0));

        txtError.setBackground(new java.awt.Color(0, 0, 0));
        txtError.setForeground(new java.awt.Color(204, 0, 0));

        jMenu2.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrir(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Guardar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Guardar Como");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pestañas");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Nuevo documento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizar(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Cerrar Pestaña");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem6.setText("Ejecutar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtConsola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(4, 4, 4)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirArchivo() {
        String texto = "", aux = "";
        try {
            JFileChooser abridor = new JFileChooser();

            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos GXML o FS", "GXML", "FS");

            abridor.setFileFilter(filtro);
            abridor.showOpenDialog(this);
            File archivo = abridor.getSelectedFile();

            System.out.println(archivo.getName() + "    " + archivo.getName().replaceAll("^.*\\.(.*)$", "$1"));
            if (archivo != null) {

                FileReader lector = new FileReader(archivo);
                BufferedReader buffer = new BufferedReader(lector);
                while ((aux = buffer.readLine()) != null) {
                    texto += aux + "\n";
                }

                CampoTexto cam = (archivo.getName().replaceAll("^.*\\.(.*)$", "$1").equalsIgnoreCase("gxml")) ? new CampoTexto(true) : new CampoTexto(false);
                cam.setTexto(texto);
                cam.setPath(archivo.getAbsoluteFile().toString());

                cam.carpeta = archivo.getParent() + "\\";

                System.out.println(cam.carpeta);
                this.jTabbedPane1.add(archivo.getName(), cam);
                jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 1);

            }
        } catch (Exception e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }

    private void abrir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrir
        abrirArchivo();
    }//GEN-LAST:event_abrir

    private void abrir1(java.awt.event.ActionEvent evt) {

        try {
            File path = new File(evt.getActionCommand());
            Desktop.getDesktop().open(path);

        } catch (Exception e) {

        }
    }

    private void cerrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar
        if (this.jTabbedPane1.getTabCount() > 0) {
            jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());
            this.indicePestañas--;
        }
    }//GEN-LAST:event_cerrar

    private void analizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizar

        nueva_pestana();

    }//GEN-LAST:event_analizar

    public void nueva_pestana(String st) {
        try {
            CampoTexto cam = new CampoTexto(false);
            cam.setTexto(st);
            cam.setPath("");

            this.jTabbedPane1.add("new" + this.indicePestañas, cam);
            this.indicePestañas++;
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 1);

        } catch (Exception e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }

    public void nueva_pestana() {
        try {
            CampoTexto cam = new CampoTexto(false);
            cam.setTexto("");
            cam.setPath("");

            this.jTabbedPane1.add("new" + this.indicePestañas, cam);
            this.indicePestañas++;
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 1);

        } catch (Exception e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            if (this.jTabbedPane1.getTabCount() > 0) {
                CampoTexto area = (CampoTexto) this.jTabbedPane1.getSelectedComponent();
                if (area.getPath().equals("")) {
                    guardarArchivo();

                    return;
                }
                File fichero = new File(area.getPath());
                if (fichero != null) {
                    FileWriter save = new FileWriter(fichero);
                    save.write(area.getTexto());
                    save.close();

                }
            }

        } catch (IOException ex) {
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ejecutar();


    }//GEN-LAST:event_jButton1ActionPerformed
    private void ejecutar() {

        if (this.jTabbedPane1.getTabCount() == 0) {
            return;
        }
        CampoTexto area = (CampoTexto) this.jTabbedPane1.getSelectedComponent();
        String texto = area.getTexto();

        //TODO validar si se ejecuta en javacc o en flex y cup 
        ejecJavacc(texto);

        //System.out.println(texto);

        /*System.out.println("path: " + area.path);
         System.out.println("carpeta: " + area.carpeta); */
    }
    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed

    }//GEN-LAST:event_jButton1KeyPressed


    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ejecutar();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("adios.");
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        System.out.println("hola.");

    }//GEN-LAST:event_formWindowOpened

    public void guardarArchivo() {
        try {
            if (this.jTabbedPane1.getTabCount() == 0) {
                return;
            }
            fileCh = new javax.swing.JFileChooser();

            int status = fileCh.showSaveDialog(this);
            ///*****poner el filtor

            File fichero = fileCh.getSelectedFile();

            if (fichero != null && status == 0) {
                CampoTexto area;
                FileWriter save = new FileWriter(fichero);

                if (this.jTabbedPane1.getTabCount() > 0) {
                    area = (CampoTexto) this.jTabbedPane1.getSelectedComponent();
                    save.write(area.getTexto());
                } else {
                    return;
                }

                System.out.println("fichero guardado : " + fichero);

                int jp = this.jTabbedPane1.getSelectedIndex();

                this.jTabbedPane1.setTitleAt(jp, fichero.getName());
                area.carpeta = fichero.getParent() + "\\";

                area.setPath(fichero.getAbsolutePath().toString());
                System.out.println(area.path);
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo  " + fichero + "  se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Su archivo no se ha guardado",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    private javax.swing.JFileChooser fileCh;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.TextArea txtConsola;
    private java.awt.TextArea txtError;
    // End of variables declaration//GEN-END:variables

    boolean ejecJavacc(String st) {
        try {

            Gramatica parser = new Gramatica(new BufferedReader(new StringReader(st)));
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
            System.out.println("ha surgido un error");
        } catch (TokenMgrError e) {
            // consola.setText(consola.getText() + e.getMessage() + "\n");
            System.err.println(e.getMessage());
            System.out.println("ha surgido un error");
        }

        return true;
    }

    void ejecutarJavacc(ArrayList<Nodo> arr) {
        Tabla_Sim ts = new Tabla_Sim();
        Auxiliar aux = new Auxiliar(txtConsola, txtError, ts);

        txtConsola.setText("");
        txtError.setText("");
        for (Nodo n : arr) {
            n.ejecutar(ts, aux);
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
