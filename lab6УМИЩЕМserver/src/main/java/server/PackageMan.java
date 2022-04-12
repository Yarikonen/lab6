package server;

import commands.Command_man;

import java.io.*;
import java.nio.ByteBuffer;

public class PackageMan {
    public String setCommand(ByteBuffer buf, Command_man man) throws IOException, ClassNotFoundException{
            ObjectInputStream obj = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        /* ((Response) obj.readObject).getName  */
            Response response = (Response) obj.readObject();
            man.upgComMap(response.getCommand());
            return response.getCommand().getname();


    }
    public ByteBuffer packResponse(Response resp) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream obj = new ObjectOutputStream(bos);
        obj.writeObject(resp);
        return(ByteBuffer.wrap(bos.toByteArray()));
    }

}
