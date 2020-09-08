package pl.sitpres4.demo.Counters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountersController {
    @GetMapping("/counters")
    public Counters counters() {
        Counters counters = new Counters();
        return counters;
    }
}
