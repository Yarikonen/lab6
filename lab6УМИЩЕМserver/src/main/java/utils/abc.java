package utils;

public class abc<E extends Integer> {
    E t;
    abc (E t){
        this.t = t;


    }
    public Integer  sum(E t, E a){
        return(t.intValue()+a.intValue());
    }
}

