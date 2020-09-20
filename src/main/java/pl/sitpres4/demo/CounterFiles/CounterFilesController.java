package pl.sitpres4.demo.CounterFiles;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.CounterFiles.CounterFiles;
import pl.sitpres4.demo.Counters.Counters;
import pl.sitpres4.demo.Data.DataFromFTP;
import pl.sitpres4.demo.DemoApplication;

import javax.xml.crypto.Data;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CounterFilesController {
    @GetMapping("/counters/{number}/files")
    public CounterFiles counterFiles(@PathVariable("number") String number) {
        return new CounterFiles(DataFromFTP.getInstance().getCountersFromConfig().get(Integer.parseInt(number)));
    }
}
