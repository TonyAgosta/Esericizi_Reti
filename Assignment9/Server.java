import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    static int BUF_SIZE = 1024;
    private int port;// porta su cui e`in ascolto il server
    private String REPLY;
    private byte[] replyBuffer;

    public Server(int numporta) {
        this.port = numporta;
    }

    public void start() {
        int rand = (int) (Math.random() * 4) + 1;
        long latenza;
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            byte[] buffer = new byte[BUF_SIZE];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            System.out.println("Il server è in attesa");
            String ip = serverSocket.getLocalAddress().getHostAddress();

            while (true) {
                rand = (int) (Math.random() * 4) + 1;
                serverSocket.receive(receivedPacket);
                String msg = new String(receivedPacket.getData());

                if (rand == 4) {
                    System.out.println(ip + " " + receivedPacket.getPort() + " " + msg + " ACTION: not sent");
                    continue;
                } else {// invia una risposta con probabilità del 25%

                    Thread.currentThread();
                    latenza = (long) (Math.random() * 200);
                    System.out.println(
                            ip + " " + receivedPacket.getPort() + " " + msg + " ACTION: delayed " + latenza + " ms");

                    try {
                        Thread.sleep(latenza);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    REPLY = Long.toString(latenza);
                    replyBuffer = REPLY.getBytes();
                    DatagramPacket packetToSend = new DatagramPacket(replyBuffer, replyBuffer.length,
                            receivedPacket.getAddress(), receivedPacket.getPort());
                    serverSocket.send(packetToSend);
                }
            }
        } catch (BindException e) {
            System.out.println("Porta già occupata");
        } catch (IOException e) { // NB: SocketException è una sottoclasse di IOException
            e.printStackTrace();
        }

    }

}
