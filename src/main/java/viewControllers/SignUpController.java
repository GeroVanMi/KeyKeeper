package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignUpController extends ViewController {
    @FXML
    private TextField username, mail;

    @FXML
    private PasswordField password, repeatedPassword;

    @FXML
    public void handleButtonSignUp(ActionEvent e) {
        if (!password.getText().equals(repeatedPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords don't match");
            alert.setContentText("Your Passwords aren't identical. Please try again.");
            alert.showAndWait();
        } else {
            loadScreen("/fxmlFiles/listView.fxml", e);
        }
    }

    @FXML
    public void handleButtonSignIn(ActionEvent e) {
        Button btn = (Button)e.getSource();
        Stage stage = (Stage)btn.getScene().getWindow();
        loadScreen("/fxmlFiles/loginView.fxml", e);
    }

}
