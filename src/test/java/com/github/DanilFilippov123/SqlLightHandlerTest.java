package com.github.DanilFilippov123;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlLightHandlerTest {

    @Test
    void insertAndSelectAllSportObjects() {
        List<CsvSportObjectBean> csvSportObjectBeanList = CsvUtils.readBeansCsv("w.csv");
        SqlLightHandler sqlLightHandler = SqlLightHandler.getInstance();
        List<SportObject> sportObjects = csvSportObjectBeanList.stream().map(SportObject::new).toList();
        sqlLightHandler.truncateTable();
        sqlLightHandler.insertAllSportObjects(sportObjects);

        List<SportObject> insertedSportObjects = sqlLightHandler.selectAllSportObjects();
        assertTrue(sportObjects.containsAll(insertedSportObjects));
    }

    @Test
    void truncateTable() {
        SqlLightHandler sqlLightHandler = SqlLightHandler.getInstance();
        sqlLightHandler.truncateTable();
        assertTrue(sqlLightHandler.selectAllSportObjects().isEmpty());
    }
}