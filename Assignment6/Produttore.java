//Tony Agosta 544090

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Produttore extends Thread {
    private Consumatori threadpool;
    private Occorrenze occ;

    public Produttore(Consumatori thp, Occorrenze occ) throws NullPointerException {
        this.threadpool = thp;
        this.occ = occ;
    }

    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();
        File newfile = new File("ListaMovimenti.json");
        Banca conto;
        ArrayList<ContoCorrente> object = new ArrayList<ContoCorrente>();
        try {
            conto = objectMapper.readValue(newfile, Banca.class);

            ArrayList<ContoCorrente> newlist = conto.getListaContiCorrenti();
            int size = newlist.size();
            for (ContoCorrente newc : newlist) {
                object.add(newc);
            }
            ArrayList<CoppiaMovimenti> listadeimovimenti = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                listadeimovimenti.addAll(object.get(i).getListaMovimenti());
            }
            for (int i = 0; i < listadeimovimenti.size(); i++) {
                Task task = new Task(listadeimovimenti.get(i).getCausale(), occ);
                threadpool.esecuzione(task);
            }

            threadpool.endpool();
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
