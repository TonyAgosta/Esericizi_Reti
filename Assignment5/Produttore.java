// Tony Agosta 544090

import java.io.File;

public class Produttore extends Thread {

    private File startDirectory;
    private Coda coda;

    public Produttore(File startDirectory, Coda coda) throws NullPointerException {
        if (startDirectory == null || coda == null)
            throw new NullPointerException();
        this.startDirectory = startDirectory;
        this.coda = coda;
    }

    public void run() {

        File finalfile = new File("finalfile"); // nuovo file che viene aggiunto alla fine della linkelist per segnalare
                                                // ai consumatori che i file e le directory sono finite
        addDirectory(startDirectory);// inizio con l'aggiungere la directory di partenza
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        coda.lockcoda.lock();
        coda.addfinal(finalfile);// aggiungo il file finale in coda
        coda.notEmpty.signalAll();
        coda.lockcoda.unlock();

    }

    // metodo ricorsivo che aggiunge le directory e sottodirectory presenti
    public void addDirectory(File dir) throws NullPointerException {
        if (dir == null)
            throw new NullPointerException();
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
