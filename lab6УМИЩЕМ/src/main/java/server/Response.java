package server;

import commands.Command;

import java.io.Serializable;

public class Response implements Serializable {
    private Boolean IsCommandOk;
    private Command command;
    private String msg = "";
    public Response(Boolean IsCommandOk, Command command, String msg){
        this.IsCommandOk = IsCommandOk;
        this.command = command;
        this.msg = msg;
    }
    public Response(Boolean IsCommandOk, Command command){
        this.IsCommandOk = IsCommandOk;
        this.command = command;
    }
    public void extendResponse(Response resp){
        this.msg += "\n" + resp.getMsg();
    }
    public String getMsg(){
        return(msg);
    }
    public String getMessage(){
        if(IsCommandOk) {
            return ("\n" +command.getname() + " выполнилась корректно, вы большой специалист в этом деле\n" + msg);
        }
        else{
            return("\n" +command.getname() + " выполнилась некорректно, вы в этом не очень большой специалист\n"+msg);
        }

    }
    Command getCommand(){
        return command;
    }
}
