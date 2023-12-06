package com.github.DanilFilippov123;

import java.time.LocalDate;

public class SportObject {
    public int id;
    public String name;
    public String subject;
    public String address;
    public LocalDate date;

    SportObject() {}

    public SportObject(int id, String name, String subject, String address, LocalDate date) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.date = date;
    }

    public SportObject(CsvSportObjectBean objectBean) {
        this.id = objectBean.id;
        this.name = objectBean.name;
        this.subject = objectBean.subject;
        this.address = objectBean.address;
        this.date = LocalDate.parse(objectBean.date, CsvUtils.csvDateFormat);
    }
}
