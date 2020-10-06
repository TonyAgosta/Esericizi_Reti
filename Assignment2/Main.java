import java.util.concurrent.ArrayBlockingQueue;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        int p = (int) (Math.random() * 20); // utilizzo un numero random per decidere quanti task (persone) far
                                            // entrare nella sala d'attesa
        int k = (int) (Math.random() * p);// utilizzo un numero random per decidere la dimensione del gruppo di
                                          // task(persone) che possono entrare nella seconda sala

        // stampo il numero di task che entrano nella prima sala e la dimensione del
        // gruppo di persone che possono entrare nella seconda sala
        System.out.println("Il numero di task che verranno creati e`:" + p);
        System.out.printf("Nella seconda sala entrano %d persone per volta\n", k);
        if (p <= 0 || k <= 0)
            throw new IllegalArgumentException("Invalid size of p or k ");
        Ufficio ufficio = new Ufficio(k); // creo un oggetto di tipo Ufficio per gestire la seconda sala
        ArrayBlockingQueue<Runnable> salaattesa = new ArrayBlockingQueue<>(p);

        for (int i = 0; i < p; i++) {// in ogni posizione dell'array salvo un task
            salaattesa.add(new Task("Task" + i));

        }

        for (int i = 0; i < p; i++) {
            ufficio.executeTask(salaattesa.poll());
            /*
             * try { Thread.sleep(5000); // faccio una sleep in modo da sfruttare al meglio
             * il riuso } catch (InterruptedException e) { e.printStackTrace(); }
             */
        }
        ufficio.endUfficio();// termino
    }
}
