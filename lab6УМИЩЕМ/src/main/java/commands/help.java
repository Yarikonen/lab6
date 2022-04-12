package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class help extends Command {
    private String msg = "";
    @Override
    public Response execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        if (!servermode) {
            msg =("help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} : добавить новый элемент в коллекцию\n" +
                    "updatebyID id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id : удалить элемент из коллекции по его id\n" +
                    "clear : очистить коллекцию\n" +
                    "save : сохранить коллекцию в файл\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "exit : завершить программу (без сохранения в файл)\n" +
                    "remove_last : удалить последний элемент из коллекции\n" +
                    "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                    "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                    "remove_all_by_distance distance : удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному\n" +
                    "sum_of_distance : вывести сумму значений поля distance для всех элементов коллекции\n" +
                    "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
        }
        else {
            return new Response(true,this,msg);
        }
        return new Response (true, this);

    }

    @Override
    public String description() {
        return("помощь");

    }

    @Override
    public String getname() {
        return("help");
    }
}
