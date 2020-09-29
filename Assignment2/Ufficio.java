import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.*;
import java.util.concurrent.*;
import java.util.*;
import java.lang.reflect.Array;
import java.lang.*;
import java.lang.Math;

public class Ufficio {
    private ThreadPoolExecutor executor;

    public Ufficio() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);// creo un threadpool di dimensione fissa=4
    }

    public void executeTask(Task[] task, int k) {// metodo che manda in esecuzione i task
        for (int i = 0; i < k; i++) {
            if (Array.get(task, i) != null)
                executor.execute(task[i]);
            try {
                Thread.sleep(5000); // faccio una sleep in modo da sfruttare al meglio il riuso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void endUfficio() {
        executor.shutdown();
    }

}
