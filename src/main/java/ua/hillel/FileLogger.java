package ua.hillel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FileLogger {
    private final FileLoggerConfiguration flc;
    private String actualLogFile;


    public FileLogger(FileLoggerConfiguration flc) {
        this.flc = flc;
    }

    public void log(String logLevel, String message) throws IOException {
    if(!checkFileSize()){
        this.setupLogFileName();
    }

    this.writeToFile(prepareMessage(logLevel, message));
    }


    private String prepareMessage(String logLevel, String message){
//Here must be string formatting logic, according to config:: format,  but I'm so tired, sorry ((
        return getTimeString()+": "+logLevel+": "+message;
    }
    private void setupLogFileName() {

        this.actualLogFile = this.flc.getFilePath()+"Log__"+getTimeString()+".txt";
    }

    private String getTimeString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void writeToFile(String message) throws IOException {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(this.actualLogFile, true));
            br.write(message + "\n");
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkFileSize() {
        try {
            Path filePath = Paths.get(this.actualLogFile);
            return Files.size(filePath) <= flc.getMaxFileSize();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
