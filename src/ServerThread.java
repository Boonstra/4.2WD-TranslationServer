import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Erik on 4/21/14.
 */
public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        super("Main");
        this.socket = socket;
    }

    @Override
    public void run() {
        HashMap<String, String> words = new HashMap<>();

        words.put("car", "auto");
        words.put("experience", "ervaring");
        words.put("tobacco", "tabak");

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;

            outputLine = "Hello, what word would you like to translate?";

            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                out.println(words.get(inputLine));
//                if (inputLine.equals("car")) {
//                    out.println("Auto");
//                } else {
//                    out.println("Not found");
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}