//Tony Agosta 544090

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numstudenti, numtesisti, numprofessori;
        int totaleutenti; // variabile per salvare il numero totale di thread da attivare
        int i, indextesista;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire il numero di studenti,tesisti e Professori:");
        numstudenti = scan.nextInt(); // numero di studenti che accedono al laboratorio
        numtesisti = scan.nextInt(); // numero di tesisti che accedono al laboratorio
        numprofessori = scan.nextInt();// numero di professori che accedono al laboratorio
        scan.close();
        indextesista = (int) (Math.random() * 20); // selezione di un numero random tra 0 e 20 per scegliere il pc a cui
                                                   // devono lavorare i tesisti
        totaleutenti = numprofessori + numstudenti + numtesisti; // numero totale di utenti che accedono al laboratorio
        int accesstudenti = (int) (Math.random() * 5) + 1; // selezione di un numero random per determinare il numero di
                                                           // accessi al laboratorio che fa ogni studente
        int accesstesisti = (int) (Math.random() * 5) + 1; // selezione di un numero random per determinare il numero di
                                                           // accessi al laboratorio che fa ogni tesista
        int accessprofessori = (int) (Math.random() * 5) + 1; // selezione di un numero random per determinare il numero
                                                              // di accessi al laboratorio che fa ogni professore

        MonitorLaboratorio ML = new MonitorLaboratorio(indextesista);
        Studenti[] studenti = new Studenti[numstudenti]; // array di tipo Studenti che contiene i thread di tipo
                                                         // "Studenti"
        Tesisti[] tesisti = new Tesisti[numtesisti]; // array di tipo Tesisti che contiene i thread di tipo "Tesisti"
        Professori[] professori = new Professori[numprofessori];// array di tipo Professori che contiene i thread di
                                                                // tipo "Professori"
        System.out.println("accessi stud=" + accesstudenti);
        for (i = 0; i < numstudenti; i++) {// inserisco i thread di tipo "Studenti" nel relativo array
            studenti[i] = new Studenti(ML, accesstudenti);// argomenti : monitor e numero di accessi
        }

        System.out.println("accessi tes=" + accesstesisti);
        for (i = 0; i < numtesisti; i++) {// inserisco i thread di tipo "Tesisti" nel relativo array
            tesisti[i] = new Tesisti(ML, indextesista, accesstesisti);// argomenti : monitor, indici del pc da
                                                                      // utilizzare e numero di accessi
        }
        System.out.println("accessi prof=" + accessprofessori);
        for (i = 0; i < numprofessori; i++) {// inserisco i thread di "Professori" nel relativo array
            professori[i] = new Professori(ML, accessprofessori); // argomenti : monitor e numero di accessi
        }

        for (i = 0; i < totaleutenti; i++) { // start di tutti i thread
            if (i < numprofessori)
                professori[i].start();
            if (i < numtesisti)
                tesisti[i].start();
            if (i < numstudenti)
                studenti[i].start();
        }
    }
}