package pl.sitpres4.demo.Counters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.DemoApplication;

@RestController
public class CountersController {
    @GetMapping("/counters")
    public Counters counters() {
        if (DemoApplication.counters == null) {
            DemoApplication.counters = new Counters();
        }
        return DemoApplication.counters;
    }
}
