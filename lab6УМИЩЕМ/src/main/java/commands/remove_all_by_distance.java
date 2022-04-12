package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class remove_all_by_distance extends Command {
    boolean another_script;
    public remove_all_by_distance(boolean another_script){
        this.another_script = another_script;
    }
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        Long distance = 0L;

        b.reset();
        if (servermode){
            a.remove_all_by_distance(distance);
        }
        else {
            Boolean cont = true;
            do {
                try {
                    String c;
                    String[] content = b.readLine().split(" ");
                    System.out.println(content[0]);
                    if (content.length == 2) {
                        c = content[1];
                    } else {
                        c = content[0];
                    }
                    if (c == null) throw new WrongScriptException();
                    distance = Long.parseLong(c);
                    cont = false;


                } catch (NumberFormatException ex) {
                    System.out.println("число типа Long не найдено или вы забыли его ввести, попробуйте ввести ещё раз");
                    if (another_script) throw new WrongScriptException();
                }
            }while(cont);
        }
        return(new Response(true,this));





    }

    @Override
    public String description() {
        return("удаляю всё че равно такому расстоянию");

    }

    @Override
    public String getname() {
        return("remove_all_by_distance");
    }
}
