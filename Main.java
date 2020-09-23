public class Main {
    public static void main(String[] args) {
        float accu = Float.valueOf(args[0]).floatValue(); // per determinare il grado di accuratezza del calcolo
        float attesath = Float.valueOf(args[1]).floatValue();// per determinare il massimo tempo di attesa in millisecondi
        PigrecoThread pigreco= new PigrecoThread(accu);
        pigreco.start(); // faccio partire il trhead che si occupa del calcolo del pigreco
        try {
           pigreco.join((long) attesath); //metto in attesa il main
           System.out.println("pigreco calcolato");
        } catch (InterruptedException e) {
            System.out.println("Timer");
        }
    }
}
