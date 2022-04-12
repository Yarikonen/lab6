package collec_class;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement(name = "storage2")
@XmlAccessorType(XmlAccessType.FIELD)
public class Collection_manager<E extends Route> implements Serializable {
    @XmlElement(name="route")
    private Stack<E> storage;
    private final Date creationDate = new Date();
    public Collection_manager(Stack <E> storage){
        this.storage = storage;
    }
    public Collection_manager(){
        this.storage = new Stack<>();
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public Stack<E> getStorage() {
        return storage;
    }
    public void setStorage(Stack<E> storage) {
        this.storage = storage;
    }
    public void filterwithname(String name){
        for(E e:storage){
            if(e.get_Name().startsWith(name)){
                System.out.println(e);
            }
        }
    }
    public E find_by_id(int id){
        for (E e: storage) {
            if (e.get_ID()==id){
                return(e);
            }
        }
        return(null);
    }
    public void remove_lower(E k){
        storage.stream().forEach(e -> {if (e.compareTo(k)<0){storage.remove(e);}});
    }
    public Stack<E> reorder(){
        Stack<E> temp2 = storage;
        Stack<E> temp = new Stack<>();
        for (int i = 0; i <= storage.size(); i++) {
            E e = storage.pop();
            temp.push(e);
        }
        storage = temp;
        return(temp);

    }
    public void remove_all_by_distance(Long distance){
        Stack<E> c = storage;
        storage.removeIf(e -> e.getDistance().equals(distance));
    }
    public Long sum_distance(){
        Long sum = 0L;
        for (E e: storage) {
            sum += e.getDistance();
        }
        return(sum);
    }
    public void remove_lower(String name){
        List<E> a  = new ArrayList<E>();
        for (E e: storage) {
            if (e.get_Name().startsWith(name)){
                a.add(e);
            }
        }
    }
    public void add(E e){
        storage.add(e);
    }







}
