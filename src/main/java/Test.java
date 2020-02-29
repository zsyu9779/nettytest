import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @Author: Zsyu
 * @Date: 20-2-17 下午3:13
 */
public class Test {
    public static void main(String[] args) {
      int result = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println("result = " + result);
    }
}
