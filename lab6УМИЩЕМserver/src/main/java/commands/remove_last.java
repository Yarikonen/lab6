package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class remove_last extends Command {

    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (servermode) {
            if (a.getStorage().isEmpty()) {
                return (new Response(false, this, "already pusto"));
            } else {
                a.getStorage().pop();
            }
            return (new Response(true, this));
        }
        else{
            return(new Response(true,this));
        }
    }

    @Override
    public String description() {
        return("удалю послдений элемент");

    }

    @Override
    public String getname() {
        return("remove_last");
    }
}
