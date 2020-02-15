package NIOtest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: Zsyu
 * @Date: 20-2-15 下午2:53
 */
public class NioServer {

    private static Map<String,SocketChannel> cliMap = new HashMap();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {

                    final SocketChannel client;

                    try {

                        if (selectionKey.isAcceptable()){
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "["+ UUID.randomUUID().toString()+"]";
                            cliMap.put(key,client);
                        }
                        else if (selectionKey.isReadable()){
                            client = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);

                            if (count>0){
                                readBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client+": "+ receivedMessage);

                                String senderkey = null;

                                for (Map.Entry<String,SocketChannel>entry : cliMap.entrySet()){
                                    if (client == entry.getValue()){
                                        senderkey = entry.getKey();
                                    }
                                }

                                for (Map.Entry<String, SocketChannel> entry : cliMap.entrySet()){
                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((senderkey+": "+receivedMessage).getBytes());
                                    writeBuffer.flip();

                                    value.write(writeBuffer);

                                }
                            }
                            else if (count ==-1){
                                client.close();

                            }
                        }

                        selectionKeys.clear();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });




            }catch (Exception e){
                e.printStackTrace();
            }
        }




    }
}
