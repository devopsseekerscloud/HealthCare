package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import lk.ijse.healthcare.entity.Doctor;

public class DoctorFormController {
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtId;

    public void saveDoctorOnAction(ActionEvent actionEvent) {
        Doctor d1= new Doctor(
                txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()
        );

    }
}
