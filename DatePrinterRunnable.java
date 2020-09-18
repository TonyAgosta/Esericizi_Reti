import java.util.Calendar;

public class DatePrinterRunnable implements Runnable {
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
        DatePrinterRunnable threadrunnable = new DatePrinterRunnable();
        Thread tr = new Thread(threadrunnable, "tr");
        tr.start();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException E) {
        }
    }
}
