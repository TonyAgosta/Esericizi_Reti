
//Tony Agosta 544090
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.sql.Timestamp;

public class Client {
    private String nomeserver;
    private int serverport;// porta del server

    public Client(String nomeserver, int numport) {
        this.nomeserver = nomeserver;
        this.serverport = numport;
    }

    public void start() {

        int porta = 1234;// porta del client
        int nummessaggiricevuti = 0;
        long min = 1000, max = 0, somma = 0; // variabili per le statistiche
        try {
            int timeout = 2000;
            String messaggio = null;

            DatagramSocket clientsocket = new DatagramSocket(porta);
            // invio dei 10 messaggi al server
            for (int i = 0; i < 10; i++) {
                messaggio = "PING " + i + " " + System.nanoTime();
                byte[] sendData = messaggio.getBytes();

                DatagramPacket packetToSend = new DatagramPacket(sendData, sendData.length,
                        InetAddress.getByName(nomeserver), serverport);
                Timestamp sendTime = new Timestamp(System.currentTimeMillis());// tempo al momento dell'invio del
                                                                               // messaggio
                clientsocket.send(packetToSend);

                clientsocket.setSoTimeout(timeout);// set del timeout di attesa
                byte[] receivedata = new byte[messaggio.length()];
                DatagramPacket receivedPacket = new DatagramPacket(receivedata, receivedata.length);
                try {// se il timeout non e` scaduto
                    clientsocket.receive(receivedPacket);
                    String messaggioricevuto = new String(receivedPacket.getData());
                    if (messaggioricevuto.equals(messaggio)) {

                        Timestamp receiveTime = new Timestamp(System.currentTimeMillis());// tempo di ricezione del
                                                                                          // messaggio di risposta dal
                                                                                          // Server
                        // aggiorno le variabili per le statistiche
                        if (receiveTime.getTime() - sendTime.getTime() < min)
                            min = receiveTime.getTime() - sendTime.getTime();
                        if (receiveTime.getTime() - sendTime.getTime() > max)
                            max = receiveTime.getTime() - sendTime.getTime();
                        somma += (receiveTime.getTime() - sendTime.getTime());
                        System.out.printf(messaggio + " RTT: %d ms\n", receiveTime.getTime() - sendTime.getTime());
                        nummessaggiricevuti++;
                    } else {// se il messaggio che il serve ha rispedito al client da quello che il client
                            // ha inviato al server lo considero perso
                        System.out.println(messaggio + " RTT: " + "*");
                    }
                } catch (SocketTimeoutException e) {// il timeout e` scaduto
                    System.out.println(messaggio + " RTT: " + "*");
                }

            }
            ///// STATISTICHE \\\\\
            int percentuale = 100 - nummessaggiricevuti * 10;
            System.out.println("                     -----PING STATISTICS-----");
            System.out.println("10 packets transmitted, " + nummessaggiricevuti + " packets received, " + percentuale
                    + "% packet loss");
            float media = (float) somma / nummessaggiricevuti;
            System.out.printf("round-trip (ms) min/avg/max %d/ %.2f /%d\n", min, media, somma);
        } catch (IOException e) {
            System.out.println("Porta già occupata");
        }

    }

}
