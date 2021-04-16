import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ClientRes {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length != 3){
            System.out.println("Usage: java ClientRes <hostname> <size (bytes)> <rate (ms)>");
            return;
        }

        String hostname = args[0];
        int size = Integer.parseInt(args[1]);
        long rate = Long.parseLong(args[2]);

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(hostname);

        int cnt = 0;
        while(true){
            //Send req
            long t1 = System.currentTimeMillis();
            byte[] buf = new byte[size];
            byte[] st = (""+(++cnt)).getBytes();
            System.arraycopy(st, 0, buf, 0, st.length);

            DatagramPacket packet = new DatagramPacket(buf,buf.length,address,4445);
            socket.send(packet);

            buf = new byte[size];
            packet = new DatagramPacket(buf, buf.length);

            long t2 = System.currentTimeMillis();
            socket.setSoTimeout(t2-t1<rate ? (int)(t2-t1) : 1);
            try {
                socket.receive(packet);
                int val = Integer.parseInt(new String(packet.getData()).trim());
                if(val != cnt) throw new SocketTimeoutException();
            } catch (SocketTimeoutException e) {
                cnt--;
            }

            t2 = System.currentTimeMillis();
            if(t2-t1<rate) Thread.sleep(rate-(t2-t1));
        }



        // get response
//        packet = new DatagramPacket(buf,buf.length);
//        socket.receive(packet);
//        System.out.println(new String(packet.getData()).trim());
    }
}
