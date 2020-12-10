
//Tony Agosta 544090
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.Random;

public class Server {

    // indirizzo del gruppo di multicast
    private InetAddress multicastGroup;

    // porta associata all'indirizzo multicast
    private final int port;

    // addr e` indirizzo del gruppo di multicast
    // port e`la porta a cui associare il socket di multicast
    // @throws UnknownHostException se l'indirizzo non è valido
    public Server(String addr, int port) throws UnknownHostException, IllegalArgumentException {
        this.multicastGroup = InetAddress.getByName(addr);
        if (!this.multicastGroup.isMulticastAddress())
            throw new IllegalArgumentException();// l'indirizzo non è un indirizzo di multicast
        this.port = port;
    }

    // avvia il server
    public void start() {
        Random rand = new Random();
        String MSG;
        try (DatagramSocket sock = new DatagramSocket()) {
            while (true) {
                MSG = data();
                DatagramPacket dat = new DatagramPacket(MSG.getBytes(), MSG.length(), this.multicastGroup, this.port);
                sock.send(dat);
                System.out.printf("Il server ha inviato %s\n",
                        new String(dat.getData(), dat.getOffset(), dat.getLength()));
                // attende un numero di millisecondi casuale compreso tra 200 e 2000
                Thread.sleep(rand.nextInt(1800) + 200);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // metodo usato per recuperare la data e l'ora
    public String data() {
        Date d = new Date();
        return d.toString();
    }
}
