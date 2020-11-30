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
        int sendtime;
        long min = 1000, max = 0, somma = 0;
        try {
            int timeout = 2000;
            String messaggio = null;

            DatagramSocket clientsocket = new DatagramSocket(porta);
            for (int i = 0; i < 10; i++) {
                messaggio = "PING " + i + " " + System.nanoTime();
                byte[] sendData = messaggio.getBytes();

                DatagramPacket packetToSend = new DatagramPacket(sendData, sendData.length,
                        InetAddress.getByName(nomeserver), serverport);
                Timestamp sendTime = new Timestamp(System.currentTimeMillis());
                clientsocket.send(packetToSend);

                clientsocket.setSoTimeout(timeout);
                byte[] receivedata = new byte[messaggio.length()];
                DatagramPacket receivedPacket = new DatagramPacket(receivedata, receivedata.length);
                try {
                    clientsocket.receive(receivedPacket);
                    Timestamp receiveTime = new Timestamp(System.currentTimeMillis());
                    if (receiveTime.getTime() - sendTime.getTime() < min)
                        min = receiveTime.getTime() - sendTime.getTime();
                    if (receiveTime.getTime() - sendTime.getTime() > max)
                        max = receiveTime.getTime() - sendTime.getTime();
                    somma += (receiveTime.getTime() - sendTime.getTime());
                    String messaggioricevuto = new String(receivedPacket.getData());
                    // int t = messaggioricevuto + (receivedtime / 1000);
                    System.out.printf(messaggio + " RTT: %d ms\n", receiveTime.getTime() - sendTime.getTime());
                    nummessaggiricevuti++;
                } catch (SocketTimeoutException e) {
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
            e.printStackTrace();
        }

    }

}
