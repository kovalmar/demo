package pl.sitpres4.demo.CounterData;

import lombok.Data;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class CounterData {
    List<DataRowFull> data;

    public CounterData() {}

    public CounterData(String fileName)
    {
        if (!DataFromFTP.getInstance().getFileNames(fileName).isEmpty()) {
            data = new ArrayList<>();
            setData(DataFromFTP.getInstance().getDataFromFile(fileName));
        }
    }

    private boolean energyCounter(String headerLine) {
        return headerLine.equals("DateTime;Energy1;Tariff1;Energy2;Tariff2;Energy3;Tariff3;Energy4;Tariff4;" +
                "Val1;Val2;Val3;Val4;Val5;Val6;Val7;Val8;Val9");
    }

    private void setData(List<String> data) {
        if (energyCounter(data.get(0))) {
            data.remove(0);
        }
        for (String dataLine : data) {
            this.data.add(new DataRowFull(dataLine.split(";")));
        }
    }
}
