package pl.sitpres4.demo.Counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Counters.Counters;
import pl.sitpres4.demo.DemoApplication;

@RestController
public class CounterController {
    @GetMapping("/counters/{number}")
    public Counter counter(@PathVariable("number") String number) {
        if (DemoApplication.counters == null) {
            DemoApplication.counters = new Counters();
        }
        return DemoApplication.counters.getCounter(Integer.parseInt(number));
    }
}
