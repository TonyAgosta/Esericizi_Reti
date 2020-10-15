import java.util.ArrayList;
//La classe MonitorLaboratorio e` il tutor che regola gli accessi

public class MonitorLaboratorio {

    ArrayList<String> computer;

    // costruttore
    public MonitorLaboratorio() {
        this.computer = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            computer.add(i, "free");
        }

    }

    public synchronized void take(String flag, int indextesista) {
        if (flag == "stud") {
            while (computerliberi() == 0) {
                try {
                    this.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int j = computerlibero();
            while (j == 404) {
                j = computerlibero();
            }
            occupasingolopc(j);
            System.out.printf("Lo studente %s ha occupato il pc numero %d\n", Thread.currentThread().getName(), j);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            liberapc(j);
            System.out.printf("Lo studente %s ha liberato il pc numero %d\n", Thread.currentThread().getName(), j);
            this.notifyAll();
        }

        if (flag == "tes") {
            while (!computertesista(indextesista)) {
                try {
                    this.wait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("il tesista %s ha occupato  il pc numero %d\n", Thread.currentThread().getName(),
                    indextesista);
            occupasingolopc(indextesista);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            liberapc(indextesista);
            System.out.printf("il tesista %s ha liberato il pc numero %d\n", Thread.currentThread().getName(),
                    indextesista);
            this.notifyAll();
        }
        if (flag == "prof") {
            while (!interolaboratoriolibero()) {
                try {
                    this.wait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("il professore %s ha occupato il laboratorio\n", Thread.currentThread().getName());
            occupacomputer();
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            liberalaboratorio();
            System.out.printf("il professore %s ha liberato il laboratorio\n", Thread.currentThread().getName());
            this.notifyAll();
        }
    }

    public synchronized boolean interolaboratoriolibero() {

        for (int i = 0; i < 20; i++) {
            if (computer.get(i).equals("notfree"))
                return false;
        }
        return true;
    }

    // metodo che restituisce il numero di computer liberi
    public int computerliberi() {
        int numeropcliberi = 0;// variabile in cui salvo il numero totale di pc liberi
        for (int i = 0; i < 20; i++)
            if (computer.get(i).equals("free"))
                ;
        numeropcliberi++;
        return numeropcliberi;
    }

    // controllo che il computer richisto dal tesista sia libero e restituisco true
    // se e` libero false altrimenti
    public synchronized boolean computertesista(int index) {
        if (computer.get(index).equals("free"))
            return true;
        else
            return false;
    }

    // restituisco l'indice del primo pc libero
    public synchronized int computerlibero() {
        for (int i = 0; i < 20; i++) {
            if (computer.get(i).equals("free"))
                return i;
        }
        return 404; // da sistemare
    }

    // set di tutti i computer del laboratorio a notfree
    public synchronized void occupacomputer() {
        for (int i = 0; i < 20; i++)
            computer.set(i, "notfree");
    }

    // set del computer ,identificato dall'indice passato come argomento, a notfree
    public synchronized void occupasingolopc(int indicepcoccupato) {
        computer.set(indicepcoccupato, "notfree");
    }

    // set del computer,identificato dall'indice passato come argomento, a free
    public synchronized void liberapc(int pcdaliberare) {
        computer.set(pcdaliberare, "free");
    }

    // set di tutti i computer a free
    public synchronized void liberalaboratorio() {
        for (int i = 0; i < 20; i++) {
            computer.set(i, "free");
        }
    }
}
