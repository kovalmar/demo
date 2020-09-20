package pl.sitpres4.demo.CounterFiles;

import lombok.Data;
import pl.sitpres4.demo.Counter.Counter;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.util.ArrayList;
import java.util.List;

@Data
public class CounterFiles {
    Counter counter;
    List<CounterFile> files;

    public CounterFiles(Counter counter) {
        this.counter = counter;
        if (DataFromFTP.getInstance().getFileNames(counter).isEmpty())
            this.files = null;
        else {
            this.files = new ArrayList<>();
            for (String fileName : DataFromFTP.getInstance().getFileNames(counter)) {
                this.files.add(new CounterFile(this.counter, fileName));
            }
        }
    }
}
