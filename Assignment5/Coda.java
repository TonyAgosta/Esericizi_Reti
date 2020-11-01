import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Coda {
    private LinkedList<File> directories;
    final Lock lockcoda;
    final Condition notEmpty;

    public Coda(LinkedList<File> directories) {
        lockcoda = new ReentrantLock();
        notEmpty = lockcoda.newCondition();
        this.directories = directories;

    }

    public void produce(File fileEntry) {

        directories.add(0, fileEntry);
        System.out.println("Aggiunta directory: " + fileEntry.getName());

    }

    public void consume(File[] file) {

        for (File fncd : file) {
            if (fncd.isFile())
                System.out.println("File: " + fncd.getName());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    // }

    public void addfinal(File finalfile) {
        int size = directories.size();
        directories.add(finalfile);
    }

}
