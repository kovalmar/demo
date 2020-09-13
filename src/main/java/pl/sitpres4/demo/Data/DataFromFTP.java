package pl.sitpres4.demo.Data;

import org.apache.commons.collections.CollectionUtils;
import pl.sitpres4.demo.Counter.Counter;

import java.util.ArrayList;
import java.util.List;

public class DataFromFTP {
    private static final DataFromFTP instance = new DataFromFTP();
    private List<Counter> countersFromConfig;
    private List<String> fileNames;

    //private constructor to avoid client applications to use constructor
    private DataFromFTP(){}

    public static DataFromFTP getInstance(){
        return instance;
    }

    private void setCountersFromConfig() {
        countersFromConfig = FtpSaia.counterListFromSaia();
    }

    private void setFileNames() {
        fileNames = FtpSaia.getFileNames("CNT");
    }

    public List<Counter> getCountersFromConfig() {
        return getCountersFromConfig(false);
    }

    public List<Counter> getCountersFromConfig(boolean actual) {
        if (countersFromConfig == null || actual) {
            setCountersFromConfig();
        }
        return countersFromConfig;
    }

    public List<String> getFileNames() {
        return getFileNames(false);
    }

    public List<String> getFileNames(boolean actual) {
        if (fileNames == null || actual) {
            setFileNames();
        }
        return fileNames;
    }

    public List<String> getFileNames(Counter counter) {
        return getFileNames(counter,false);
    }

    public List<String> getFileNames(Counter counter, boolean actual) {
        if (fileNames == null || actual) {
            setFileNames();
        }
        ArrayList<String> output = new ArrayList<>(fileNames);
        CollectionUtils.filter(output, o -> ((String) o).contains(counter.getFtpFileNameMask()));
        return output;
    }
}
