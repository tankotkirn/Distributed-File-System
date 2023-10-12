import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        final String SERVER_IP = "10.20.24.70";
        final int SERVER_PORT = 8080;
        
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Input the file name to request
            System.out.print("Enter the file name to request: ");
            String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

            // Send the requested file name to the server
            dataOutputStream.writeUTF(fileName);

            String response = dataInputStream.readUTF();
            if (response != null && response.equals("OK")) {
                // Receive and save the file with the same name
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = dataInputStream.read(buffer)) != -1) {
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
