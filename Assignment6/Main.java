//Tony Agosta 544090

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        //////// CREO IL FILE JSON///////
        ObjectMapper objctMapper = new ObjectMapper();
        Banca banca = new Banca();
        banca.addConto();

        String path = "./ListaMovimenti.json";

        try {
            Files.deleteIfExists(Paths.get(path));
            Files.createFile(Paths.get(path));
            ByteBuffer buf = ByteBuffer.wrap((objctMapper.writeValueAsBytes(banca)));
            FileChannel outChannel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE);
            outChannel.write(buf);
            while (buf.hasRemaining()) {
                outChannel.write(buf);
            }
            outChannel.close();
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