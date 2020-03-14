/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador_graficas;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ferna
 */
public class Bar_chart extends JFrame {

    public String titulo = "", titx, tity;
    ArrayList<String> arrs;
    ArrayList<Double> arrd;

    public Bar_chart() {
    
    
    }

    public Bar_chart(String title, String tx, String ty, ArrayList<String> ars, ArrayList<Double> ard) {
        titulo = title;
        titx = tx;
        tity = ty;
        arrs = ars;
        arrd = ard;
        initUI();
    }

    private void initUI() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(titulo);
        setLocationRelativeTo(null);
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                titulo, // chart title
                titx, // domain axis label
                tity, // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // the SWTGraphics2D class doesn't handle GradientPaint well, so
        // replace the gradient painter from the default theme with a
        // standard painter...
        renderer.setBarPainter(new StandardBarPainter());

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

    private CategoryDataset createDataset() {
        // row keys...

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int nd = 1;
        for (int a = 0; a < arrd.size(); a++) {
            String s = a < arrs.size() ? arrs.get(a) : "Desconocido " + (nd++);

            dataset.addValue(arrd.get(a), s, "");

        }

        return dataset;
    }

}
