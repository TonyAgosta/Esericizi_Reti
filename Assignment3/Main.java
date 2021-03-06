import java.util.ArrayList;
import java.util.Scanner;

//Tony Agosta 544090

//Assumo che il main sia il tutor che coordina gli accessi al laboratorio

public class Main {
    public static void main(String[] args) {
        int numstudenti, numtesisti, numprofessori;
        int totaleutenti; // variabile per salvare il numero totale di thread da attivare
        int i, priority, indextesista;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire il numero di studenti,tesisti e Professori:");
        numstudenti = scan.nextInt();
        numtesisti = scan.nextInt();
        numprofessori = scan.nextInt();
        scan.close();
        indextesista = (int) (Math.random() * 20);
        totaleutenti = numprofessori + numstudenti + numtesisti;

        ArrayList<Utenti> codautenti = new ArrayList<>();
        Laboratorio laboratorio = new Laboratorio();

        // Assumo che ha la precedenza sugli altri il thread che ha il valore di
        // priority associato piu` alto
        int k = (int) (Math.random() * 5) + 1; // numero di accessi di ogni professore
        System.out.println("Il numerod i accessi dei professori e`:" + k);
        priority = 3;// associo priorita` 3 ai professori
        for (i = 0; i < numprofessori; i++) {
            codautenti.add(new Utenti(priority, k, indextesista, laboratorio));
        }
        k = (int) (Math.random() * 5) + 1; // numero di accessi di ogni tesista
        System.out.println("Il numerod i accessi dei tesisti e`:" + k);
        priority = 2;// associo priorita` 2 ai tesisti
        for (i = 0; i < numtesisti; i++) {
            codautenti.add(new Utenti(priority, k, indextesista, laboratorio));
        }
        k = (int) (Math.random() * 5) + 1; // numero di accessi di ogni studente
        System.out.println("Il numerod i accessi degli studenti e`:" + k);
        priority = 1;// associo priorita` 1 agli studentis
        for (i = 0; i < numstudenti; i++) {
            codautenti.add(new Utenti(priority, k, indextesista, laboratorio));
        }

        // faccio partire i thread
        for (i = 0; i < totaleutenti; i++) {
            codautenti.get(i).start();

        }
    }
}