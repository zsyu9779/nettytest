package NIOtest;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Author: Zsyu
 * @Date: 19-11-4 下午11:46
 */
public class NIOTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0;i<5;i++){
            int random = new SecureRandom().nextInt(50);
            buffer.put(random);
        }
        System.out.println("limit:"+buffer.limit());

        buffer.flip();

        System.out.println("after:"+buffer.limit());
        System.out.println("+++++++++++++++++++++++++++++++");
        while (buffer.hasRemaining()){
            System.out.println("position:"+buffer.position());
            System.out.println("limit:"+buffer.limit());

            System.out.println(buffer.get());
        }

        buffer.flip();

        System.out.println("+++++++++++");
        for (int i = 0;i<6;i++){
            int random = new SecureRandom().nextInt(50);
            buffer.put(random);
        }
        System.out.println(buffer.limit());

    }
}
