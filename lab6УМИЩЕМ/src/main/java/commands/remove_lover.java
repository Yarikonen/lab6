package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class remove_lover extends Command {
    private boolean another_script;
    private Integer ID;
    public remove_lover(Boolean another_script){
        this.another_script = another_script;


    }

    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        boolean cont=true;

        Route route = new Route();
        if (servermode){
            if (ID==null){
                a.remove_lower(route);
            }
            else{
                route=a.find_by_id(ID);
                if (route == null) {
                    return(new Response(false,this,"такого ID нет"));
                }
            }
            a.remove_lower(route);
            return(new Response(true,this));
        }
        else {
            do {
                System.out.println("Хотите выбрать элемент из существуюших? да/нет");
                try {
                    b.mark(8192);
                    String decis = b.readLine();
                    if (decis.equals("да")) {
                        System.out.println("Введите ID");
                        String c = b.readLine();
                        ID = Integer.parseInt(c);

                        cont = false;
                    } else if (decis.equals("нет")) {
                        add add = new add(another_script);
                        add.addd(route, b);
                        cont = false;
                    } else {
                        System.out.println("ДА/нЕТ");
                        if (another_script) throw new WrongScriptException();
                    }

                } catch (NumberFormatException exp) {
                    System.out.println("ID должен быть целым положительным");
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                } catch (NullPointerException exp) {
                    System.out.println("Такого элемента нет");
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                }
            } while (cont);
        }

        return(new Response(true,this));

    }

    @Override
    public String description() {
        return("удалю все элементы которые меньше");

    }

    @Override
    public String getname() {
        return("remove_lover");
    }
}
