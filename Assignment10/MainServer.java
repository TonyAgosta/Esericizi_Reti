
//Tony Agosta 544090
import java.net.UnknownHostException;

public class MainServer {

    // indirizzo del gruppo di multicast
    final static String DEFAULT_DATE_GROUP = "239.255.1.3";

    // porta associata all'indirizzo di multicast

    final static int DEFAULT_DATE_PORT = 30000;

    public static void main(String[] args) {

        try {
            // crea e avvia il Server
            Server server = new Server(DEFAULT_DATE_GROUP, DEFAULT_DATE_PORT);
            server.start();
        } catch (UnknownHostException e) { // l'indirizzo passato non è valido
            System.err.println("L'indirizzo immesso non e' valido");
        } catch (IllegalArgumentException e) { // l'indirizzo passato non è un indirizzo multicast
            System.err.println("L'indirizzo immesso non e' un indirizzo multicast");
        }
    }

}