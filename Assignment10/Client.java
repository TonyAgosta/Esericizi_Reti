
//Tony Agosta 544090
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client {

    // indirizzo del gruppo di multicast
    private InetAddress DateGroup;

    // porta associata all'indirizzo multicast
    private int port;

    // addr e` indirizzo del gruppo di multicast
    // port la porta a cui associare il socket di multicast
    // @throws UnknownHostException se l'indirizzo non è valido
    public Client(String addr, int port) throws UnknownHostException, IllegalArgumentException {
        this.DateGroup = InetAddress.getByName(addr);
        // verifica che l'indirizzo passato come argomento sia valido
        if (!this.DateGroup.isMulticastAddress())
            throw new IllegalArgumentException();// non è un indirizzo di multicast
        this.port = port;
    }

    // avvia il client
    public void start() {
        try (MulticastSocket multicastDateClient = new MulticastSocket(this.port)) {
            int MSG_LENGTH = data().length();// prendo la data in modo da conoscere la lunghezza del messaggio in arrivo
            multicastDateClient.joinGroup(this.DateGroup);
            for (int i = 0; i < 10; i++) {
                DatagramPacket dat = new DatagramPacket(new byte[MSG_LENGTH], MSG_LENGTH);
                multicastDateClient.receive(dat);
                System.out.printf("Ho ricevuto %s dal server\n",
                        new String(dat.getData(), dat.getOffset(), dat.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo usato per recuperare la data e l'ora
    public String data() {
        Date d = new Date();
        return d.toString();
    }

}
