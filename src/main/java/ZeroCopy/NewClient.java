package ZeroCopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: Zsyu
 * @Date: 20-2-17 上午1:24
 */
public class NewClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);

        String fileName = "/home/zsy/Downloads/CLion-2019.1.tar.gz";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long starttime = System.currentTimeMillis();
        long transfercount = fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("bytes: "+transfercount+"time: "+(System.currentTimeMillis()-starttime));

    }
}
