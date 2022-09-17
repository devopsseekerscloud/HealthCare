package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane dashboardContext;

    public void initialize() throws IOException {
        loadDefaultData();
    }

    private void loadDefaultData() throws IOException {
       setUi("DefaultForm");
    }

    public void openDoctorFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DoctorForm");
    }
    private void setUi(String value) throws IOException {
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(
                FXMLLoader.load(
                        getClass().
                                getResource("../view/"+value+".fxml")
                )
        );
    }
}
