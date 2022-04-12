package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class sum_of_distance extends Command {

    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (servermode) {
            return (new Response(true, this, a.sum_distance().toString()));
        }
        return(new Response(true,this));
    }

    @Override
    public String description() {
        return("суммирую дистанции");

    }

    @Override
    public String getname() {
        return("sum_of_distance");
    }
}
