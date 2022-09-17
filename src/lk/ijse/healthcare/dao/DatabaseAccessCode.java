package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessCode {
    // save Doctor
    public boolean saveDoctor(Doctor doc) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Doctor VALUES (?,?,?,?)",
                doc.getDid(),doc.getName(),doc.getAddress(),doc.getContact());
    }
    // load doctors
    public ArrayList<Doctor> searchDoctors(String text) throws ClassNotFoundException, SQLException {
        String searchText="%"+text+"%";
       ResultSet set = CrudUtil.execute("SELECT * FROM doctor WHERE address LiKE ? || name LIKE ? || contact liKE ?",searchText,searchText,searchText);
       ArrayList<Doctor> lst = new ArrayList<>();
       while (set.next()){
           lst.add(new Doctor(
                   set.getString(1),
                   set.getString(2),
                   set.getString(3),
                   set.getString(4))
           );
       }
       return lst;
    }
    // delete Doctor
    public boolean deleteDoctor(String id) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM doctor WHERE dId=?", id);
    }
    // update Doctor
    public boolean updateDoctor(Doctor doc) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("UPDATE doctor SET name=?, address=?, contact=? WHERE dId=?",
                doc.getName(),doc.getAddress(),doc.getContact(),doc.getDid());
    }

}
