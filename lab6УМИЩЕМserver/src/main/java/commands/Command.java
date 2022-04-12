package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    protected Boolean servermode = false;
    public abstract Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException;
    public abstract String description();
    public String  getname(){
        return(this.getClass().getName());
    }
    public Command(){
    }
    public void changeservermode(Boolean b){
        servermode=b;
    }
    public Command(Boolean a){

    }



}
