package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class filter_starts_with_name extends Command {
    boolean another_script;
    String c = "";
    public filter_starts_with_name(boolean another_script) {
        this.another_script = another_script;
    }
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        b.reset();
        if (servermode){
            a.filterwithname(c);
        }
        else {
            Boolean cont = true;

                String cc = b.readLine();
                if (cc == null || (cc.split(" ").length == 1 && another_script)) throw new WrongScriptException();
                String[] content = cc.split(" ");
                if (content.length == 2) {
                    c = content[1].replaceAll(" ", "");
                } else if (c.isEmpty()) {
                    System.out.println("Введите подстроку, вы забыли");
                    c = b.readLine();
                }
        }
        return(new Response(true,this));


    }

    @Override
    public String description() {
        return("вывожу все элемент начинающиеся с данной подстроки");

    }

    @Override
    public String getname() {
        return("filter_starts_with_name");
    }
}
