package ZeroCopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Zsyu
 * @Date: 20-2-16 下午10:41
 */
public class OldSever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes=new byte[4096];

                while (true){
                    int readcount = dataInputStream.read(bytes,0,bytes.length);

                    if (readcount==-1){
                        break;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }









    }
}
