import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Erik on 4/21/14.
 */
public class ServerThread extends Thread {

    private Socket socket = null;
    HashMap<String, String> sentence = new HashMap<>();

    public ServerThread(Socket socket) {
        super("Main");
        this.socket = socket;
    }

    @Override
    public void run() {
        sentence.put("car", "auto");
        sentence.put("experience", "ervaring");
        sentence.put("tobacco", "tabak");
        sentence.put("bicycle", "fiets");
        sentence.put("airplane", "vliegtuig");
        sentence.put("boat", "boot");
        sentence.put("walk", "loop");
        sentence.put("train", "trein");
        sentence.put("horse", "paard");
        sentence.put("dog", "hond");

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;

            outputLine = "Hello, what word would you like to translate?";

            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
//                out.println(sentence.get(inputLine));

                out.println(translateSentence(inputLine));

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

    private String translateSentence (String inputLine) {
        String[] words = inputLine.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (sentence.get(words[i]) != null) {
                words[i] = sentence.get(words[i]);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String s : words) {
            builder.append(s);
            builder.append(" ");
        }
        return builder.toString();
    }
}