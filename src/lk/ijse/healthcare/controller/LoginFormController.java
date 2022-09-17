package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginPaneContext;

    public void openSignupOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }

    public void openDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) loginPaneContext.getScene().getWindow();
        stage.setScene(
        new Scene(FXMLLoader.load(
        getClass().
        getResource("../view/"+location+".fxml"))));
    }
}
