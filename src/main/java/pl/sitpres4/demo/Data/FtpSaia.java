package pl.sitpres4.demo.Data;

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
    static final String DATA_DIRECTORY_PATH = "/M1_FLASH/LOG5M";
    static final String CONFIG_FILE_PATH = "/INTFLASH/ENERGYLOG/CONFIG.TXT";

    static private FTPClient ftpClient = new FTPClient();
    static private AtomicLong idGen = new AtomicLong();

    static private void connectSaiaFTP() throws IOException {
        ftpClient.connect(SERVER, PORT);
        ftpClient.login(USER, PASS);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    static private void disconnectSaiaFTP() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Counter> counterListFromSaia() {
        LinkedList<Counter> cntList = new LinkedList<Counter>();
        try {
            connectSaiaFTP();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(ftpClient.retrieveFileStream(CONFIG_FILE_PATH)));

            String line = null;
            Boolean newCounter = false;
            int cntCounter = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (line.startsWith("[Counter ")) {
                        newCounter = true;
                        cntList.add(new Counter(cntCounter,line));
                        if (cntList.getLast().counterFileExists()) {
                            cntCounter++;
                        }
                        else {
                            cntList.removeLast();
                        }
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
            disconnectSaiaFTP();
        }
        return cntList;
    }

    public static List<String> getFileNames(String fileMask) {
        ArrayList<String> fileNameList = new ArrayList<String>();
        try {
            connectSaiaFTP();

            FTPFileFilter filter = ftpFile -> (ftpFile.isFile() && ftpFile.getName().contains(fileMask));
            ftpClient.changeWorkingDirectory(DATA_DIRECTORY_PATH);
            FTPFile[] fileList = ftpClient.listFiles("", filter);

            // prepare list of names to be returned
            for (FTPFile f: fileList) {
                fileNameList.add(f.getName());
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            disconnectSaiaFTP();
        }
        return fileNameList;
    }

    public static List<FTPFile> getCounterFileList() {
        ArrayList<FTPFile> fileList = new ArrayList<FTPFile>();
        String ftpDirectoryName = "/M1_FLASH/LOG5M";
        try {
            connectSaiaFTP();

            ftpClient.changeWorkingDirectory(ftpDirectoryName);
            FTPFile[] ftpFileList = ftpClient.listFiles("");
            for (FTPFile f : ftpFileList) {
                fileList.add(f);
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            disconnectSaiaFTP();
        }
        return fileList;
    }

    public static List<String> getDataFromFile(String name) {
        ArrayList<String> dataList = new ArrayList<String>();
        try {
            connectSaiaFTP();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(ftpClient.retrieveFileStream(DATA_DIRECTORY_PATH + "/" + name)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                dataList.add(line);
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            disconnectSaiaFTP();
        }
        return dataList;
    }
}
