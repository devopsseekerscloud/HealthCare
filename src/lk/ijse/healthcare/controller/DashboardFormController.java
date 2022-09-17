package lk.ijse.healthcare.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane dashboardContext;

    public void initialize() throws IOException {
        loadDefaultData();
    }

    private void loadDefaultData() throws IOException {
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(
                FXMLLoader.load(
                        getClass().
                        getResource("../view/DefaultForm.fxml")
                )
        );
    }
}
