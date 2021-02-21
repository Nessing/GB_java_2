package lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Server is started and up");
            while (true) {
                Socket socket = server.accept();
                // запуск потока для чтения сообщений от клиента
                new Thread(() -> {
                    try {
                        read(socket);
                    } catch (IOException disconnected) {
                        System.out.println("Client was disconnected");
                    }
                }).start();
                // запуск потока для отправки сообщений клиенту с сервера
                new Thread(() -> {
                    try {
                        write(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    private void read(Socket socket) throws IOException {
        System.out.println("Client connection");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while (true) {
            String message = in.readUTF();
            if (message.equals("/exit")) {
                out.writeUTF("Good bye");
                break;
            }
            System.out.println("Client: " + message);
            out.writeUTF("You: " + message);
        }
    }

    private void write(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while (true) {
            String message = reader.readLine();
            out.writeUTF("Server: " + message);
        }
    }
}
