import java.io.*;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;
        final String FILE_TO_REQUEST = "file_to_request.txt";
        final String SAVE_PATH = "received_file.txt";
        
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Request the file from the server
            writer.write(FILE_TO_REQUEST + "\n");
            writer.flush();

            String response = reader.readLine();
            if (response != null && response.equals("OK")) {
                FileOutputStream fileOutputStream = new FileOutputStream(SAVE_PATH);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                System.out.println("File received and saved as " + SAVE_PATH);
            } else {
                System.out.println("File not found on the server.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
