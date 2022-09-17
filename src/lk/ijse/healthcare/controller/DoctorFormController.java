package lk.ijse.healthcare.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.healthcare.dao.DatabaseAccessCode;
import lk.ijse.healthcare.entity.Doctor;
import lk.ijse.healthcare.view.tm.DoctorTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorFormController {
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtId;
    public TableView<DoctorTm> tblDoctors;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;

    public void initialize(){
        searchData();
    }

    private void searchData() {
        try{
            ArrayList<Doctor> lst = new DatabaseAccessCode().searchDoctors("");
            ObservableList<DoctorTm> tmList = FXCollections.observableArrayList();

            for (Doctor d:lst
                 ) {
                Button btn= new Button("Delete");
                tmList.add(new DoctorTm(d.getDid(),d.getName(),d.getAddress(),d.getContact(),btn));
            }
            tblDoctors.setItems(tmList);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


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
