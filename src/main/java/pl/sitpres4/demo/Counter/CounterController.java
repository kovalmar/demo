package pl.sitpres4.demo.Counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sitpres4.demo.ftpSaia;

@RestController
public class CounterController {
    @GetMapping("/counter")
    public Counter counter(@RequestParam(value = "number", defaultValue = "0") String number) {
        Counter cnt = ftpSaia.counterListFromSaia().get(Integer.parseInt(number));
        return cnt;
    }
}
