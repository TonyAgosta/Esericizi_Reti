import java.util.*;

public class Task implements Runnable {
    private String name;

    public Task(String name) throws NullPointerException {
        if (name == null)
            throw new NullPointerException();
        this.name = name;
    }

    public void run() {
        System.out.printf("%s: task: %s \n", Thread.currentThread().getName(), name);
        try {
            Long duration = (long) (Math.random() * 10); //decido in modo randomico la durata di ogni task
            System.out.printf("%s: task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name,
                    duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task Finished %s \n", Thread.currentThread().getName(), name);
    }
}
