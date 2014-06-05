package exercise11;

import java.io.Serializable;

public class DataFile implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileName;
    private int fileSize;
    
    public DataFile() {
        
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public int getFileSize() {
        return this.fileSize;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
}
