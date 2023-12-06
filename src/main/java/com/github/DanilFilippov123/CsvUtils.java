package com.github.DanilFilippov123;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("UnusedAssignment")
public class CsvUtils {

    public static final DateTimeFormatter csvDateFormat = DateTimeFormatter.ofPattern("d.M.yyyy");

    public static Path resourceFileNameToPath(String filename) throws URISyntaxException {
        return Paths.get(
                ClassLoader.getSystemResource(filename).toURI());
    }

    public static List<CsvSportObjectBean> readBeansCsv(String fileName) {
        Path filePath = null;
        try {
            filePath = resourceFileNameToPath(fileName);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try (Reader reader = Files.newBufferedReader(filePath, Charset.forName("CP1251"))) {
            CsvToBean<CsvSportObjectBean> cb = new CsvToBeanBuilder<CsvSportObjectBean>(reader)
                    .withType(CsvSportObjectBean.class)
                    .build();
            return cb.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String[]> readAllLinesFromResources(String fileName) {
        Path path = null;
        try {
            path = resourceFileNameToPath(fileName);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return CsvUtils.readAllLines(path, "CP1251");
    }
    public static List<String[]> readAllLines(Path filePath, String charSet) {
        try (Reader reader = Files.newBufferedReader(filePath, Charset.forName(charSet))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
