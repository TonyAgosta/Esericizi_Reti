//Tony Agosta 544090

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {
        int p = 1;
        int k = (int) (Math.random() * 10) + 1;// numero dei conti correnti e dei correntisti
        System.out.println(k);
        //////// CREO IL FILE JSON///////
        ObjectMapper objctMapper = new ObjectMapper();
        // /ArrayList<String> nomiconti = new ArrayList<>(5);
        Banca banca = new Banca(k);
        for (int i = 0; i < k; i++) {
            banca.setContoCorrente("ContoCorrente-" + i, i);
        }

        try {
            File file = new File("ListaMovimenti.json");
            file.createNewFile();
            objctMapper.writeValue(file, banca);
            System.out.println(objctMapper.writeValueAsString(banca));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////

        /*
         * File inputFile = new File("listamovimenti.json"); if (!inputFile.exists()) {
         * System.out.println("The input file doesn't exist"); return; } try {
         * FileInputStream fis = new FileInputStream(inputFile); FileChannel fileChannel
         * = fis.getChannel(); ByteBuffer buffer = ByteBuffer.allocate(1024); String s =
         * new String(); while (fileChannel.read(buffer) > 0) { buffer.flip(); while
         * (buffer.hasRemaining()) { s = s +
         * StandardCharsets.UTF_8.decode(buffer).toString(); } buffer.clear(); }
         * System.out.println(s);
         */
    }
}