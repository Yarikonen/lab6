package commands;

import collec_class.*;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class show extends Command {
    Stack<Route> target = new Stack<>();

    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        if (servermode) {
            this.target = a.getStorage();
            return(new Response(true,this,this.infor()));

        }
        else{
            return(new Response(true,this));
        }

    }

    @Override
    public String description() {
        return("вывожу че в коллекции");

    }

    @Override
    public String getname() {
        return("show");
    }
    public String infor(){
        String a ="";
        for (Route route: target
             ) {
            a+=(route.toString()) +"\n";
        }
        return(a);
    }

}
