package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class updatebyID extends Command {
    private boolean another_script;
    private add add;
    private int id;

    public updatebyID(Boolean another_script){
        this.another_script = another_script;

    }


    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader in) throws IOException {


        if(!servermode) {
            int id=0;
            in.reset();
            add = new add(another_script);
            Route intik = new Route();
            Boolean cont = true;
            System.out.println("введите номер элемента, который хотите обновить");
            do {
                try {
                    String c;
                    String kekw = in.readLine();
                    if (kekw == null) {
                        throw new NullPointerException();
                    }
                    String[] content = kekw.split(" ");
                    if (content.length == 2) {
                        c = content[1];
                    } else {
                        c = content[0];
                    }
                    id = Integer.parseInt(c);
                    return(new Response(true,this));
                } catch (NumberFormatException ex) {
                    System.out.println("Возможно вы ввели не integer или забыли ввести данные, попробуйте ещё раз");
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                }

            } while (cont);
        }
        else {
            try {
                Route intik = a.find_by_id(id);
                if (intik == null) {
                    throw new NullPointerException();
                }
                add.addd(intik, in);
                return(new Response(true,this));
            }catch (ArrayIndexOutOfBoundsException ex) {
                if (another_script) {
                    throw new WrongScriptException();
                }
                return(new Response(false,this,"выход за грань"));
            } catch (NullPointerException exp) {
                if (another_script) {
                    throw new WrongScriptException();
                }
                return(new Response(false,this,"такого нет"));
            }

        }
        return(null);



    }

    @Override
    public String description() {
        return("изменим элемент по ID");

    }

    @Override
    public String getname() {
        return("updatebyID");
    }
}
