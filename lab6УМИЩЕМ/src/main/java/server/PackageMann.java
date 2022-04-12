package server;

import commands.Command_man;

import java.io.*;
import java.nio.ByteBuffer;

public class PackageMann {
    public String setCommand(ByteBuffer buf, Command_man man) throws IOException, ClassNotFoundException{
        ObjectInputStream obj = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        Response response = (Response) obj.readObject();
        man.upgComMap(response.getCommand());
        return(((Response) obj.readObject()).getCommand().getname());
    }
    public byte[] packResponse(Response resp) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(bos);
        obj.writeObject(resp);
        obj.flush();
        return((bos.toByteArray()));
    }

}
