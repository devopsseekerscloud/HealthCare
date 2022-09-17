package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.healthcare.dao.DatabaseAccessCode;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.SQLException;

public class DoctorFormController {
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtId;
    public TableView tblDoctors;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn coOption;


    public void saveDoctorOnAction(ActionEvent actionEvent) {
        Doctor d1= new Doctor(
                txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()
        );

        try{

            boolean isSaved = new DatabaseAccessCode().saveDoctor(d1);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
            e.printStackTrace();
        }

    }
}
