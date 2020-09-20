package pl.sitpres4.demo.CounterFiles;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Data.DataFromFTP;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileNamesController {
    @GetMapping("")
    public List<String> fileNames() {
        return DataFromFTP.getInstance().getFileNames();
    }

    @GetMapping("/refresh")
    public List<String> fileNamesRefresh() {
        return DataFromFTP.getInstance().getFileNames(true);
    }

    @GetMapping("/lastupdated")
    public ZonedDateTime fileNamesLastUpdated() {
        return DataFromFTP.getInstance().getLastUpdated(DataFromFTP.FILE_NAMES);
    }
}
