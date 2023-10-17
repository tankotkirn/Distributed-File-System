import java.io.*;
import java.net.Socket;

public class naming_server {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;
        
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Input the file name to request
            System.out.print("Enter the file name to request: ");
            String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

            // Send the requested file name to the server
            writer.write(fileName + "\n");
            writer.flush();

            String response = reader.readLine();
            if (response != null && response.equals("OK")) {
                // Receive and save the file with the same name
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                System.out.println("File received and saved as " + fileName);
            } else {
                System.out.println("File not found on the server.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
