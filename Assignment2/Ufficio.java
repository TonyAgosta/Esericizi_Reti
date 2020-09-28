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
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }

    public void executeTask(Task[] task, int k) {
       // System.out.println("qui1");
        for (int i = 0; i < k; i++) {
            if(Array.get(task, i) != null)
                executor.execute(task[i]);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // executor.execute(task[0]);
    }

    public void endUfficio() {
        executor.shutdown();
    }

}
