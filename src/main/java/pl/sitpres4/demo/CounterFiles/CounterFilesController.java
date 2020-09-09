package pl.sitpres4.demo.CounterFiles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.CounterFiles.CounterFiles;
import pl.sitpres4.demo.Counters.Counters;
import pl.sitpres4.demo.DemoApplication;

@RestController
public class CounterFilesController {
    @GetMapping("/counters/{number}/filenames")
    public CounterFiles counterFiles(@PathVariable("number") String number) {
        if (DemoApplication.counters == null) {
            DemoApplication.counters = new Counters();
        }
        return new CounterFiles(DemoApplication.counters.getCounter(Integer.parseInt(number)));
    }
}
