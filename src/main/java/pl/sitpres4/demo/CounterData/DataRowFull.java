package pl.sitpres4.demo.CounterData;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.TimeZone;

@Data
public class DataRowFull extends DataRow {
    private Double energy1;
    private Double tariff1;
    private Double energy2;
    private Double tariff2;
    private Double energy3;
    private Double tariff3;
    private Double energy4;
    private Double tariff4;
    private Double val1;
    private Double val2;
    private Double val3;
    private Double val4;
    private Double val5;
    private Double val6;
    private Double val7;
    private Double val8;
    private Double val9;

    DataRowFull(String[] dataFields) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
        formatter.setTimeZone((TimeZone.getTimeZone("UTC")));
        try {
            this.dateTime = formatter.parse(dataFields[0]);
        }
        catch (ParseException e) { };
        this.energy1 = Double.parseDouble(dataFields[1]);
        this.tariff1 = Double.parseDouble(dataFields[2]);
        this.energy2 = Double.parseDouble(dataFields[3]);
        this.tariff2 = Double.parseDouble(dataFields[4]);
        this.energy3 = Double.parseDouble(dataFields[5]);
        this.tariff3 = Double.parseDouble(dataFields[6]);
        this.energy4 = Double.parseDouble(dataFields[7]);
        this.tariff4 = Double.parseDouble(dataFields[8]);
        this.val1 = Double.parseDouble(dataFields[9]);
        this.val2 = Double.parseDouble(dataFields[10]);
        this.val3 = Double.parseDouble(dataFields[11]);
        this.val4 = Double.parseDouble(dataFields[12]);
        this.val5 = Double.parseDouble(dataFields[13]);
        this.val6 = Double.parseDouble(dataFields[14]);
        this.val7 = Double.parseDouble(dataFields[15]);
        this.val8 = Double.parseDouble(dataFields[16]);
        this.val9 = Double.parseDouble(dataFields[17]);
    }
}
