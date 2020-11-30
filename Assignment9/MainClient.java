import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        String nomeserver = args[0];
        Integer serverport = Integer.parseInt(args[1]);
        if (nomeserver.getClass() != String.class) {
            System.out.println("ERR -arg 1");
            return;
        }
        if (serverport.getClass() != Integer.class) {
            System.out.println("ERR -arg 2");
            return;
        }

        Client client = new Client(nomeserver, serverport);

        client.start();

    }
}
