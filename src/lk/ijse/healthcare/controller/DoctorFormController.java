package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import lk.ijse.healthcare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.healthcare.entity.Doctor;
import lk.ijse.healthcare.view.tm.DoctorTm;

import javax.xml.crypto.Data;
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
    public TextField txtSearch;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        searchData(sText);


        tblDoctors.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            sText=newValue;
            searchData(sText);
        });

    }
    private void setData(DoctorTm tm){
        btnSaveDoctor.setText("Update Doctor");
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
    }
private String sText="";
    private void searchData(String text) {

        try{
            ArrayList<Doctor> lst = new DoctorDaoImpl().searchDoctors(text);
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
                            if (new DoctorDaoImpl().delete(d.getDid())){
                                searchData(sText);
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

                boolean isSaved = new DoctorDaoImpl().save(d1);
                if (isSaved){
                    searchData(sText);
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

                boolean isUpdated = new DoctorDaoImpl().update(d1);
                if (isUpdated){
                    searchData(sText);
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

    public void newDoctorOnAction(ActionEvent actionEvent) {
        btnSaveDoctor.setText("Save Doctor");
    }
}
