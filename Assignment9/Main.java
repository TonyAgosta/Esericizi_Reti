public class Main {
    public static void main(String[] args) {
        Integer numporta = Integer.parseInt(args[0]);
        if (numporta.getClass() != Integer.class) {
            System.out.println("ERR -arg 1");
            return;
        }
        Server svr = new Server(numporta);
        svr.start();
    }
}