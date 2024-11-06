import java.io.IOException;
import java.net.*;
import java.util.Scanner;
public class UDPClient {
public static void main(String[] args) {
try (DatagramSocket udpSocket = new DatagramSocket()) {
InetAddress serverAddress = InetAddress.getByName("localhost");
Scanner scanner = new Scanner(System.in);
while (true) {
System.out.print("Enter status update (e.g., 'is typing...'): ");
String status = scanner.nextLine();
byte[] buffer = status.getBytes();
DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress,
54321);
udpSocket.send(packet);
System.out.println("UDP status sent.");
}
} catch (IOException e) {
e.printStackTrace();
}
}
}