package ZeroCopy;

import java.io.*;
import java.net.Socket;

/**
 * @Author: Zsyu
 * @Date: 20-2-16 下午11:26
 */
public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8899);

        String fileName = "/home/zsy/Downloads/CLion-2019.1.tar.gz";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];

        long readcount;
        long total = 0;

        long starttime = System.currentTimeMillis();
        while ((readcount = inputStream.read(bytes))>=0){
            total += readcount;
            dataOutputStream.write(bytes);
        }

        System.out.println("发送总字节数： "+total+", 耗时"+(System.currentTimeMillis()-starttime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();

    }
}
