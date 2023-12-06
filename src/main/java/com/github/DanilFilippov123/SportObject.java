package com.github.DanilFilippov123;

import java.time.LocalDate;
import java.util.Objects;

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
        this.name = objectBean.name.strip();
        this.subject = objectBean.subject.strip();
        this.address = objectBean.address.strip();
        this.date = LocalDate.parse(objectBean.date.strip(), CsvUtils.csvDateFormat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportObject that = (SportObject) o;
        return id == that.id &&
               Objects.equals(name, that.name) &&
               Objects.equals(subject, that.subject) &&
               Objects.equals(address, that.address) &&
               Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subject, address, date);
    }
}
