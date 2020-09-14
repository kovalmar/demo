package pl.sitpres4.demo.Data;

import org.apache.commons.collections.CollectionUtils;
import pl.sitpres4.demo.Counter.Counter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFromFTP {
    private static final DataFromFTP instance = new DataFromFTP();
    private List<Counter> countersFromConfig;
    private Date countersFromConfigLastUpdated;
    private List<String> fileNames;
    private Date fileNamesLastUpdated;
    public final static String COUNTERS_FROM_CONFIG = "countersFromConfig";
    public final static String FILE_NAMES = "fileNames";

    //private constructor to avoid client applications to use constructor
    private DataFromFTP(){}

    public static DataFromFTP getInstance(){
        return instance;
    }

    public Date getLastUpdated(String dataName) {
        switch (dataName) {
            case COUNTERS_FROM_CONFIG:
                return countersFromConfigLastUpdated;
            case FILE_NAMES:
                return fileNamesLastUpdated;
            default:
                return null;
        }
    }

    private void setCountersFromConfig() {
        countersFromConfig = FtpSaia.counterListFromSaia();
        countersFromConfigLastUpdated = new Date(System.currentTimeMillis());
    }

    private void setFileNames() {
        fileNames = FtpSaia.getFileNames("CNT");
        fileNamesLastUpdated = new Date(System.currentTimeMillis());
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
        return getFileNames(counter.getFtpFileNameMask(), actual);
    }

    public List<String> getFileNames(String fileMask) {
        return getFileNames(fileMask,false);
    }

    public List<String> getFileNames(String fileMask, boolean actual) {
        if (fileNames == null || actual) {
            setFileNames();
        }
        ArrayList<String> output = new ArrayList<>(fileNames);
        CollectionUtils.filter(output, o -> ((String) o).contains(fileMask));
        return output;
    }
}
