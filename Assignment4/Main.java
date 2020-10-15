//Tony Agosta 544090

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numstudenti, numtesisti, numprofessori;
        int totaleutenti; // variabile per salvare il numero totale di thread da attivare
        int i, indextesista;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire il numero di studenti,tesisti e Professori:");
        numstudenti = scan.nextInt();
        numtesisti = scan.nextInt();
        numprofessori = scan.nextInt();
        scan.close();
        indextesista = (int) (Math.random() * 20);
        totaleutenti = numprofessori + numstudenti + numtesisti;
        int accesstudenti = (int) (Math.random() * 5) + 1;
        int accesstesisti = (int) (Math.random() * 5) + 1;
        int accessprofessori = (int) (Math.random() * 5) + 1;

        MonitorLaboratorio ML = new MonitorLaboratorio(numprofessori * accessprofessori, numtesisti * accesstesisti,
                indextesista);
        Studenti[] studenti = new Studenti[numstudenti];
        Tesisti[] tesisti = new Tesisti[numtesisti];
        Professori[] professori = new Professori[numprofessori];
        System.out.println("accessi stud=" + accesstudenti);
        for (i = 0; i < numstudenti; i++) {
            studenti[i] = new Studenti(ML, numtesisti * accesstesisti, numprofessori * accessprofessori, accesstudenti);
        }

        System.out.println("accessi tes=" + accesstesisti);
        for (i = 0; i < numtesisti; i++) {
            tesisti[i] = new Tesisti(ML, indextesista, numprofessori * accessprofessori, accesstesisti);
        }
        System.out.println("accessi prof=" + accessprofessori);
        for (i = 0; i < numprofessori; i++) {
            professori[i] = new Professori(ML, accessprofessori);
        }

        for (i = 0; i < totaleutenti; i++) {
            if (i < numprofessori)
                professori[i].start();
            if (i < numtesisti)
                tesisti[i].start();
            if (i < numstudenti)
                studenti[i].start();
        }
    }
}