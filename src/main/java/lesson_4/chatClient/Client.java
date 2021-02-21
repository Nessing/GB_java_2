package chatClient;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Client {
    public TextArea output;
    public TextField message;

    public void enter() {
        output.appendText(message.getText() + "\n");
        clear();
    }

    public void clear() {
        message.setText("");
    }

    public void keyListen(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString() == "ENTER") {
            output.appendText(message.getText() + "\n");
            clear();
        }
    }
}
