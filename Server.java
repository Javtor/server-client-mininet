import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        if(args.length != 1){
            System.out.println("Usage: java Server <size (bytes)>");
            return;
        }
        int size = Integer.parseInt(args[0]);
        int received = 0;
        while(true){
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            received++;
            int total = Integer.parseInt(new String(packet.getData()).trim());
            int lost = total-received;
            double percent = (double)lost/(double)total;
            percent*=100f;
            System.out.println("Lost/Total: "+lost+"/"+total+" "+String.format("%.2f", percent)+'%');

//            InetAddress address = packet.getAddress();
//            int port = packet.getPort();
//            packet = new DatagramPacket(buf, buf.length, address, port);
//            socket.send(packet);
        }
    }
}
