package pl.sitpres4.demo.CounterData;

import lombok.Data;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.util.List;

@Data
public class CounterData {
    List<DataRowEnergy> data;

    public CounterData() {};

    public CounterData(String fileName)
    {
        if (!DataFromFTP.getInstance().getFileNames(fileName).isEmpty()) {
            this.data = DataFromFTP.getInstance().getDataFromFile(fileName);
        }
    }
}
