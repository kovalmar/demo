package pl.sitpres4.demo.Counters;
import pl.sitpres4.demo.Counter.Counter;
import java.util.List;
import lombok.Getter;
import pl.sitpres4.demo.ftpSaia;

public class Counters {
    @Getter
    private List<Counter> counterList;

    public Counters () {
        counterList = ftpSaia.counterListFromSaia();
    }
}
