import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;

public class ServerRes {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        if(args.length != 1){
            System.out.println("Usage: java ServerRes <size (bytes)>");
            return;
        }
        int size = Integer.parseInt(args[0]);
        HashSet<Integer> set = new HashSet<>();
        while(true){
            byte[] buf = new byte[size];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            int total = Integer.parseInt(new String(packet.getData()).trim());
            set.add(total);
            int lost = total-set.size();
            double percent = (double)lost/(double)total;
            percent*=100f;
            System.out.println("Lost/Total: "+lost+"/"+total+" "+String.format("%.2f", percent)+'%');

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        }
    }
}
