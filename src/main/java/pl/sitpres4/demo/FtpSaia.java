package pl.sitpres4.demo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import pl.sitpres4.demo.Counter.Counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FtpSaia {
    static final String SERVER = "89.239.98.11";
    static final int PORT = 6090;
    static final String USER = "monitoring";
    static final String PASS = "Bal@)15Plaza";

    static private FTPClient ftpClient = new FTPClient();
    static private AtomicLong idGen = new AtomicLong();

    public static List<Counter> counterListFromSaia() {
        LinkedList<Counter> cntList = new LinkedList<Counter>();
        String ftpFileName = "/INTFLASH/ENERGYLOG/CONFIG.TXT";
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteFile1 = ftpFileName;
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ftpClient.retrieveFileStream(ftpFileName)));

            String line = null;
            Boolean newCounter = false;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (line.startsWith("[Counter ")) {
                        newCounter = true;
                        cntList.add(new Counter(FtpSaia.idGen.incrementAndGet(),line));
                    }
                    if (newCounter) {
                        if (line.startsWith("Name = ")) {
                            cntList.getLast().setName(line);
                            newCounter = false;
                        }
                    }
                }
            }
            reader.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return cntList;
    }

    public static List<String> getFileNameList(String fileMask) {

        ArrayList<String> fileNameList = new ArrayList<String>();
        String ftpDirectoryName = "/M1_FLASH/LOG5M";
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.changeWorkingDirectory(ftpDirectoryName);
            FTPFileFilter filter = ftpFile -> (ftpFile.isFile() &&  ftpFile.getName().contains(fileMask));
            FTPFile[] fileList = ftpClient.listFiles("", filter);
            for (FTPFile f: fileList) {
                fileNameList.add(f.getName());
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileNameList;
    }
}