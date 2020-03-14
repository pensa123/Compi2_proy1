/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador_graficas;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author ferna
 */

public class Histogram_chart extends JFrame {

    String title, titlex;
    ArrayList<Double> arrd;

    public Histogram_chart(String t, String tx, ArrayList<Double> ard) {
        arrd = ard;
        title = t;
        titlex = tx;
        initUI();
    }

    private void initUI() {
        IntervalXYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(title);
        setLocationRelativeTo(null);
    }

    private IntervalXYDataset createDataset() {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);

        double[] data = new double[arrd.size()];

        double mayor = 0;
        double menor = 0;
        for (int a = 0; a < arrd.size(); a++) {
            if (a == 0) {
                mayor = arrd.get(a);
                menor = arrd.get(a);
            }
            data[a] = arrd.get(a);
            if (mayor < arrd.get(a)) {
                mayor = arrd.get(a);
            }
            if (menor > arrd.get(a)) {
                menor = arrd.get(a);
            }
        }

        dataset.addSeries(titlex, data, data.length, (menor - 1), (mayor + 1));
        return dataset;
    }

    private JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                title,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.75f);
        NumberAxis axis = (NumberAxis) plot.getDomainAxis();
        axis.setAutoRangeIncludesZero(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());

        return chart;
    }

}
