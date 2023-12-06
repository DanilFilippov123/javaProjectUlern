package com.github.DanilFilippov123;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class CsvSportObjectBean {
    @CsvBindByName(column = "Номер")
    public int id;
    @CsvBindByName(column = "Наименование")
    public String name;
    @CsvBindByName(column = "Субъект РФ")
    public String subject;
    @CsvBindByName(column = "Полный адрес")
    public String address;
    @CsvBindByName(column = "Дата занесения в реестр")
    public String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvSportObjectBean that = (CsvSportObjectBean) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(subject, that.subject) && Objects.equals(address, that.address) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subject, address, date);
    }

    @Override
    public String toString() {
        return "CsvSportObjectBean{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", subject='" + subject + '\'' +
               ", address='" + address + '\'' +
               ", date=" + date +
               '}';
    }

    public CsvSportObjectBean(int id, String name, String subject, String address, String date) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.date = date;
    }

    public CsvSportObjectBean() {
    }
}
