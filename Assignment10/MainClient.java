
//Tony Agosta 544090
import java.net.UnknownHostException;

public class MainClient {

    public static void main(String[] args) {
        String DEFAULT_DATE_GROUP = "239.255.1.3";// indirizzo di broadcast

        int DEFAULT_DATE_PORT = 30000; // porta associata all'indirizzo di multicast
        try {
            // crea e avvia il Client
            Client client = new Client(DEFAULT_DATE_GROUP, DEFAULT_DATE_PORT);
            client.start();
        } catch (UnknownHostException e) { // l'indirizzo passato non è valido
            System.err.println("L'indirizzo immesso non e' valido");
        } catch (IllegalArgumentException e) { // l'indirizzo passato non è un indirizzo multicast
            System.err.println("L'indirizzo immesso non e' un indirizzo multicast");
        }
    }
}