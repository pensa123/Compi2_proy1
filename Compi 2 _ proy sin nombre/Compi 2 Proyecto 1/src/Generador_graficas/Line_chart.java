/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador_graficas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author ferna
 */
public class Line_chart extends JFrame {

    int decision = 0; //0 normal , 1 sin lineas, 2 sin puntos

    ArrayList<Double> arrd = new ArrayList<>();
    String tit, titx, tity;

    public Line_chart(int n, ArrayList<Double> ard, String t, String tx, String ty) {
        decision = n;
        arrd = ard;
        titx = tx;
        tit = t;
        tity = ty;

        initUI();

    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(tit);
        setLocationRelativeTo(null);
    }

    private XYDataset createDataset() {

        XYSeries series = new XYSeries("");

        for (int a = 0; a < arrd.size(); a++) {

            series.add((a + 1), arrd.get(a));
        }

        /*series.add(18, 567);
         series.add(20, 612);
         series.add(25, 800);
         series.add(30, 980);
         series.add(40, 1410);
         series.add(50, 2350);*/
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                tit,
                titx,
                tity,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        if (decision == 0) {
            //renderer.setLinesVisible(false);
            plot.setRenderer(renderer);
        } else if (decision == 1) {
            renderer.setLinesVisible(false);
            plot.setRenderer(renderer);
        } else if (decision == 2) {
            //renderer.setLinesVisible(false);
            //plot.setRenderer(renderer);
        } else {
            plot.setRenderer(renderer);
        }

        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(false);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(tit,
                new Font("Serif", java.awt.Font.BOLD, 18)
        )
        );

        return chart;

    }

}
