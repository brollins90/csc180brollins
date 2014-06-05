package edu.neumont.csc180.brollins.daily.day33;

import java.io.Serializable;

public class JavaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Class conforms to a standard
    // 1) public no-argument constructor
    // 2) private member variables, getters / setters
    // 3) implements Serializable

    private int x;
    private int y;

    public JavaBean() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
