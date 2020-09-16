package pl.sitpres4.demo.CounterFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.CounterData.CounterData;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.util.List;

@RestController
public class CounterFileController {
    @GetMapping("/counters/{number}/files/{year}/{month}")
    public CounterFile counterFile(@PathVariable("number") String number,
                           @PathVariable("year") String year,
                           @PathVariable("month") String month) {
        //TODO
        return new CounterFile(String.format("%s%s_%s.CSV",
                DataFromFTP.getInstance().getCountersFromConfig().get(Integer.parseInt(number)).getFtpFileNameMask(),
                year,month));
    }

    @GetMapping("/counters/{number}/files/{year}/{month}/data")
    public CounterData counterData(@PathVariable("number") String number,
                                   @PathVariable("year") String year,
                                   @PathVariable("month") String month) {
        //TODO
        return new CounterData(String.format("%s%s_%s.CSV",
                DataFromFTP.getInstance().getCountersFromConfig().get(Integer.parseInt(number)).getFtpFileNameMask(),
                year,month));
    }
}
