import collec_class.*;
import commands.*;
import server.PackageMan;
import server.Response;
import utils.WrongXMLWormatException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class testing {
    public static void main(String ... arg)  {
        try {
            byte[] byt = new byte[10000];
            ByteBuffer bf;
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            SocketAddress addr = new InetSocketAddress(6789);
            dc.bind(addr);
            JAXBContext context = JAXBContext.newInstance(Collection_manager.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Collection_manager<Route> kek = new Collection_manager<>();
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            try {
                kek = (Collection_manager) unmarshaller.unmarshal(new BufferedReader(new FileReader("aboba.txt")));
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                for (Route a : kek.getStorage()
                ) {
                    Set<ConstraintViolation<Route>> viol = validator.validate(a);
                    if (viol.size() > 0) {
                        throw new WrongXMLWormatException();
                    }

                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден или к нему нет доступа");
                exit exit = new exit();
                try {
                    exit.execute(kek, buff);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (WrongXMLWormatException exp) {
                System.out.println("XML файл задан некорректно");
            }
            Stack<Route> st = kek.getStorage();


            Thread thread = new Thread(()->{
                Scanner sc = new Scanner(System.in);
                while(true){
                if (sc.nextLine().equals("save")) {
                    Command save = new save(false);
                    save.changeservermode(false);
                    Collection_manager a = new Collection_manager<>();
                    a.setStorage(st);
                    try {
                        save.execute(a, buff);
                        save.changeservermode(true);
                        System.out.println(save.execute(a, buff).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                }
            });
            thread.start();


            Command_man man = new Command_man();
            boolean booling = true;
            PackageMan pm = new PackageMan();
            while(booling){
                bf = ByteBuffer.wrap(byt);
                addr=dc.receive(bf);
                if(addr!=null){
                    String name = pm.setCommand(bf,man);
                    man.changeservermode(name,true);
                    Response resp =man.command_exec(name,kek,buff,false);
                    dc.send(pm.packResponse(resp),addr);
                }
            }


            /*
            do {
                try {
                    PrintStream originalStream = System.out;
                    System.setOut(originalStream);
                    buff.mark(8192);
                    String aa = buff.readLine();
                    man.command_exec(aa.split(" ")[0], kek, buff, false);
                    man.changeservermode(aa.split(" ")[0],true);
                    System.out.println(man.command_exec(aa.split(" ")[0], kek, buff, false).getMessage());
                    man.changeservermode(aa.split(" ")[0],false);
                } catch (NullPointerException excp) {
                    excp.printStackTrace();
                    System.out.println("нет такой команды//вы принудительно вышли");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (booling);

            */
        }


        catch(JAXBException | IOException exp){
            exp.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
