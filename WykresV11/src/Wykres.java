import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

class Wykres extends JFrame {

    private XYLineAndShapeRenderer r1;
    private final XYPlot plot; //przypisz  tylko raz do referencji


    public Wykres(String nazwawykresu, String legenda, String XX, String YY,
                  ArrayList<Double> x, ArrayList<Double> y, int Col, boolean przerywana, boolean aprox) {
        super("Okno Wykresu");
        XYSeries series = new XYSeries(legenda);

        for (int i = 0; i < x.size(); ++i) {
            series.add(x.get(i), y.get(i));
        }

        XYSeriesCollection dane = new XYSeriesCollection(series);
        JFreeChart wykres = ChartFactory.createXYLineChart(
                nazwawykresu,
                XX,
                YY,
                dane,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        plot = wykres.getXYPlot();
        int seriesCount = plot.getSeriesCount();
        for (int i = 0; i < seriesCount; ++i) {
            plot.getRenderer().setSeriesStroke(i, new BasicStroke(1)); //ustawienie grubosci lini
        }

        r1 = new XYLineAndShapeRenderer();
        zmiencolor(Col);
        styllini(przerywana);

        if (Collections.min(y) < 0.0 && Collections.max(y) < 0) {
            plot.getRangeAxis().setRange(Collections.min(y) * 1.05, Collections.max(y) * 0.95);
        } else if (Collections.min(y) < 0.0 && Collections.max(y) > 0.0) {
            plot.getRangeAxis().setRange(Collections.min(y) * 1.05, Collections.max(y) * 1.05);
        } else {
            plot.getRangeAxis().setRange(Collections.min(y) * 0.95, Collections.max(y) * 1.05); //centrowanie osi y
        } //range axis y

        if (aprox) {
            plot.setRenderer(new XYSplineRenderer());
        }

        ustawieniaSiatki(); //ustawia siatke


        ChartPanel nowypanel = new ChartPanel(wykres);
        nowypanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        nowypanel.setBackground(Color.BLACK);
        nowypanel.setPreferredSize(new java.awt.Dimension(800, 400));
        this.add(nowypanel);


    }


    private void zmiencolor(int i) {
        if (i == 0) {
            r1 = new XYLineAndShapeRenderer();
            r1.setSeriesPaint(0, Color.BLACK);
            r1.setSeriesShapesVisible(0, false);
            plot.setRenderer(0, r1);
        }
        if (i == 1) {
            r1 = new XYLineAndShapeRenderer();
            r1.setSeriesPaint(0, Color.GREEN);
            r1.setSeriesShapesVisible(0, false);
            plot.setRenderer(0, r1);
        }
        if (i == 2) {
            r1 = new XYLineAndShapeRenderer();
            r1.setSeriesPaint(0, Color.RED);
            r1.setSeriesShapesVisible(0, false);
            plot.setRenderer(0, r1);
        }
        if (i == 3) {
            r1 = new XYLineAndShapeRenderer();
            r1.setSeriesPaint(0, Color.PINK);
            r1.setSeriesShapesVisible(0, false);
            plot.setRenderer(0, r1);
        }

    }

    public void styllini(boolean i) {
        if (i) {
            plot.getRenderer().setSeriesStroke(0,
                    new BasicStroke(
                            1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                            1.0f, new float[]{6.0f, 6.0f}, 0.0f
                    ));
        }
    }

    public void ustawieniaSiatki() {

        plot.setBackgroundPaint(Color.WHITE);

        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);

        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

    }


}
