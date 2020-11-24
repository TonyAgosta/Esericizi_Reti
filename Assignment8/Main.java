import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

//LA CLASSE MAIN SI COMPORTA DA SERVER

public class Main {
    public static void main(String[] args) {
        ServerSocketChannel ssc;
        Selector selector;
        try {
            ssc = ServerSocketChannel.open();
            ServerSocket socket = ssc.socket();
            socket.bind(new InetSocketAddress(6789));
            ssc.configureBlocking(false);
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {

                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Connessione accettata dal client ");
                        client.configureBlocking(false);
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_READ);

                    } else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        String output = (String) key.attachment();
                        if (output != null) {
                            ByteBuffer bufstring = ByteBuffer.wrap(output.getBytes());
                            int numbyte = client.write(bufstring);
                            if (numbyte == -1) {
                                key.channel().close();
                            } else if (bufstring.hasRemaining()) {
                                bufstring.flip();
                                String messaggio = StandardCharsets.UTF_8.decode(bufstring).toString();
                                key.attach(messaggio);
                                System.out.println("dopo while");
                            } else {
                                key.attach(null);
                                key.interestOps(SelectionKey.OP_READ);
                            }

                        }

                    } else if (key.isReadable()) {
                        System.out.println("sono nella readble");
                        SocketChannel client = (SocketChannel) key.channel();

                        String messaggio = (String) key.attachment();
                        messaggio = "echoed by server: ";
                        ByteBuffer messclient = ByteBuffer.allocate(1024);
                        messclient.clear();
                        int numbyte = client.read(messclient);
                        if (numbyte == 1024) {
                            messclient.flip();
                            messaggio = messaggio + StandardCharsets.UTF_8.decode(messclient).toString();
                            key.attach(messaggio);
                        } else if (numbyte == -1) {
                            key.cancel();
                            key.channel().close();
                        } else if (numbyte < 1024) {
                            messclient.flip();
                            messaggio = messaggio + StandardCharsets.UTF_8.decode(messclient).toString();
                            key.attach(messaggio);
                            key.interestOps(SelectionKey.OP_WRITE);

                        }
                        System.out.println(messaggio);

                    }

                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }

            }
        }

    }
}