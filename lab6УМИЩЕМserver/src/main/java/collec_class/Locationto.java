package collec_class;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "to")
@XmlAccessorType(XmlAccessType.NONE)
public class Locationto extends Location implements Serializable {
    @XmlElement
    private double x;
    @NotNull
    @XmlElement
    private Long y; //Поле не может быть null
    @XmlElement
    private long z;
    @NotEmpty
    @XmlElement
    private String name; //Строка не может быть пустой, Поле не может быть null
}
