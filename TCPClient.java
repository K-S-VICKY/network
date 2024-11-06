import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TCPClient {
public static void main(String[] args) {
try (Socket socket = new Socket("localhost", 4000);
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
Scanner scanner = new Scanner(System.in)) {
System.out.println("Connected to TCP server.");
new Thread(() -> {
try {
String serverMessage;
while ((serverMessage = in.readLine()) != null) {
System.out.println("Server: " + serverMessage);
}
} catch (IOException e) {
e.printStackTrace();
}
}).start();
// Main loop for sending messages
while (true) {
System.out.print("You: ");
String message = scanner.nextLine();
out.println(message);
}
} catch (IOException e) {
e.printStackTrace();
}
}
}