import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Laboratorio {
    final ReentrantLock lockcoda;
    final Condition pcFree;
    final Condition labFull;
    final Condition tesistaFree;
    ArrayList<String> computer;

    // costruttore
    public Laboratorio() {
        this.computer = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            computer.add(i, "free");
        }
        lockcoda = new ReentrantLock();
        pcFree = lockcoda.newCondition();
        labFull = lockcoda.newCondition();
        tesistaFree = lockcoda.newCondition();
    }

    // metodo che controlla se l'intero laboratorio e` occupato o meno
    // e restitusce true se trova tutti i computer liberi, false se ne trova
    // almeno uno occupato
    public boolean interolaboratoriolibero() {
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

    // controllo che il computer richisto dal tesista sia libero
    public boolean computertesista(int index) {
        if (computer.get(index).equals("free"))
            return true;
        else
            return false;
    }

    // restituisco l'indice del primo pc libero
    public int computerlibero() {
        for (int i = 0; i < 20; i++) {
            if (computer.get(i).equals("free"))
                return i;
        }
        return 404; // da sistemare
    }

    // set di tutti i computer del laboratorio a notfree
    public void occupacomputer() {
        for (int i = 0; i < 20; i++)
            computer.set(i, "notfree");
    }

    // set del computer ,identificato dall'indice passato come argomento, a notfree
    public void occupasingolopc(int indicepcoccupato) {
        computer.set(indicepcoccupato, "notfree");
    }

    // set del computer,identificato dall'indice passato come argomento, a free
    public void liberapc(int pcdaliberare) {
        computer.set(pcdaliberare, "free");
    }

    // set di tutti i computer a free
    public void liberalaboratorio() {
        for (int i = 0; i < 20; i++) {
            computer.set(i, "free");
        }
    }
}
