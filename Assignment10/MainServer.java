
//Tony Agosta 544090
import java.net.UnknownHostException;

public class MainServer {

    public static void main(String[] args) {
        String DEFAULT_DATE_GROUP; // indirizzo del gruppo di multicast
        try {
            DEFAULT_DATE_GROUP = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Inserire l'indirizzo IP di DATEGROUP");
            return;
        }
        DEFAULT_DATE_GROUP = args[0];

        int DEFAULT_DATE_PORT = 30000; // porta associata all'indirizzo di multicast
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