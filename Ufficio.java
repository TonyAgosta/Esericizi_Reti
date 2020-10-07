
// Tony Agosta 544090
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ufficio {
    private ThreadPoolExecutor executor;
    private ArrayBlockingQueue<Runnable> coda;

    public Ufficio(int k) {
        this.coda = new ArrayBlockingQueue<>(k);// arrayblockingqueue per gestire la coda dei task che arrivano nel
                                                // threadpool
        this.executor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.MILLISECONDS, coda);// threadpool con core di
                                                                                       // dimensione 4
    }

    public void executeTask(Object task) {
        System.out.println("Nella sala piccola e`arrivato un nuovo task");
        this.executor.execute((Runnable) task); // vengono eseguiti i task

        try {
            Thread.sleep(2000); // faccio una sleep in modo da sfruttare al meglio il riuso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void endUfficio() {

        this.executor.shutdown();
        while (!executor.isTerminated()) { // attendo che l'executor esegua tutti i task e mi metto in attesa della sua
                                           // terminazione

        }
        System.out.println("L'ufficio e` stato chiuso");
    }

}
