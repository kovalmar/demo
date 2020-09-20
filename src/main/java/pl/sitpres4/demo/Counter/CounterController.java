package pl.sitpres4.demo.Counter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Counters.Counters;
import pl.sitpres4.demo.Data.DataFromFTP;
import pl.sitpres4.demo.DemoApplication;

@RestController
public class CounterController {
    @GetMapping("/counters/{number}")
    public Counter counter(@PathVariable("number") String number) {
        return DataFromFTP.getInstance().getCountersFromConfig().get(Integer.parseInt(number));
    }
}
