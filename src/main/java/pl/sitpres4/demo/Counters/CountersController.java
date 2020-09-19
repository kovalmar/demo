package pl.sitpres4.demo.Counters;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/counters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountersController {
    @GetMapping("")
    public Counters counters() {
        return new Counters();
    }

    @GetMapping("/refresh")
    public Counters countersRefresh() {
        return new Counters(true);
    }

    @GetMapping("/lastupdated")
    public ZonedDateTime countersLastUpdated() {
        return DataFromFTP.getInstance().getLastUpdated(DataFromFTP.COUNTERS_FROM_CONFIG);
    }
}
