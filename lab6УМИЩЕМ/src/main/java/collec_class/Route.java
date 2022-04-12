package collec_class;

import utils.dateadapter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDateTime;
import java.util.Set;

@XmlType(name="Route")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Route implements Comparable<Route>, Serializable {
    @XmlElement(name="id",required=true)
    @NotNull
    @Min(value = 1)
    private Integer id; //Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement(name="name",required=true)
    @Size(min = 1)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement(name="coordinates",required=true)
    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @XmlElement(name = "creationDate",required=true)
    @NotNull
    @XmlJavaTypeAdapter(utils.dateadapter.class)
    private java.time.LocalDateTime creationDate; //, Значение этого поля должно генерироваться автоматически
    @XmlElement(name="from",required=true)
    @NotNull
    private Location from; //Поле не может быть null
    @XmlElement(name = "to",required=true)
    @NotNull
    private Location to; //Поле может быть null
    @XmlElement(name="distance",required=true)
    @NotNull
    @Min(value = 3L, message="distance должно быть большей 1")
    private Long distance; //Поле может быть null, Значение поля должно быть больше 1
     public Route(){
         this.id = this.hashCode();
         this.creationDate= LocalDateTime.now();
     }
     public Route(Integer id, String name, Coordinates coordinates, Location f, Location t, Long distance) {
        this.id = this.hashCode();
        this.name = name;
        this.coordinates=coordinates;
        this.from=f;
        this.to=t;
        this.distance=distance;
        this.creationDate= LocalDateTime.now();
    }

    @Override
    public int compareTo(Route o) {
        int compaaaare = distance.compareTo(o.getDistance());
        return(compaaaare == 0 ? (name.compareTo(o.get_Name())): compaaaare);
    }
    public int get_ID(){
        return(this.id);
    }


    @Override
    public String toString() {
        return("id = "+this.id.toString() + "\n"+
                "name = "+this.name + "\n" +
                this.coordinates.toString()+ "\n"+
                "from = \n"+this.from.toString()+"\n"+
                "to = \n"+ this.to.toString() + "\n"+
                "distance = " + this.distance.toString()+ "\n" +
                "creationDate = " + this.creationDate.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Route){
            Route a = (Route) obj;
            return(a.get_ID()==this.id);
        }
        else return(false);
    }

    public String get_Name(){
        return(this.name);
    }
    public Long getDistance() {
        return distance;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }
    public static <E> boolean  validate(E e){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(e);
        return(violations.size()==0);
    }


}