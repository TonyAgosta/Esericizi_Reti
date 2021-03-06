//Tony Agosta 544090

//La classe produttore ha il compito di leggere dal file json gli oggetti json per poi passarli ,
//tramite opportuni metodi,al threadpool che si occuperà della gestione degli oggetti ricevuti

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Produttore extends Thread {
    private Consumatori threadpool;
    private Occorrenze occ;

    // COSTRUTTORE
    public Produttore(Consumatori thp, Occorrenze occ) throws NullPointerException {
        this.threadpool = thp;
        this.occ = occ;
    }

    public void run() {

        // DESERIALIZZAZIONE FILE JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String path = "./ListaMovimenti.json";

        // Lettura dal file json
        try {
            FileChannel fis = FileChannel.open(Paths.get(path), StandardOpenOption.READ);
            ByteBuffer buff = ByteBuffer.allocate(1024);
            String s = new String();
            while (fis.read(buff) > 0) {
                buff.flip();
                while (buff.hasRemaining()) {
                    s = s + StandardCharsets.UTF_8.decode(buff).toString();

                }
                buff.clear();
            }
            fis.close();
            Banca bancajson = objectMapper.readValue(s, Banca.class);

            ArrayList<ContoCorrente> listajson = bancajson.getListaContiCorrenti();// arrayList in cui salvo la lista di
                                                                                   // tutti i conti correnti ricavata
                                                                                   // dal
                                                                                   // file json

            ArrayList<CoppiaMovimenti> listadeimovimenti = new ArrayList<>(); // arraylist in cui salvo la lista di
                                                                              // tutti i movimenti presenti nei conti
                                                                              // correnti

            // aggiungo all'arraylist dei movimenti ogni singolo movimento che ho trovato
            // nel file json
            for (int i = 0; i < listajson.size(); i++) {
                listadeimovimenti.addAll(listajson.get(i).getListaMovimenti());
            }

            // creo tanti task quanti sono i movimenti individuati nella lista dei movimenti
            // in modo tale che ogni singolo task porti al threadpool la causale, di ogni //
            // singolo movimento,per essere gestita

            for (int j = 0; j < listadeimovimenti.size(); j++) {
                Task task = new Task(listadeimovimenti.get(j).getCausale(), occ);
                threadpool.esecuzione(task);
            }

            // terminazione del threadpool
            threadpool.endpool();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // RISULTATI
        System.out.println("Il numero di Bollettini è: " + occ.getBollettino());
        System.out.println("Il numero di F24 è: " + occ.getF24());
        System.out.println("Il numero di Accrediti è: " + occ.getAccredito());
        System.out.println("Il numero di PagoBancomat è: " + occ.getPagoBancomat());
        System.out.println("Il numero di Bonifici è: " + occ.getBonifico());
    }
}