import java.util.concurrent.locks.*;

public class Utenti extends Thread {
    private int priority;
    private int access;
    // private int totaleutenti;
    private int indextesista; // indice del computer usati dai tesisti
    private Laboratorio lab;

    // costruttore
    public Utenti(int priority, int access, int indextesista, Laboratorio lab) {
        this.priority = priority;
        this.access = access; // numero degli accessi al laboratorio che fa ogni utente
        // this.totaleutenti = totaleutenti;// numero totale di utenti che devono
        // accedere al laboratorio
        this.indextesista = indextesista;
        this.lab = lab;
    }

    public void run() {

        int j;
        for (int i = 0; i < access; i++) {

            // studente
            if (this.priority == 1) {
                lab.lockcoda.lock();
                while (lab.computerliberi() == 0) {
                    try {
                        lab.pcFree.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                j = lab.computerlibero();// indice del primo computer constrassegnato come free
                while (j == 404) {
                    j = lab.computerlibero();
                }
                System.out.printf("Lo studente %s ha occupato il computer %d\n", Thread.currentThread().getName(), j);

                lab.occupasingolopc(j); // set del pc appeno occupato a notfree
                lab.lockcoda.unlock();
                try {
                    Thread.currentThread().sleep(3000); // uso la sleep per simulare l'utilizzo del computer
                                                        // compresi i momenti in cui esce dal laboratorio
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.printf("Lo studente %s ha liberato il computer %d\n", Thread.currentThread().getName(), j);
                lab.lockcoda.lock();
                lab.liberapc(j);// set a free del computer dopo aver finito di utilizzarlo
                lab.labFull.signal();
                lab.tesistaFree.signal();
                lab.pcFree.signal();

                lab.lockcoda.unlock();
            }
            // tesista
            else if (this.priority == 2) {
                lab.lockcoda.lock();
                while (!lab.computertesista(indextesista)) {
                    try {
                        lab.tesistaFree.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("Il tesista %s ha occupato il computer numero %d\n", Thread.currentThread().getName(),
                        indextesista);

                lab.occupasingolopc(indextesista); // set del pc richiesto dal tesista a notfree
                lab.lockcoda.unlock();
                try {
                    Thread.currentThread().sleep(3000); // uso la sleep per simulare l'utilizzo del computer
                                                        // compresi i momenti in cui esce dal laboratorio
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Il tesista %s ha liberato il computer numero %d\n", Thread.currentThread().getName(),
                        indextesista);
                lab.lockcoda.lock();
                lab.liberapc(indextesista);// set a free del computer dopo aver finito di utilizzarlo

                lab.labFull.signal();
                lab.tesistaFree.signal();
                lab.pcFree.signal();
                lab.lockcoda.unlock();

            }

            // professore
            else if (this.priority == 3) {
                lab.lockcoda.lock();
                while (!lab.interolaboratoriolibero()) {
                    try {
                        lab.labFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("Il professore %s ha occupato tutto il laboratorio\n",
                        Thread.currentThread().getName());
                lab.occupacomputer();// set di tutti i computer a notfree
                lab.lockcoda.unlock();

                try {
                    Thread.currentThread().sleep(3000); // sleep per simulare l'utilizzo del computer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Il professore %s ha liberato tutto il laboratorio\n",
                        Thread.currentThread().getName());
                lab.lockcoda.lock();
                lab.liberalaboratorio();
                lab.labFull.signalAll();
                lab.tesistaFree.signalAll();
                lab.pcFree.signalAll();
                lab.lockcoda.unlock();
            }

        }
    }
}