package pl.sitpres4.demo.CounterData;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class DataRowEnergy extends DataRow {
    private Double energy1;

    public DataRowEnergy(String dataLine) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy HH:mm:ss");
        String[] fields = dataLine.split(";");
        for (int fieldIndex = 0; fieldIndex < 2; fieldIndex++) {
            switch (fieldIndex) {
                case 0:
                    this.setDateTime(LocalDate.parse(fields[fieldIndex],formatter));
                    break;
                case 1:
                    this.energy1 = Double.parseDouble(fields[fieldIndex]);
                    break;
            }
        }
    }

    public static boolean energyCounter(List<String> dataFromFile) {
        if (dataFromFile.get(0).equals("DateTime;Energy1;Tariff1;Energy2;Tariff2;Energy3;Tariff3;Energy4;Tariff4;" +
                "Val1;Val2;Val3;Val4;Val5;Val6;Val7;Val8;Val9"))
            return true;
        else
            return false;
    }
}
