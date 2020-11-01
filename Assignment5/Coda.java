// Tony Agosta 544090

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class Coda {
    private LinkedList<File> directories;
    final Lock lockcoda;
    final Condition notEmpty;

    public Coda(LinkedList<File> directories) throws NullPointerException {
        if (directories == null)
            throw new NullPointerException();
        lockcoda = new ReentrantLock();
        notEmpty = lockcoda.newCondition();
        this.directories = directories;

    }

    // metodo invocato dal produttore per aggiungere nuove directory nella
    // linkedlist
    public void produce(File fileEntry) throws NullPointerException {
        if (fileEntry == null)
            throw new NullPointerException();
        directories.add(0, fileEntry);
        System.out.println("Aggiunta directory: " + fileEntry.getName());

    }

    // metodo invocato dai consumatori per stampare i file presenti nella directory
    // passata come argomento
    public void consume(File[] file) throws NullPointerException {
        if (file == null)
            throw new NullPointerException();
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

    // metodo che aggiunge in coda alla linkedlist il file passato come argomento
    public void addfinal(File finalfile) throws NullPointerException {
        if (finalfile == null)
            throw new NullPointerException();
        directories.add(finalfile);
    }

}
