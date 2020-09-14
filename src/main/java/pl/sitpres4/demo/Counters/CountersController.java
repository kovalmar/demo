package pl.sitpres4.demo.Counters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Data.DataFromFTP;
import pl.sitpres4.demo.DemoApplication;

import java.util.Date;

@RestController
public class CountersController {
    @GetMapping("/counters")
    public Counters counters() {
        return new Counters();
    }

    @GetMapping("/counters/refresh")
    public Counters countersRefresh() {
        return new Counters(true);
    }

    @GetMapping("/counters/lastupdated")
    public Date countersLastUpdated() {
        return DataFromFTP.getInstance().getLastUpdated(DataFromFTP.COUNTERS_FROM_CONFIG);
    }
}
