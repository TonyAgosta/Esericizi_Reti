//Tony Agosta 544090

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Consumatori {
    private ThreadPoolExecutor executor;
    private LinkedBlockingQueue<Runnable> coda;

    public Consumatori() {

        this.coda = new LinkedBlockingQueue<>();// arrayblockingqueue per gestire la coda dei task che arrivano nel
                                                // threadpool
        this.executor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.MILLISECONDS, coda);// threadpool con core di
                                                                                       // dimensione 4
    }

    public void esecuzione(Task cc) {

        executor.execute(cc);
    }

    public void endpool() {

        this.executor.shutdown();
        while (!executor.isTerminated()) { // attendo che l'executor esegua tutti i task e mi metto in attesa della sua
                                           // terminazione

        }
    }
}
