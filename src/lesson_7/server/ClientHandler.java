package lesson_7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        if (nickName.equals("/exit")) {
            try {
                out.writeUTF("/exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("Client started");
            setNick();

            while (true) {
                String message = in.readUTF();
                String[] msgArray = message.split("");
                if (message.equals("/exit")) out.writeUTF(message);
                // отправка приватного сообщения
                else if (msgArray.length >= 2) {
                    String[] arr = message.split(" ");
                    if (arr[0].equals("/p")) {
                        sendPrivateMessage(message);
                    }
                    // перенос строки
                    List<String> words = Arrays.asList(message.split(""));
                    if (words.size() >= 70) {
                        for (int i = 0; i < words.size(); i++) {
                            if(i % 70 == 0) {
                                words.set(i, words.get(i) + "\n");
                            }
                        }
                    }
                    String mes = words.stream().collect(Collectors.joining(""));
                    server.sendMessageToAll(nickName + ": " + mes);
                } else {
                    server.sendMessageToAll(nickName + ": " + message);
                }
            }

        } catch (Exception e) {
            System.err.println("Handler disconnected");
            try {
                server.sendMessageToAll("=== " + this.getNickName() + " покинул чат ===");
            } catch (IOException ex) {
                System.err.println("client lose server");
            }
            server.removeClient(this);
        }
    }

    private void setNick() throws IOException {
        sendMessage("=== Введите свой ник ===");
        setNickName(in.readUTF());
        sendMessage("[Вы вошли в чат как {" + this.getNickName() + "}]");
        server.sendMessageToAll("=== " + this.getNickName() + " вошел в чат ===");
        System.out.println("client identified nick: " + getNickName());
    }

    private void sendPrivateMessage(String message) throws IOException {
        List<String> array = Arrays.asList(message.split(" "));
        try {
            String nickTo = array.get(1);
            array.set(0, "[private]");
            array.set(1, "{ " + this.getNickName() + " to " + nickTo + " }:");
            String sendMessage = array.stream().collect(Collectors.joining(" "));
            server.sendMessagePrivate(getNickName(), nickTo, sendMessage);
        } catch (Exception e) {
            server.sendMessagePrivate(getNickName(), this.getNickName(), "=== Ник получателя не был введен! ===");
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}
