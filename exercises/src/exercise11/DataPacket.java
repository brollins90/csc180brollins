package exercise11;

import java.io.Serializable;
import java.util.Date;

public class DataPacket implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String message;
    private Date timestamp;
    
    public DataPacket() {
        
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
