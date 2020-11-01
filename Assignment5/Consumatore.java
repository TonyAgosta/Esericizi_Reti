// Tony Agosta 544090

import java.io.File;
import java.util.LinkedList;

public class Consumatore extends Thread {

    private LinkedList<File> directories;
    private Coda coda;

    public Consumatore(Coda coda, LinkedList<File> directories) throws NullPointerException {
        if (coda == null || directories == null)
            throw new NullPointerException();
        this.coda = coda;
        this.directories = directories;
    }

    public void run() {

        boolean b = true; // variabile boolean usata per controllare se la linkedlist della directory e`
                          // vuota. b=false la lista e` vuota, b=true la lista non e` vuota
        System.out.printf("Il Thread: %s sta lavorando\n", Thread.currentThread().getName());

        while (b) {
            coda.lockcoda.lock();
            while (directories.isEmpty()) { // attendo fin quando non c'e` un elemento nella linkedlist
                try {
                    coda.notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (directories.peek().getName() == "finalfile") {
                coda.lockcoda.unlock();
                b = false;
                System.out.println("Il " + Thread.currentThread().getName() + " ha finito");
                return;

            }
            if (directories.size() > 0) {
                File[] fileindirectory = directories.remove(0).listFiles();
                coda.consume(fileindirectory);
                coda.lockcoda.unlock();
            }

        }

    }

}