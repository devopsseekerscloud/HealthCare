package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.healthcare.dao.DatabaseAccessCode;
import lk.ijse.healthcare.entity.Doctor;
import lk.ijse.healthcare.view.tm.DoctorTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    public JFXButton btnSaveDoctor;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
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


                btn.setOnAction(event -> {
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "are you sure?",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            if (new DatabaseAccessCode().deleteDoctor(d.getDid())){
                                searchData();
                                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                            }else{
                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                            }
                        } catch (ClassNotFoundException | SQLException e) {

                            throw new RuntimeException(e);
                        }
                    }
                });

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

        if (btnSaveDoctor.getText().equals("Save Doctor")){
            try{

                boolean isSaved = new DatabaseAccessCode().saveDoctor(d1);
                if (isSaved){
                    searchData();
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }

            }catch (ClassNotFoundException | SQLException e){
                new Alert(Alert.AlertType.ERROR, "Try Again!").show();
                e.printStackTrace();
            }
        }else{
            try{

                boolean isUpdated = new DatabaseAccessCode().updateDoctor(d1);
                if (isUpdated){
                    searchData();
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }

            }catch (ClassNotFoundException | SQLException e){
                new Alert(Alert.AlertType.ERROR, "Try Again!").show();
                e.printStackTrace();
            }
        }



    }
}
