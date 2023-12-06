package com.github.DanilFilippov123;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Chart extends ApplicationFrame {
    public Chart(String title, List<SportObject> sportObjects) {
        super(title);
        DefaultCategoryDataset histogramDataset = toHistogramDataset(sportObjects);
        JFreeChart histogram = createChart(histogramDataset);

        histogram.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(histogram);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(1000, 700));

        setContentPane(panel);
    }

    public DefaultCategoryDataset toHistogramDataset(List<SportObject> sportObjects) {
        var countByCity = sportObjects.stream()
                .collect(Collectors.groupingBy(sportObject -> sportObject.subject, Collectors.counting()));
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String city : countByCity.keySet()) {
            dataset.addValue(countByCity.get(city), city, "Область");
        }
        return dataset;
    }



    public JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество спортивных объектов по облостям",
                "Область",                   // x-axis label
                "Количестов спортивных лбъектов",                // y-axis label
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

}
