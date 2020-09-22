package pl.sitpres4.demo.CounterData;

import lombok.Data;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class CounterData {
    int counterType; // 1 - energy counter
    String[] dataHeader;
    List<String[]> data;

    public CounterData() {}

    public CounterData(String fileName)
    {
        if (!DataFromFTP.getInstance().getFileNames(fileName).isEmpty()) {
            data = new ArrayList<>();
            setDataAndHeader(DataFromFTP.getInstance().getDataFromFile(fileName));
        }
    }

    private void setTypeAndHeader(String headerLine) {
        if (energyCounter(headerLine)) {
            setCounterType(1);
        }
        this.dataHeader = headerLine.split(";");
    }

    private boolean energyCounter(String headerLine) {
        return headerLine.equals("DateTime;Energy1;Tariff1;Energy2;Tariff2;Energy3;Tariff3;Energy4;Tariff4;" +
                "Val1;Val2;Val3;Val4;Val5;Val6;Val7;Val8;Val9");
    }

    private void setDataAndHeader(List<String> data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy HH:mm:ss");
        //TODO
        Boolean[] usedField;
        boolean header = true;
        for (String dataLine : data) {
            if (!header) {
                this.data.add(dataLine.split(";"));
            } else {
                this.setTypeAndHeader("DateTime;Energy1;Tariff1;Energy2;Tariff2;Energy3;Tariff3;Energy4;Tariff4;" +
                        "prąd_faza1;moc_czynna_faza1;moc_bierna_faza1;prąd_f2;moc_czynna_f2;moc_bierna_f2;prąd_f3;moc_czynna_f3;moc_bierna_f3");
                header = false;
                //TODO
                usedField = new Boolean[dataHeader.length];
            }
        }
    }
}
