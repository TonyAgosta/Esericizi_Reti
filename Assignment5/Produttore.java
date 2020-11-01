import java.io.File;
import java.util.LinkedList;

public class Produttore extends Thread {

    private LinkedList<File> directories;
    private File startDirectory;
    private Coda coda;

    public Produttore(LinkedList<File> directories, File startDirectory, Coda coda) {
        this.directories = directories;
        this.startDirectory = startDirectory;
        this.coda = coda;
    }

    public void run() {

        File finalfile = new File("finalfile");
        addDirectory(startDirectory);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        coda.lockcoda.lock();
        coda.addfinal(finalfile);
        coda.notEmpty.signalAll();
        coda.lockcoda.unlock();

    }

    public void addDirectory(File dir) {
        File[] filein = dir.listFiles();

        for (File file : filein) {
            if (file.isDirectory()) {
                coda.lockcoda.lock();
                coda.produce(file);
                coda.notEmpty.signalAll();
                coda.lockcoda.unlock();
                addDirectory(file);
            }
        }
    }
}
