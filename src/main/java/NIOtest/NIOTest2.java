package NIOtest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Zsyu
 * @Date: 20-2-13 下午10:49
 */
public class NIOTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("aaa.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("bbb.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer= ByteBuffer.allocate(10);

        while (true){
            //byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);

            System.out.println(read);

            if (read==-1){
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);

        }
        inputChannel.close();
        outputChannel.close();
    }
}
