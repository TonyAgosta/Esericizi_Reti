//Tony Agosta 544090
public class MainServer {
    public static void main(String[] args) {
        Integer numporta = null;
        long seed = 0;
        // Se il numero degli argomenti e` uguale a 2 allora e` stato inserito anche
        // l'argomento opzionale seed da usare nel server
        if (args.length == 2) {
            // mi assicuro che siano entrambi degli interi
            try {
                numporta = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("ERR -arg 0");
                return;
            }
            try {
                seed = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("ERR -arg 1");
                return;
            }
            Server svr = new Server(numporta, seed);
            svr.start();
        } else {// e` stato inserito un solo argomento: la porta del server
            try {// mi assicuro che sia un intero
                numporta = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("ERR -arg 0");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inserire il numero di porta del server e opzionalmente un seed");
                return;
            }
            Server svr = new Server(numporta, 0);
            svr.start();
        }
    }
}