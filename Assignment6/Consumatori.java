//Tony Agosta 544090

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//La classe Consumatori viene utilizzata per gestire,tramite un threadpool con core 4,  gli oggetti che gli vengono passati

public class Consumatori {
    private ThreadPoolExecutor executor;
    private LinkedBlockingQueue<Runnable> coda;

    public Consumatori() {

        this.coda = new LinkedBlockingQueue<>();// linkedblockingqueue per gestire la coda dei task che arrivano nel
                                                // threadpool
        this.executor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.MILLISECONDS, coda);// threadpool con core di
                                                                                       // dimensione 4
    }

    public void esecuzione(Task cc) {

        // esecuzione dei task
        executor.execute(cc);
    }

    // metodo invocato per terminare il thredpool
    public void endpool() {

        this.executor.shutdown();
        while (!executor.isTerminated()) { // attendo che l'executor esegua tutti i task e mi metto in attesa della sua
                                           // terminazione

        }
    }
}
