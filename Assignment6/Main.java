//Tony Agosta 544090

//per compilare usare il comando: javac -cp ".:./lib/*" Main.java
//per eseguire usare il comando: java -cp ".:./lib/*" Main

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        Banca banca = new Banca();
        banca.addConto();

        //////// CREO IL FILE JSON///////
        ObjectMapper objctMapper = new ObjectMapper();
        String path = "./ListaMovimenti.json";

        // Scrittura del file json
        try {
            Files.deleteIfExists(Paths.get(path)); // elimino il file se esiste già
            Files.createFile(Paths.get(path));
            ByteBuffer buf = ByteBuffer.wrap((objctMapper.writeValueAsBytes(banca)));
            FileChannel outChannel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE);
            outChannel.write(buf);
            while (buf.hasRemaining()) {
                outChannel.write(buf);
            }
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Consumatori threadpool = new Consumatori(); // oggetto in cui è presente il threadpool che gestisce gli
                                                    // oggetti json ricevuti
        Occorrenze occ = new Occorrenze();
        Produttore gestione = new Produttore(threadpool, occ); // oggetto usato per leggere dal file json gli
                                                               // oggetti json da passare al threadpool
        gestione.start();

    }
}