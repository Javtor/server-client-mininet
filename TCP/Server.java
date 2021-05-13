import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        while(true){
            System.out.println("Esperando conexion");
            Socket socket = server.accept();
            System.out.println("Conexion aceptada");

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String datos = br.readLine();
            System.out.println("recibidos "+datos.getBytes().length+" bytes en "+System.currentTimeMillis());
            socket.close();
            System.out.println("Conexion cerrada");
        }
    }
}
