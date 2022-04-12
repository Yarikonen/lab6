package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class info extends Command {


    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        if (servermode) {
            Stack<Route> b = a.getStorage();
            System.out.println((b.getClass().getName() + " " + a.getCreationDate().toString()));
            return(new Response(true,this));
        }
        else{
            return new Response(false,this);
        }

    }

    @Override
    public String description() {
        return("информация о коллекции");

    }

    @Override
    public String getname() {
        return("info");
    }

}
