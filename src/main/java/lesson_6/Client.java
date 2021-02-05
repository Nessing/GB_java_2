package lesson_6;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        // запуск потока для получения сообщений от сервера
        new Thread(() -> {
            try {
                read(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        // запуск потока для отправки сообщений на сервер
        new Thread(() -> {
            try {
                write(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }

    private void read(Socket socket) throws IOException {
        DataInputStream in = new DataInputStream(socket.getInputStream());

        while (true) {
            System.out.println(in.readUTF());
        }
    }

    private void write(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while (true) {
            String message = reader.readLine();
            out.writeUTF(message);
            out.flush();
        }
    }
}
