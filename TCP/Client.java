import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length != 2){
            System.out.println("Usage: java Client <hostname> <size (bytes)>");
            return;
        }

        String hostname = args[0];
        int size = Integer.parseInt(args[1]);

        Socket socket = new Socket(hostname, 5000);
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buffer = new byte[size];
        String datos = new String(buffer)+'\n';

        System.out.println("Enviando "+size+" bytes en "+System.currentTimeMillis());
        bw.write(datos);
        bw.flush();
    }
}
