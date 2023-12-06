package com.github.DanilFilippov123;

import java.util.List;

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

    public static void main(String[] args) {
        List<CsvSportObjectBean> csvSportObjectBeanList = CsvUtils.readBeansCsv("s2.csv");
        System.out.print("Считано " + csvSportObjectBeanList.size() + " строк\n");

        SqlLightHandler sqlLightHandler = SqlLightHandler.getInstance();
        System.out.println("Соединение с БД успешно\n");

        List<SportObject> sportObjects = csvSportObjectBeanList.stream().map(SportObject::new).toList();

        sqlLightHandler.truncateTable();
        sqlLightHandler.insertAllSportObjects(sportObjects);
        System.out.println("Строки отправленны в базу данных");

        sportObjects = prepareSportObjects(sportObjects);

        Chart demo = new Chart("Финальный проект по джава", sportObjects);

        demo.pack();
        demo.setVisible(true);


    }
}