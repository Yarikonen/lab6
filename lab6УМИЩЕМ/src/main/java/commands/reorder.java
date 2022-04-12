package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class reorder extends Command {

    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (servermode){
            a.reorder();
            return(new Response(true,this));
        }
        else{
            return(new Response(true,this));
        }

    }

    @Override
    public String description() {
        return("переворачиваю коллекцию");

    }

    @Override
    public String getname() {
        return("reorder");
    }
}
