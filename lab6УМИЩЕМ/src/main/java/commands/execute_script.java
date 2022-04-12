package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.*;
import java.util.Stack;

public class execute_script extends Command {
    private Collection_manager<Route> target;
    private Stack<String> scripts = new Stack<>();
    private Stack<Response> responses = new Stack<>();
    private String script;
    private String scriptnext;



    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        scripts = new Stack<>();
        responses = new Stack<>();
        target = a;
        boolean cont = true;
        if (servermode){
            try{
                scriptnext = script;
                exec(script);
            }
            catch(FileNotFoundException ex){
                return (new Response(false, this, "файл не найден или вы забыли его ввести, попробуйте ввести другой"));

            }

        }
        else {
                    buff.reset();
                    String[] content = buff.readLine().split(" ");
                    if (content.length == 2) {
                        script = content[1];
                    } else {
                        System.out.println("Введите название скрипта, вы забыли");
                        script = buff.readLine();
                    }
                    return(new Response(true,this));


        }


        Response resp = new Response(true,this );

        responses.stream().forEach(resp::extendResponse);
        return(resp);
    }

    @Override
    public String description() {
        return("я умею запускать скрипт");

    }

    @Override
    public String getname() {
        return("execute_script");
    }
    private void exec(String script) throws IOException {
        scripts.add(scriptnext);
        BufferedReader scriptReader = new BufferedReader(new FileReader(script));
        Command_man man = new Command_man(
                new hentai()
                ,new show()
                ,new remove_by_id(true)
                ,new info()
                ,new add(true)
                ,new help()
                ,new updatebyID(true)
                ,new execute_script()
                ,new remove_last()
                ,new remove_lover(true)
                ,new reorder()
                ,new remove_all_by_distance(true)
                ,new sum_of_distance()
                ,new filter_starts_with_name(true));

        while (true) {
            String command;
            String commargs = scriptReader.readLine();
            if (commargs == null) {
                break;
            }
            scriptReader.mark(8192);
            System.out.println(commargs);
            String[] content = commargs.split(" ");
            command=content[0];
            try{
            if (command.equals("execute_script")) {
                if (content.length==1) throw new WrongScriptException();
                scriptnext = content[1];
                if (!scripts.contains(scriptnext)) {
                    try {
                        exec(scriptnext);
                        Response resp = new Response(true, this);
                        responses.add(resp);

                    } catch (FileNotFoundException ex) {
                        Response resp1 = new Response(false, this, "файл " + scriptnext+ " не найден или доступ к нему затреднён");
                        responses.add(resp1);
                    }
                } else {
                    Response resp1 = new Response(false, this,"Данный скрипт уже исполнялся - "+scriptnext);
                    responses.add(resp1);
                }
            } else {
                try {
                    man.changeservermode(command,false);
                    man.command_exec(command, target, scriptReader, true);
                    man.changeservermode(command,true);
                    responses.add(man.command_exec(command, target, scriptReader, true));

                } catch (NullPointerException exp) {
                    responses.add(new Response(false,this,"Команды " + command + " не существует"));
                }

            }
            }catch(IOException e){

                try {
                    scriptReader.reset();
                } catch (IOException exp) {
                    exp.printStackTrace();
                    responses.add(new Response(false,this,"sAD"));
                }
                responses.add(new Response(false, man.commandgetter(command), "Ошибка при выполнении " + command + "\nСкрипт " + scripts.peek() + " перешёл к следующей команде"));


            }
        }

    }
}