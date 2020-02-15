package NIOtest;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author: Zsyu
 * @Date: 20-2-14 下午1:40
 */
public class NIOTest11 {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 9;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){
            int byteread = 0;

            while (byteread<messageLength){
                long r = socketChannel.read(byteBuffers );
                byteread+=r;
                System.out.println("byteRead"+byteread);

                Arrays.asList(byteBuffers).stream().
                        map(byteBuffer -> "position"+byteBuffer.position()+",limit"+byteBuffer.limit()).
                        forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });
            long bytesWritten = 0;
            while (bytesWritten<messageLength){
                long r = socketChannel.write(byteBuffers);
                bytesWritten +=r;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });
            System.out.println(byteread+"  "+bytesWritten+"  "+messageLength);
        }

    }
}
