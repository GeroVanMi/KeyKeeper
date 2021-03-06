package viewControllers;


import encryption.CryptionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Entry;
import models.User;

import java.io.IOException;

public class ListViewController extends ViewController {

    private User currentUser;

    @FXML
    private VBox contentBox;

    public void setUp(Scene scene, User user) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                // TODO: Add Code to close all Titled Panes
                System.out.println("Hello World");
            }
        });
        currentUser = user;
        CryptionManager cm = new CryptionManager(currentUser.getPassword());
        for(Entry e : currentUser.getEntries()) {
            Label username = new Label(e.getUsername());
            username.getStyleClass().add("listLabel");

            Label password = new Label(cm.decrypt(e.getPassword()));
            password.getStyleClass().add("listLabel");

            VBox box = new VBox(username, password);
            box.getStyleClass().add("listBox");

            TitledPane titledPane = new TitledPane(e.getWebsite(), box);
            titledPane.getStyleClass().add("listItem");
            titledPane.setAnimated(true);
            titledPane.setExpanded(false);
            contentBox.getChildren().add(titledPane);
        }
    }

    @FXML
    public void handleButtonNew(ActionEvent e) {
        loadScreen("/fxmlFiles/entryCreation.fxml", currentUser);
    }

    @FXML
    public void handleButtonHelp(ActionEvent e) {
        // TODO: Open box with help commands
    }

    public void loadScreen(String screen, User user) {
        Parent root;
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            root = loader.load();
            EntryCreationController entryCreationController = loader.getController();
            entryCreationController.setUp(user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}