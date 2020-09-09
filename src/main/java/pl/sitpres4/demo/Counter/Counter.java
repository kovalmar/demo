package pl.sitpres4.demo.Counter;

import java.util.Calendar;

import lombok.Data;

@Data
public class Counter {
    private Long id;
    private Integer number;
    private Integer subNumber;
    private String name;
    private String ftpFileNameMask;

    protected Counter() {}

    public Counter(Long id, String counterNumber) {
        if (counterNumber.startsWith("[Counter")) {
            this.id = id;
            String s = counterNumber.substring("[Counter ".length(), counterNumber.length() - 1);
            if (s.contains(".")) {
                this.number = Integer.parseInt(s.substring(0, s.length() - 2));
                this.subNumber = Integer.parseInt(s.substring(s.length() - 1));
            } else {
                this.number = Integer.parseInt(s);
                this.subNumber = null;
            }
            this.setFtpFileNameMask();
        }
    }

    public void setName(String counterName) {
        String prefix = "Name = ";
        if (counterName.startsWith(prefix)) {
            name = counterName.substring(prefix.length());
        }
        else
        {
            name = counterName;
        }
    }

    public void setFtpFileNameMask() {
        String cNum = String.format("%03d", number);
        if (subNumber != null) {
            cNum += "_" + subNumber;
        }
        //ftpFileNameMask = String.format("CNT_%s_%s.CSV",cNum,"%s_%s"); // %s_%s is for future YYYY_MM
        ftpFileNameMask = String.format("CNT%s_",cNum);

    }
//TODO
    public String getFtpFileName(Calendar reportDate) {
        return String.format(ftpFileNameMask,
                             reportDate.get(Calendar.YEAR),
                             String.format("%02d",(reportDate.get(Calendar.MONTH)+1)));
    }
//TODO
    public String getFtpFileNamePath(Calendar reportDate) {
        String ftpPath = "/M1_FLASH/LOG5M/";
        return ftpPath + getFtpFileName(reportDate);
    }

    @Override
    public String toString() {
        int numberColWidth = 4;
        int nameColWidth = 25;

        if (subNumber == null) {
            return "Numer licznika: " + number
                 + " ".repeat(numberColWidth - number.toString().length())  // fixed length separator
                 + " | Nazwa licznika: " + name
                 + " ".repeat(nameColWidth - name.length())  // fixed length separator
                 + " | Plik raportu: " + ftpFileNameMask;
        }
        else
        {
            return "Numer licznika: " + number + '.' + subNumber
                 + " ".repeat(numberColWidth - 1 - (number.toString()+subNumber.toString()).length()) // fixed length
                 + " | Nazwa licznika: " + name                                                       // separator
                 + " ".repeat(nameColWidth - name.length())  // fixed length separator
                 + " | Plik raportu: " + ftpFileNameMask;
        }
    }
}
