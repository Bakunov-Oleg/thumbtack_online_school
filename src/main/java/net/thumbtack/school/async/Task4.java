package net.thumbtack.school.async;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Task4 {
    //4. Доработать сервер на основе селекторов, чтобы он принимал запрос http
    //содержащий json вида "{x:10, y:20}" и возвращал ответ с суммой двух чисел.

    private static final String HTTP_RESPONSE = "HTTP/1.1 200 OK\n" +
            "Content-Type: text/html\n" +
            "Connection: Closed\n\n";

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8080));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT, null);

        while (true) {
            selector.select();
            Set<SelectionKey> selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey selectKey = iter.next();

                if (selectKey.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);

                } else if (selectKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectKey.channel();
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    int n = client.read(buff);
                    if (n == -1) {
                        client.close();
                    }
                    String result = new String(buff.array()).trim();
                    result = result.split(" HTTP")[0];

                    String xVal = result.substring(result.indexOf("x:") + 2, result.indexOf(","));
                    String yVal = result.substring(result.indexOf("y:") + 2, result.indexOf("}"));

                    selectKey.attach(Integer.parseInt(xVal) + Integer.parseInt(yVal));
                    selectKey.interestOps(SelectionKey.OP_WRITE);

                } else if (selectKey.isWritable()) {
                    SocketChannel client = (SocketChannel) selectKey.channel();
                    Integer result = (Integer) selectKey.attachment();
                    ByteBuffer wBuff = StandardCharsets.UTF_8.encode(HTTP_RESPONSE);

                    client.write(wBuff);
                    client.write(StandardCharsets.UTF_8.encode("Result = " + result.toString()));
                    if (!wBuff.hasRemaining()) {
                        wBuff.compact();
                        selectKey.interestOps(SelectionKey.OP_READ);
                    }
                    client.close();
                }
                iter.remove();
            }
        }
    }
}
