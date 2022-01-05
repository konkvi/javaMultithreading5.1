import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int port = 23444;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end"))
                        break;
                    out.printf("N-ное число Фибоначчи [%s] = %s%n", line, fibonacci(Long.parseLong(line)));
                }

                serverSocket.close();

            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }

    public static long fibonacci(long n) {
        int x = 0;
        int y = 1;
        for (int i = 0; i < n; i++) {
            y = x + y;
            x = y - x;
        }
        return y;
    }
}
