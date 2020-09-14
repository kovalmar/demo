package pl.sitpres4.demo.CounterFiles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sitpres4.demo.Data.DataFromFTP;

import java.util.Date;
import java.util.List;

@RestController
public class FileNamesController {
    @GetMapping("/files")
    public List<String> fileNames() {
        return DataFromFTP.getInstance().getFileNames();
    }

    @GetMapping("/files/refresh")
    public List<String> fileNamesRefresh() {
        return DataFromFTP.getInstance().getFileNames(true);
    }

    @GetMapping("/files/lastupdated")
    public Date fileNamesLastUpdated() {
        return DataFromFTP.getInstance().getLastUpdated(DataFromFTP.FILE_NAMES);
    }
}
