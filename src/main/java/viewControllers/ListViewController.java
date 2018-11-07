package viewControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import models.Entry;
import models.User;

public class ListViewController extends ViewController {

    private User currentUser;

    @FXML
    private VBox contentBox;

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
        for(Entry e : currentUser.getEntries()) {
            Label username = new Label(e.getUsername());
            username.getStyleClass().add("listLabel");

            Label password = new Label(e.getPassword());
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
        loadScreen("/fxmlFiles/entryCreation.fxml", e, currentUser);
    }

    @FXML
    public void handleButtonHelp(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("How to use KeyKeeper:");
        alert.setContentText("With the Button 'Create New' you are able to add a Website with your Username and your Password.");
        alert.showAndWait();
    }
}