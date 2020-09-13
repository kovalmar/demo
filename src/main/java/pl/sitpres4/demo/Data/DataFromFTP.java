package pl.sitpres4.demo.Data;

import pl.sitpres4.demo.Counter.Counter;
import java.util.List;

public class DataFromFTP {
    private static final DataFromFTP instance = new DataFromFTP();
    private List<Counter> configCounters;
    private List<String> fileNameList;

    //private constructor to avoid client applications to use constructor
    private DataFromFTP(){}

    public static DataFromFTP getInstance(){
        return instance;
    }

    private void setConfigCounters() {
        configCounters = FtpSaia.counterListFromSaia();
    }

    private void setFileNameList() {
        fileNameList = FtpSaia.getFileNameList("");
    }

    public List<Counter> getConfigCounters() {
        return getConfigCounters(false);
    }

    public List<Counter> getConfigCounters(boolean actual) {
        if (configCounters == null || actual) {
            setConfigCounters();
        }
        return configCounters;
    }

    public List<String> getFileList() {
        return getFileList(false);
    }

    public List<String> getFileList(boolean actual) {
        if (fileNameList == null || actual) {
            setFileNameList();
        }
        return fileNameList;
    }
}
