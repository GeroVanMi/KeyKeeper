package viewControllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.User;

import java.awt.event.ActionEvent;

public class ListViewController extends ViewController {

    private User currentUser;

    @FXML
    private ScrollPane scrollPane;

    public ListViewController() {
    }

    public void setUp(Scene scene, User user) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                // TODO: Add Code to close all Titled Panes
                System.out.println("Hello World");
            }
        });
        currentUser = user;
    }

    @FXML
    public void handleButtonNew(ActionEvent e) {
        // TODO: Open box to create new entry
    }

    @FXML
    public void handleButtonHelp(ActionEvent e) {
        // TODO: Open box with help commands
    }
}
