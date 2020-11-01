import java.io.File;
import java.util.LinkedList;

public class Consumatore extends Thread {

    private LinkedList<File> directories;
    private Coda coda;

    public Consumatore(Coda coda, LinkedList<File> directories) {
        this.coda = coda;
        this.directories = directories;
    }

    public void run() {

        boolean b = true;
        System.out.printf("Il Thread: %s sta lavorando\n", Thread.currentThread().getName());

        while (b) {
            coda.lockcoda.lock();
            while (directories.isEmpty()) {
                try {
                    coda.notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // System.out.println(directories.get(1).getName());
            if (directories.peek().getName() == "finalfile") {
                coda.lockcoda.unlock();
                b = false;
                System.out.println("Il " + Thread.currentThread().getName() + " ha finito");
                return;

            }
            if (directories.size() > 0) {
                File namedirectory = directories.get(0);
                File[] fileindirectory = directories.remove(0).listFiles();

                // System.out.println("Sono nella directory: " + namedirectory.getName());
                coda.consume(fileindirectory);
                coda.lockcoda.unlock();
            }

        }

    }

}