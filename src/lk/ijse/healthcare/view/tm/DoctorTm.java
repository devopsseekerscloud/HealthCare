package lk.ijse.healthcare.view.tm;

import javafx.scene.control.Button;

public class DoctorTm {
    private String did;
    private String name;
    private String address;
    private String contact;
private Button btn;

    public DoctorTm() {
    }

    public DoctorTm(String did, String name, String address, String contact, Button btn) {
        this.did = did;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.btn = btn;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
