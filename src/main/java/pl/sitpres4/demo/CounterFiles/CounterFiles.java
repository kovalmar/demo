package pl.sitpres4.demo.CounterFiles;

import lombok.Data;
import pl.sitpres4.demo.Counter.Counter;
import pl.sitpres4.demo.Data.DataFromFTP;
import pl.sitpres4.demo.Data.FtpSaia;

import java.util.List;

@Data
public class CounterFiles {
    Counter counter;
    List<String> fileNames;

    public CounterFiles(Counter counter) {
        this.counter = counter;
        this.fileNames = DataFromFTP.getInstance().getFileNames(counter);
    }
}
