package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;

public class EntryCreationController {

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

        if(websiteInput.getText() != null || usernameInput.getText() != null || passwordInput.getText() != null) {
            // TODO: Add entry

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields.", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Field(s) empty!");
            alert.showAndWait();
        }
    }

    private void closeWindow(ActionEvent e) {
        Button btn = (Button) e.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
