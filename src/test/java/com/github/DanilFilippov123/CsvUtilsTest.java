package com.github.DanilFilippov123;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvUtilsTest {

    @Test
    void readAllLinesFromResources() {
        var csv = CsvUtils.readAllLinesFromResources("w.csv");
        for(var line : csv) {
            System.out.print("\n"+Arrays.toString(line));
        }
    }

    @Test
    void readAllLines() {

    }

    @Test
    void readBeansCsv() {
        List<CsvSportObjectBean> rows = CsvUtils.readBeansCsv("w.csv");
        CsvSportObjectBean test1 = new CsvSportObjectBean(1,
                "ТЮЗ там пятое 'десятое'",
                "Москва",
                " там-то там-то",
                " 28.02.2004"
        );
        CsvSportObjectBean test2 = new CsvSportObjectBean(2,
                " ДОВЬЫф фвц ф2у",
                " Чита",
                " здесь",
                " 7.2.2005"
        );

        assertEquals(test1, rows.get(0));
        assertEquals(test2, rows.get(1));
    }
}