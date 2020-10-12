import java.util.concurrent.locks.*;

public class Utenti extends Thread {
    private int priority;
    private int access;
    private int totaleutenti;
    private int indextesista; // indice del computer usati dai tesisti
    final Lock lockcoda;
    final Condition notFull;
    final Condition notEmpty;
    private Laboratorio lab;

    public Utenti(int priority, int access, int totaleutenti, Laboratorio lab) {
        this.priority = priority;
        this.access = access; // numero degli accessi al laboratorio che fa ogni utente
        this.totaleutenti = totaleutenti;// numero totale di utenti che devono accedere al laboratorio
        this.indextesista = (int) (Math.random() * 20);
        lockcoda = new ReentrantLock();
        notFull = lockcoda.newCondition();
        notEmpty = lockcoda.newCondition();
        this.lab = lab;
    }

    public void run() {

        int j;
        for (int i = 0; i < totaleutenti; i++) {

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
                System.out.printf("Lo studente %s ha avuto accesso al laboratorio\n", Thread.currentThread().getName());
                lab.lockcoda.unlock();
                lab.occupasingolopc(j); // set del pc appeno occupato a notfree
                for (int l = 0; l < access; l++) {
                    try {
                        Thread.currentThread().sleep(3000); // uso la sleep per simulare l'utilizzo del computer
                                                            // compresi i momenti in cui esce dal laboratorio
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lab.lockcoda.lock();
                lab.liberapc(j);// set a free del computer dopo aver finito di utilizzarlo
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
                System.out.printf("Il tesista %s ha avuto accesso al laboraotrio\n", Thread.currentThread().getName());
                lab.lockcoda.unlock();
                lab.occupasingolopc(indextesista); // set del pc richiesto dal tesista a notfree
                for (int l = 0; l < access; l++) {
                    try {
                        Thread.currentThread().sleep(3000); // uso la sleep per simulare l'utilizzo del computer
                                                            // compresi i momenti in cui esce dal laboratorio
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lab.lockcoda.lock();
                lab.liberapc(indextesista);// set a free del computer dopo aver finito di utilizzarlo
                lab.tesistaFree.signal();
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
                lab.lockcoda.unlock();
                lab.occupacomputer();// set di tutti i computer a notfree
                for (int l = 0; l < access; l++) {
                    try {
                        Thread.currentThread().sleep(3000); // uso la sleep per simulare l'utilizzo del computer
                                                            // compresi i momenti in cui esce dal laboratorio
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lab.lockcoda.lock();
                lab.liberalaboratorio();
                lab.labFull.signalAll(); // signalAll per seganalare a tutti che i computer sono tutti liberi
                lab.lockcoda.unlock();
            }

        }

    }
}