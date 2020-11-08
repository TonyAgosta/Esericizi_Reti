//Tony Agosta 544090

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        //////// CREO IL FILE JSON///////
        ObjectMapper objctMapper = new ObjectMapper();
        Banca banca = new Banca();
        banca.addConto();

        try {
            File file = new File("ListaMovimenti.json");
            file.createNewFile();
            objctMapper.writeValue(file, banca);
            Consumatori threadpool = new Consumatori(); // oggetto in cui è presente il threadpool che gestisce gli
                                                        // oggetti json ricevuti
            Occorrenze occ = new Occorrenze();
            Produttore gestione = new Produttore(threadpool, occ); // oggetto usato per leggere dal file json gli
                                                                   // oggetti json da passare al threadpool
            gestione.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}