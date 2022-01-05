import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String host = "127.0.0.1";
    public static int port = 23444;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(host, port);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(clientSocket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String message;
            while (true) {
                System.out.print("Введите целое число: ");
                message = scanner.nextLine();
                out.println(message);
                if ("end".equals(message))
                    break;
                System.out.println("Ответ сервера: " + in.readLine());
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
