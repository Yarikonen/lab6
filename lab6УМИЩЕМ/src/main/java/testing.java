import collec_class.*;
import commands.*;
import server.PackageMann;
import server.Response;

import java.io.*;
import java.net.*;


public class testing {
    public static void main(String ... arg) {
        try {
            System.out.println("ABOBA");
            DatagramSocket ds;
            DatagramPacket dp;
            InetAddress host;
            int port;
            ds = new DatagramSocket();
            ds.setSoTimeout(5000);
            host = InetAddress.getLocalHost();
            port = 6789;
            Collection_manager<Route> kek = new Collection_manager<>();
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            Command_man man = new Command_man(
                    new gigachad(),
                    new exit(),
                    new remove_by_id(false),
                    new hentai(),
                    new show(),
                    new info(),
                    new add(false),
                    new help(),
                    new updatebyID(false),
                    new execute_script(),
                    new remove_last(),
                    new remove_lover(false),
                    new reorder(),
                    new remove_all_by_distance(false),
                    new sum_of_distance(),
                    new filter_starts_with_name(false));
            boolean booling = true;
            do {
                try {
                    PrintStream originalStream = System.out;
                    System.setOut(originalStream);
                    buff.mark(8192);
                    String aa = buff.readLine();
                    if (aa.split(" ")[0].equals("exit")){
                        Command save = new save(false);
                        save.changeservermode(false);
                        Response resp =save.execute(kek,buff);
                        testing.send(resp,host,port,ds);
                        testing.get(host,port,ds);
                    }
                    Response resp = man.command_exec(aa.split(" ")[0], kek, buff, false);
                    man.changeservermode(aa.split(" ")[0], true);
                    testing.send(resp,host,port,ds);
                    testing.get(host,port,ds);
                    man.changeservermode(aa.split(" ")[0], false);

                } catch (NullPointerException excp) {
                    System.out.println("нет такой команды//вы принудительно вышли");

                } catch (SocketTimeoutException exp) {
                    System.out.println("вы лох");
                    testing.main();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } while (booling);
        }catch(SocketException | UnknownHostException exp2){
            System.out.println("ТЫ ЛОХ");
        }
    }
    private static void get(InetAddress host,int port, DatagramSocket ds) throws IOException, ClassNotFoundException{
        byte[] arr2 = new byte[10000];
        DatagramPacket dp2 = new DatagramPacket(arr2, arr2.length, host, port);
        ds.receive(dp2);
        ObjectInputStream obj = new ObjectInputStream(new ByteArrayInputStream(dp2.getData()));
        Response resp2 = (Response) obj.readObject();
        System.out.println(resp2.getMessage());
    }
    private static void send(Response resp, InetAddress host,int port, DatagramSocket ds)throws IOException{
        PackageMann pm = new PackageMann();
        byte[] arr = pm.packResponse(resp);
        DatagramPacket dp = new DatagramPacket(arr, arr.length, host, port);
        ds.send(dp);
    }

    }




