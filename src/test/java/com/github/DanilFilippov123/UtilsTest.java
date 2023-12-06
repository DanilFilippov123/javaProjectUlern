package com.github.DanilFilippov123;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.github.DanilFilippov123.Main.prepareSportObjects;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void prepareSportObjectsTest() {
        List<SportObject> sportObjects = new ArrayList<>();
        sportObjects.add(new SportObject(1,"2","Москва","2", LocalDate.now()));
        sportObjects.add(new SportObject(1,"2","Московская область","2", LocalDate.now()));
        sportObjects.add(new SportObject(1,"2","г. Москва","2", LocalDate.now()));

        var r = prepareSportObjects(sportObjects);

        for(SportObject s : r){
            assertEquals("Москва и область", s.subject);
        }
    }
}