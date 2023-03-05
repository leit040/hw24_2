package main.java.ua.hillel;

public class FileLoggerConfiguration {
    private  String filePath;
    private  String level;
    private  int maxFileSize;
    private  String logFormat;


    public FileLoggerConfiguration(String filePath, String level, int maxFileSize, String logFormat){
    this.filePath = filePath;
    this.level = level;
    this.maxFileSize = maxFileSize;
    this.logFormat = logFormat;
    }

    public FileLoggerConfiguration(){
        this.filePath = "/var/log/";
        this.level = "DEBUG";
        this.maxFileSize = 256;
        this.logFormat = "";
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public void setLogFormat(String logFormat) {
        this.logFormat = logFormat;
    }

    public void setParams(String key, String value){
        switch (key) {
            case "filePath" -> setFilePath(value);
            case "level" -> setLevel(value);
            case "maxFileSize" -> setMaxFileSize(Integer.parseInt(value));
            case "logFormat" -> setLogFormat(value);
            default -> System.out.println("UNKNOW PARAMETER");
        }

    }
    
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public String getLevel() {
        return level;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public String getLogFormat() {
        return logFormat;
    }
}
