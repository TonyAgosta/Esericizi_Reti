//Tony Agosta 544090

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

//La classe produttore ha il compito di leggere dal file json gli oggetti json per poi passarli ,tramite opportuni metodi, al threadpool
//che si occuperà della gestione degli oggetti ricevuti

public class Produttore extends Thread {
    private Consumatori threadpool;
    private Occorrenze occ;

    // COSTRUTTORE
    public Produttore(Consumatori thp, Occorrenze occ) throws NullPointerException {
        this.threadpool = thp;
        this.occ = occ;
    }

    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();
        File newfile = new File("ListaMovimenti.json");
        Banca bancajson;
        ArrayList<ContoCorrente> object = new ArrayList<ContoCorrente>(); // arrayList in cui salvo la lista dei
                                                                          // conticorrenti ricavati dal file json
        try {

            // DESERIALIZZAZIONE DEL FILE JSON
            bancajson = objectMapper.readValue(newfile, Banca.class);

            ArrayList<ContoCorrente> listajson = bancajson.getListaContiCorrenti();// arrayList in cui salvo la lista,
                                                                                   // di tutti i conti correnti,
                                                                                   // ricavata dal file json

            // Aggiungo all'arraylist dei conti correnti ogni singolo conto corrente che ho
            // trovato nel file json
            for (ContoCorrente newc : listajson) {
                object.add(newc);
            }
            ArrayList<CoppiaMovimenti> listadeimovimenti = new ArrayList<>(); // arraylist cin cui salvo la lista di
                                                                              // tutti i movimenti fatti

            // aggiungo all'arraylist dei movimenti ogni singolo movimento che ho trovato
            // nel file json
            for (int i = 0; i < listajson.size(); i++) {
                listadeimovimenti.addAll(object.get(i).getListaMovimenti());
            }

            // creo tanti task quanti sono i movimenti individuati nella lista dei movimenti
            // in modo tale che ogni singolo task porti al threadpool la causale, di ogni
            // singolo movimento,per essere gestita
            for (int i = 0; i < listadeimovimenti.size(); i++) {
                Task task = new Task(listadeimovimenti.get(i).getCausale(), occ);
                threadpool.esecuzione(task);
            }

            // terminazione del threadpool
            threadpool.endpool();

            // RISULTATI
            System.out.println("Il numero di Bollettini è: " + occ.getBollettino());
            System.out.println("Il numero di F24 è: " + occ.getF24());
            System.out.println("Il numero di Accrediti è: " + occ.getAccredito());
            System.out.println("Il numero di PagoBancomat è: " + occ.getPagoBancomat());
            System.out.println("Il numero di Bonifici è: " + occ.getBonifico());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
