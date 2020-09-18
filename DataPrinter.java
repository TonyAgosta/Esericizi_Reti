import java.util.Calendar;

public class DataPrinter {
    public static void main(String[] args) {
        Calendar dat = Calendar.getInstance();
        while (true) {
            System.out.println(dat.getTime());
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException E) {
            }

        }
       
    }
}
