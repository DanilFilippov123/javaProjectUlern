package com.github.DanilFilippov123;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

public class Main  {

    public static void main(String[] args) {
        List<CsvSportObjectBean> csvSportObjectBeanList = CsvUtils.readBeansCsv("s2.csv");
        System.out.print("Считано " + csvSportObjectBeanList.size() + " строк\n");

        SqlLightHandler sqlLightHandler = SqlLightHandler.getInstance();
        System.out.println("Соединение с БД успешно\n");

        List<SportObject> sportObjects = csvSportObjectBeanList.stream().map(SportObject::new).toList();

        sqlLightHandler.truncateTable();
        sqlLightHandler.insertAllSportObjects(sportObjects);
        System.out.println("Строки отправленны в базу данных");

        Chart demo = new Chart("Финальный проект по джава", sportObjects);

        demo.pack();
        demo.setVisible(true);
    }
}