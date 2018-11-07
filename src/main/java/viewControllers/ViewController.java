package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;

import java.io.IOException;

public abstract class ViewController {

    public void loadScreen(String screen, ActionEvent e) {
        Parent root;
        try {
            Button btn = (Button) e.getSource();
            Stage stage = (Stage) btn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
