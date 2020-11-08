//Tony Agosta 544090

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        //////// CREO IL FILE JSON///////
        ObjectMapper objctMapper = new ObjectMapper();
        Banca banca = new Banca();
        banca.exec();

        try {
            File file = new File("ListaMovimenti.json");
            file.createNewFile();
            objctMapper.writeValue(file, banca);
            Consumatori threadpool = new Consumatori();
            Occorrenze occ = new Occorrenze();
            Produttore gestione = new Produttore(threadpool, occ);
            gestione.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}