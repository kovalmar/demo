package pl.sitpres4.demo.Counters;
import pl.sitpres4.demo.Counter.Counter;
import java.util.List;
import lombok.Getter;
import pl.sitpres4.demo.Data.DataFromFTP;
import pl.sitpres4.demo.DemoApplication;
import pl.sitpres4.demo.Data.FtpSaia;

public class Counters {
    @Getter
    private List<Counter> counterList;

    public Counters () {
        counterList = DataFromFTP.getInstance().getCountersFromConfig();
    }

    public Counters (boolean actual) {
        counterList = DataFromFTP.getInstance().getCountersFromConfig(actual);
    }

    public Counter getCounter(int index) {
        return counterList.get(index);
    }
}
