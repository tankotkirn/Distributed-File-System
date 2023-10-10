package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class client {
    public static void main(String[] args) throws IOException {
        // Define the server's IP address and port number
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 12345;

        // Create a DatagramSocket object to send and receive UDP packets
        DatagramSocket socket = new DatagramSocket();

        // Send a message to the server
        String message = "Hello, server!";
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
        socket.send(packet);

        // Receive a message from the server
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String receivedMessage = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received message from server: " + receivedMessage);

        // Close the socket
        socket.close();
    }
}
