package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.*;

public class save extends Command {
    private boolean another_script;
    private String file = "";

    public save(Boolean another_script){
        this.another_script = another_script;
    }


    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader b) throws IOException {


        if(!servermode){
            try {
                System.out.println("куда хотите сохранить файл?");
                file = b.readLine();
                if (file == null) throw new NullPointerException();
            }catch(NullPointerException exp){
                throw new WrongScriptException();
            }
        }
        else {
            try {
                PrintWriter write = new PrintWriter(file);
                JAXBContext context = JAXBContext.newInstance(Collection_manager.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(a, write);
                return (new Response(true, this));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Нет доступа");
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (NullPointerException exp) {
                if (another_script) {
                    throw new WrongScriptException();
                }
            }
        }
        return(new Response(true,this));

    }








    @Override
    public String description() {
        return("сохраняю коллекцию в файл");

    }

    @Override
    public String getname() {
        return("save");
    }
}