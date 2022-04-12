package collec_class;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private long x;
    private double y;

    public long getX() {
        return x;
    }
    public void setX(long x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return("Coordinates = " + this.x + " "+ this.y );
    }


}
