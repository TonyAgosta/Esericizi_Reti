//Tony Agosta 544090

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int port = 6789;
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = socket.accept();
                InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader buffer = new BufferedReader(input);
                OutputStream output = clientSocket.getOutputStream();
                String richiesta = buffer.readLine(); // salvo la richiesta ricevuta
                if (richiesta != null) {
                    String[] s = richiesta.split(" ");
                    if (s[0].equals("GET")) {
                        String nomefile = s[1]; // prendo il nome del file
                        String pathfile = System.getProperty("user.dir") + nomefile; // salvo il path del file
                        Path path = Paths.get(pathfile);
                        if (Files.exists(path)) {// controllo se il file esiste
                            long sizefile = Files.size(path); // salvo la lunghezza del file
                            String tipofile = Files.probeContentType(path); // salvo il tipo del file
                            String risposta = "HTTP/1.1 200 OK\n Lunghezza file: " + sizefile + "\n" + "Tipo file: "
                                    + tipofile + "\n\n"; // messaggio di risposta
                            output.write(risposta.getBytes()); // mando la risposta al client
                            File file = new File(pathfile);
                            Files.copy(file.toPath(), output);// scrivo sill'output il contenuto del file

                        } else {// il file non esiste
                            String risposta = "HTTP/1.1 404 Not Found\n\n";
                            output.write(risposta.getBytes());// mando la risposta al client
                        }
                        // chiusura della connessione
                        input.close();
                        output.close();
                    } else {
                        System.out.println("La richiesta ricevuta  non e` una GET");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
