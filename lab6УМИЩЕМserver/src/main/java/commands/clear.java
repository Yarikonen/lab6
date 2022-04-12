package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class clear extends Command {
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (!servermode){
            return(new Response(true,this));
        }
        a.getStorage().removeAllElements();
        return(new Response(true, this));

    }

    @Override
    public String description() {
        return("чисти");

    }

    @Override
    public String getname() {
        return("clear");
    }
}
