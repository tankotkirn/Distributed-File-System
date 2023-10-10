import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Waiting for connection...");

        try {
            clientSocket = serverSocket.accept();
            System.out.println("Connection established with client: " + clientSocket.getInetAddress().getHostName());
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("Hello, client!");

        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
