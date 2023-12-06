package com.github.DanilFilippov123;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main  {

    public static List<SportObject> prepareSportObjects(List<SportObject> sportObjects) {
        return sportObjects.stream()
                .peek(sportObject -> {
                    if(sportObject.subject.equals("Москва") ||
                            sportObject.subject.equals("г. Москва") ||
                            sportObject.subject.equals("Московская область")) {
                        sportObject.subject = "Москва и область";
                    }
                })
                .toList();
    }

    public static Map<String, Long> groupSportObjectsByCityCount(List<SportObject> sportObjects) {
        return sportObjects.stream()
                        .collect(Collectors.groupingBy(sportObject -> sportObject.subject, Collectors.counting()));
    }

    public static void main(String[] args) {
        List<CsvSportObjectBean> csvSportObjectBeanList = CsvUtils.readBeansCsv("s2.csv");
        System.out.print("Считано " + csvSportObjectBeanList.size() + " строк\n");

        SqlLightHandler sqlLightHandler = SqlLightHandler.getInstance();
        System.out.println("Соединение с БД успешно\n");

        List<SportObject> sportObjects = csvSportObjectBeanList.stream().map(SportObject::new).toList();

        List<SportObject> finalSportObjects = new ArrayList<>(sportObjects);
        Thread sqlThread = new Thread(() -> {
            sqlLightHandler.truncateTable();
            sqlLightHandler.insertAllSportObjects(finalSportObjects);
            System.out.println("\n\nСтроки отправленны в базу данных");
        });
        sqlThread.start();

        sportObjects = prepareSportObjects(sportObjects);

        Chart demo = new Chart("Финальный проект по джава", sportObjects);

        demo.pack();
        demo.setVisible(true);

        Map<String, Long> countByCity = groupSportObjectsByCityCount(sportObjects);

        long sum = 0L;

        for(Long count : countByCity.values()) {
            sum += count;
        }

        long mean = sum / countByCity.keySet().size();

        System.out.println("Среднее количество спортивных объектов в регионе: " + mean);

        List<String> sortedCounts = countByCity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(stringLongEntry -> stringLongEntry.getKey() + " - " + stringLongEntry.getValue())
                .toList();

        System.out.printf("Регионы с самым большим количеством спортивных объектов:\n1. %s\n2. %s\n3. %s",
                                                                                    sortedCounts.get(0),
                                                                                    sortedCounts.get(1),
                                                                                    sortedCounts.get(2));


        try {
            sqlThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}