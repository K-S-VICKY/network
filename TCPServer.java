import java.io.*;
import java.net.*;
import java.util.*;
public class TCPServer {
private static List<Socket> clients = new ArrayList<>();
public static void main(String[] args) {
try (ServerSocket serverSocket = new ServerSocket(4000)) {
System.out.println("TCP Server started on port 4000.");
while (true) {
Socket clientSocket = serverSocket.accept();
clients.add(clientSocket);
System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
new Thread(() -> handleClient(clientSocket)).start();
}
} catch (IOException e) {
e.printStackTrace();
}
}
private static void handleClient(Socket clientSocket) {
try (BufferedReader in = new BufferedReader(new
InputStreamReader(clientSocket.getInputStream()))) {
String message;
while ((message = in.readLine()) != null) {
System.out.println("Received: " + message);
broadcast(message, clientSocket);
}
} catch (IOException e) {
e.printStackTrace();
} finally {
clients.remove(clientSocket);
try {
clientSocket.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}
private static void broadcast(String message, Socket sender) {
for (Socket client : clients) {
if (client != sender) {
try {
PrintWriter out = new PrintWriter(client.getOutputStream(), true);
out.println("Message: " + message);
} catch (IOException e) {
e.printStackTrace();
}
}
}
}
}