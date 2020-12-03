
//Tony Agosta 544090
import java.util.regex.Pattern;

public class MainClient {
    public static void main(String[] args) {
        String nomeserver = null;
        Integer serverport = null;
        if (args.length < 2) {
            System.out.println("Inserire nome e numero di porta del Server!");
            return;
        }

        nomeserver = args[0];
        // il nome del server deve essere composto da soli char
        if (Pattern.matches("[a-zA-Z]+", nomeserver) == false) {
            System.out.println("ERR -arg 1");
            return;
        }
        try {// mi assicuro che il numero di porta sia un intero
            serverport = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("ERR -arg 2");
            return;
        }

        Client client = new Client(nomeserver, serverport);

        client.start();

    }
}
