package viewControllers;

import encryption.CryptionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import xmlHandling.Writer;

public class EntryCreationController extends ViewController {

    private User user;

    @FXML
    private TextField websiteInput, usernameInput, passwordInput;

    public void setUp(User user) {
        this.user = user;
    }

    @FXML
    public void handleButtonCancel(ActionEvent e) {
        closeWindow(e);
    }

    @FXML
    public void handleButtonSubmit(ActionEvent e) {

        if(websiteInput.getText().isEmpty() || usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields.", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Field(s) empty!");
            alert.showAndWait();
        }
        else {
            CryptionManager cm = new CryptionManager(user.getPassword());
            Writer writer = new Writer("/xml/passwords.xml");
            user.createEntry(websiteInput.getText(), cm.encrypt(passwordInput.getText()), usernameInput.getText());
            writer.saveUser(user);
        }
    }

    private void closeWindow(ActionEvent e) {
        Button btn = (Button) e.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
