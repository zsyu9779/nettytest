package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author: Zsyu
 * @Date: 20-3-1 上午12:51
 */
public class ByteBufTest0 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0;i<10;i++){
            buffer.writeByte(i);
        }
        for (int i = 0;i<10;i++){
            System.out.println(buffer.getByte(i));
        }
    }
}
