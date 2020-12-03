
//Tony Agosta 544090
import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class Server {
    static int BUF_SIZE = 1024;
    private int port;// porta su cui e`in ascolto il server
    private String REPLY;
    private byte[] replyBuffer;
    private long seed;

    public Server(int numporta, long seed) {
        this.port = numporta;
        this.seed = seed;

    }

    public void start() {
        int rand;
        long latenza;
        Random generator;
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            byte[] buffer = new byte[BUF_SIZE];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            System.out.println("Il server è in attesa");
            String ip = serverSocket.getLocalAddress().getHostAddress();
            if (seed == 0) {// se il valore di seed e` zero vuol dire che non e` stato dato come argomento
                            // da linea di comando
                generator = new Random();
            } else {
                generator = new Random(seed);
            }

            while (true) {
                rand = generator.nextInt(4) + 1;// genero un numero random tra 1 e 4 per simulare il 25% di perdita dei
                                                // pacchetti
                serverSocket.receive(receivedPacket);
                String msg = new String(receivedPacket.getData());

                // simulo la perdita di un pacchetto con il valore di rand=4
                if (rand == 4) {
                    System.out.println(ip + " " + receivedPacket.getPort() + " " + msg + " ACTION: not sent");
                    continue;
                } else {// invia una risposta con probabilità del 25%
                    if (seed == 0) {// se il valore di seed e` zero vuol dire che non e` stato dato come argomento
                                    // da linea di comando
                        latenza = (long) (Math.random() * 200);
                    } else {
                        latenza = (long) (Math.random() * seed);
                    }

                    Thread.currentThread();

                    System.out.println(
                            ip + " " + receivedPacket.getPort() + " " + msg + " ACTION: delayed " + latenza + " ms");

                    try {
                        Thread.sleep(latenza);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    REPLY = msg;
                    replyBuffer = REPLY.getBytes();
                    DatagramPacket packetToSend = new DatagramPacket(replyBuffer, replyBuffer.length,
                            receivedPacket.getAddress(), receivedPacket.getPort());
                    serverSocket.send(packetToSend);// echo del messaggio ricevuto dal client
                }
            }

        } catch (BindException e) {
            System.out.println("Porta già occupata");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
