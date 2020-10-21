//Tony Agosta 544090

import java.util.ArrayList;
//La classe MonitorLaboratorio e` il tutor che regola gli accessi

public class MonitorLaboratorio {

    private ArrayList<String> computer;
    private boolean lockvariable = true; // variabile condivisa per simulare la lock
    private int indextesista;

    // costruttore
    public MonitorLaboratorio(int indextesista) {
        this.computer = new ArrayList<>(20); // set di tutti i pc a "free"
        for (int i = 0; i < 20; i++) {
            computer.add(i, "free");
        }
        this.indextesista = indextesista;

    }

    // metodo synchronized che serve per mettere in attesa il thread che lo invoca
    // al verificarsi di determinate condizioni, altrimenti "prende" la
    // variabile lockvariabile
    public synchronized void take(String flag) {
        if (flag == "stud") {
            while (!lockvariable || computerliberi() == 0) {
                try {
                    System.out.printf("Lo studente %s sta aspettando\n", Thread.currentThread().getName());
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = true; // lo studente lascia la variabile lockvariable a true in modo da far
                                 // "entrare" nel laboratorio anche i tesisti lasciando la variabile
                                 // disponibile anche per essi
            this.notifyAll(); // sveglio tutti i thread in attesa in modo da notificare che la variabile è
                              // disponibile per consentire ad altri utenti di accedere
        }
        if (flag == "tes") {
            while (!lockvariable || !computertesista(indextesista)) {
                try {
                    System.out.printf("Il tesista %s sta aspettando\n", Thread.currentThread().getName());
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = true;// il tesista lascia la variabile lockvariable a true in modo da far
                                // "entrare" nel laboratorio anche gli studenti lasciando la variabile
                                // disponibile anche per essi
            this.notifyAll();// sveglio tutti i thread in attesa in modo da notificare che la variabile è
                             // disponibile per consentire ad altri utenti di accedere

        }
        if (flag == "prof") {
            while (!lockvariable || !interolaboratoriolibero()) {
                try {
                    System.out.printf("Il professore %s sta aspettando\n", Thread.currentThread().getName());
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            lockvariable = false; // il professore setta la variabile lockvariable a false per impedire agli altri
                                  // utenti di accedere al laboratorio
            this.notifyAll();
        }

    }

    // metodo synchronized che serve per impostare la variabile condivisa
    // lockvariable a true o false in base al thread che lo invoca
    public synchronized void rilascia(String flag) {
        if (flag == "stud") {
            while (!lockvariable) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true; // set a true della variabile condivisa lockvariable per permettere ad altri
                                 // thread di occuparla
            this.notifyAll(); // sveglio i thread in attesa per avvisarli che la variabile condivisa è
                              // disponibile
        }
        if (flag == "tes") {
            while (!lockvariable) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true;// set a true della variabile condivisa lockvariable per permettere ad altri
                                // thread di occuparla
            this.notifyAll();// sveglio i thread in attesa per avvisarli che la variabile condivisa è
                             // disponibile
        }
        if (flag == "prof") {
            while (lockvariable) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lockvariable = true;// set a true della variabile condivisa lockvariable per permettere ad altri
                                // thread di occuparla
            this.notifyAll();// sveglio i thread in attesa per avvisarli che la variabile condivisa è
                             // disponibile
        }
    }

    // metodo che restituisce true se tutti i pc sono etichettati come free
    // altrimenti restituisce false
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
