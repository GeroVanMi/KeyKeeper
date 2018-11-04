package viewControllers;

import encryption.CryptionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import xmlHandling.Reader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController extends ViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleButtonLogin(ActionEvent e) {
        String password = passwordField.getText();
        CryptionManager cm = new CryptionManager(password);

        Reader reader = new Reader("src/main/resources/xml/passwords.xml");
        ArrayList<User> userList = reader.readAllUser();
        for(User user : userList) {
            if(usernameField.getText().equals(user.getName())) {
                if(cm.encrypt(password).equals(user.getPassword())) {
                    Button btn = (Button) e.getSource();
                    Stage stage = (Stage) btn.getScene().getWindow();
                    loadScreen("/fxmlFiles/listView.fxml", e, user);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Password incorrect");
                    alert.setContentText("Wrong Password.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    public void handleButtonSignUp(ActionEvent e) {
        // TODO: Switch View to signUpView.fxml
        Button btn = (Button) e.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        loadScreen("/fxmlFiles/signUpView.fxml", e);
    }

    public void loadScreen(String screen, ActionEvent e, User user) {
        Parent root;
        try {
            Button btn = (Button) e.getSource();
            Stage stage = (Stage) btn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            root = loader.load();
            ListViewController listViewController = loader.getController();
            listViewController.setUp(btn.getScene(), user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}