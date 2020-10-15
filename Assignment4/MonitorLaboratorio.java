import java.util.ArrayList;
//La classe MonitorLaboratorio e` il tutor che regola gli accessi

public class MonitorLaboratorio {

    private ArrayList<String> computer;
    private boolean lockvariable = true;
    private int numprofessorieaccessi;
    private int numtesistieaccessi;
    private int indextesista;

    // costruttore
    public MonitorLaboratorio(int numprofessorieaccessi, int numtesistieaccessi, int indextesista) {
        this.computer = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            computer.add(i, "free");
        }
        this.numprofessorieaccessi = numprofessorieaccessi;
        this.numtesistieaccessi = numtesistieaccessi;
        this.indextesista = indextesista;

    }

    public synchronized void take(String flag) {
        if (flag == "stud") {
            while (!lockvariable || computerliberi() == 0) {
                try {
                    System.out.println("stud waiting");
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = true;
            this.notifyAll();
        }
        if (flag == "tes") {
            while (!lockvariable || !computertesista(indextesista)) {
                try {
                    System.out.println("tes waiting");
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = true;
            this.notifyAll();
            this.numtesistieaccessi--;

        }
        if (flag == "prof") {
            while (!lockvariable || !interolaboratoriolibero()) {
                try {
                    System.out.println("prof waiting");
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = false;
            this.notifyAll();
            this.numprofessorieaccessi--;
        }

    }

    public synchronized void rilascia(String flag) {
        if (flag == "stud") {
            while (!lockvariable) {
                System.out.println("while1");
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true;
            this.notifyAll();
        }
        if (flag == "tes") {
            while (!lockvariable) {
                System.out.println("while2");
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true;
            this.notifyAll();
        }
        if (flag == "prof") {
            while (lockvariable) {
                System.out.println("while3");
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true;
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
