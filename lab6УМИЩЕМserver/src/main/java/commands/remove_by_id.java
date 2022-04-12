package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class remove_by_id extends Command{
    private boolean anotherscript;
    private String c = "";
    public remove_by_id(Boolean anotherscript){
        this.anotherscript = anotherscript;

    }
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        b.reset();
        Route intik = new Route();
        boolean cont=true;
        if (servermode){
            try {
                intik = a.find_by_id(Integer.parseInt(c));
                a.getStorage().remove(intik);
                if (intik == null) {
                    throw new NullPointerException();
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                if (anotherscript) {
                    throw new WrongScriptException();
                }
                return(new Response(false,this,"выход за границы"));

            }  catch (NullPointerException exp) {
                if (anotherscript) {
                    throw new WrongScriptException();
                }
                return(new Response(false,this,"такого айди нет"));
                }

        }
        do {
            try {
                String kekw = b.readLine();
                if (kekw == null) {
                    throw new NullPointerException();
                }
                String[] content = kekw.split(" ");
                if (content.length == 2) {
                    c = content[1];
                } else {
                    c = content[0];
                }


                cont = false;
            } catch (NumberFormatException ex) {
                System.out.println("Возможно вы ввели не integer или забыли ввести данные, попробуйте ещё раз");
                if (anotherscript) {
                    throw new WrongScriptException();
                }
            }


        } while (cont);
        return(new Response(true,this));
    }

    @Override
    public String getname() {
        return("remove_by_id");
    }

    @Override
    public String description() {
        return("могу удалять элемент по его id");
    }
}
