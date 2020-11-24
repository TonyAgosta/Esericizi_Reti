import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            String input = " ";
            Scanner scan = new Scanner(System.in);
            String messaggio = scan.next();

            scan.close();
            SocketAddress addres = new InetSocketAddress(6789);
            SocketChannel client = SocketChannel.open();
            client.connect(addres);
            ByteBuffer buffer = ByteBuffer.wrap(messaggio.getBytes());
            while (buffer.hasRemaining()) {
                client.write(buffer);
            }
            buffer.clear();
            buffer.flip();
            buffer = ByteBuffer.allocate(1024);
            buffer.flip();
            input = input + StandardCharsets.UTF_8.decode(buffer).toString();
            buffer.clear();
            buffer.flip();
            System.out.println(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
