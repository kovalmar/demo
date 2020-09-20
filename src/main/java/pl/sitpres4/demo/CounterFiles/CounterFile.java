package pl.sitpres4.demo.CounterFiles;

import lombok.Data;
import pl.sitpres4.demo.Counter.Counter;

import java.text.DateFormatSymbols;

@Data
public class CounterFile {
    String name;
    String year;
    String month;
    String monthNamePL;

    CounterFile(Counter counter, String name) {
        setName(name);
        int yPos = 7;  // position of 'yyyy' in CNTxxx_yyyy_mm.CSV
        int mPos = 12; // position of 'mm' in CNTxxx_yyyy_mm.CSV
        if (counter.getSubNumber() != null) { // when counter with subnumber position is shifted by '_x'
            yPos += 2;
            mPos += 2;
        }
        setYear(name.substring(yPos,yPos+4)); // yyyy = 4 signs
        setMonth(name.substring(mPos,mPos+2)); // mm = 2 signs
        setMonthNamePL();
    }

    private void setMonthNamePL() {
        this.monthNamePL = new DateFormatSymbols().getMonths()[Integer.parseInt(this.month) - 1];
    }
}
