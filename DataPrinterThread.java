import java.util.Calendar;

public class DataPrinterThread extends Thread {

    public void run() {
        while (true) {
            Calendar dat = Calendar.getInstance();
            System.out.println(dat.getTime());
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException E) {
            }
        }
    }

    public static void main(String[] args) {
        DataPrinterThread threadprinter= new DataPrinterThread();
        threadprinter.start();
        System.out.println(Thread.currentThread().getName());
    }
}
