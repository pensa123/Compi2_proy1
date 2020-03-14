/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador_graficas;

/**
 *
 * @author ferna
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Pie_chart extends JFrame {

    public Pie_chart(String title, ArrayList<String> arrst, ArrayList<Double> arrd) {
        super(title);

        // Create dataset
        PieDataset dataset = createDataset(arrst, arrd);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private PieDataset createDataset(ArrayList<String> arrst, ArrayList<Double> arrd) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        //dataset.setValue("Hola", 120.5);

        int nd = 1;
        for (int a = 0; a < arrd.size(); a++) {
            String s = a < arrst.size() ? arrst.get(a) : "Desconocido " + (nd++);
            dataset.setValue(s, arrd.get(a));
        }

        return dataset;
    }

}
