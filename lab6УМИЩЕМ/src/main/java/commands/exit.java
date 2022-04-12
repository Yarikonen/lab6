package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class exit extends Command {
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (!servermode) {
            System.out.println("завершение работы");
            System.exit(0);
            return(new Response(true, this));
        }
        else{
            return(new Response(false, this));
        }


    }

    @Override
    public String description() {
        return("ОФАЙ");

    }

    @Override
    public String getname() {
        return("exit");
    }
}
