import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ufficio {
    private ThreadPoolExecutor executor;
    private ArrayBlockingQueue<Runnable> coda;

    public Ufficio(int k) {
        this.coda = new ArrayBlockingQueue<>(k);
        this.executor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.MILLISECONDS, coda);
    }

    public void executeTask(Object task) {
        System.out.println("Nella sala piccola e`arrivato un nuovo task");
        this.executor.execute((Runnable) task);

        try {
            Thread.sleep(5000); // faccio una sleep in modo da sfruttare al meglio il riuso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void endUfficio() {
        this.executor.shutdown();
    }

}
