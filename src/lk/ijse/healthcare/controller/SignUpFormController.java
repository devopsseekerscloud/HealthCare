package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpFormController {
    public AnchorPane signUpFormContext;

    public void backToLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) signUpFormContext.getScene().getWindow();
        stage.setScene(
        new Scene(FXMLLoader.load(
        getClass().
        getResource("../view/LoginForm.fxml"))));
    }
}
