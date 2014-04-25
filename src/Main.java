import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Erik on 4/21/14.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int portNumber = 8008;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
