package pl.sitpres4.demo.Counters;
import pl.sitpres4.demo.Counter.Counter;
import java.util.List;
import lombok.Getter;
import pl.sitpres4.demo.DemoApplication;
import pl.sitpres4.demo.Data.FtpSaia;

public class Counters {
    @Getter
    private List<Counter> counterList;

    public Counters () {
        if (DemoApplication.counters == null) {
            counterList = FtpSaia.counterListFromSaia();
        }
        else {
            counterList = DemoApplication.counters.getCounterList();
        }
    }

    public Counter getCounter(int index) {
        return counterList.get(index);
    }
}
