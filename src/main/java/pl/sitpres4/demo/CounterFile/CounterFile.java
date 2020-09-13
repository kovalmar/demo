package pl.sitpres4.demo.CounterFile;

import lombok.Data;
import pl.sitpres4.demo.Data.DataFromFTP;

@Data
public class CounterFile {
//TODO
    String fileName;
    boolean exists;

    public CounterFile(String name) {
        setFileName(name);
        if (DataFromFTP.getInstance().getFileNames(name).isEmpty())
            setExists(false);
        else
            setExists(true);
    }
}
