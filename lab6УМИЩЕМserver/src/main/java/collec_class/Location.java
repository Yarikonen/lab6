package collec_class;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "from")
@XmlAccessorType(XmlAccessType.NONE)
public class Location implements Serializable {

    @XmlElement(required=true)
    private double x;

    @Override
    public String toString() {
        return ("     LocName = " + this.name + "\n" +
                "     Coordinates = " + this.x + " " + this.y.toString() + this.z);
    }

    @NotNull
    @XmlElement(required=true)
    private Long y; //Поле не может быть null


    @XmlElement(required=true)
    private long z;



    @NotEmpty
    @XmlElement(required=true)
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location() {
    }

    public Location(double x, Long y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setZ(long z) {
        this.z = z;
    }
    public void setName(String name) {
        this.name = name;
    }

}
