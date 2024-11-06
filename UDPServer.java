import java.io.IOException;
import java.net.*;
public class UDPServer {
public static void main(String[] args) {
try (DatagramSocket udpSocket = new DatagramSocket(54321)) {
byte[] buffer = new byte[1024];
System.out.println("UDP Server started on port 54321.");
while (true) {
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
udpSocket.receive(packet);
String message = new String(packet.getData(), 0, packet.getLength());
System.out.println("UDP message: " + message);
}
} catch (IOException e) {
e.printStackTrace();
}
}
}