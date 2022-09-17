package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessCode {
    // save Doctor
    public boolean saveDoctor(Doctor doc) throws ClassNotFoundException, SQLException {
        PreparedStatement stm =
                DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Doctor VALUES (?,?,?,?)");
        stm.setString(1,doc.getDid());
        stm.setString(2,doc.getName());
        stm.setString(3,doc.getAddress());
        stm.setString(4,doc.getContact());
        return stm.executeUpdate()>0;
    }

    // load doctors
    public ArrayList<Doctor> searchDoctors(String text) throws ClassNotFoundException, SQLException {
        String searchText="%"+text+"%";
        PreparedStatement stm =
                DBConnection.getInstance().getConnection().prepareStatement
                        ("SELECT * FROM doctor WHERE address LiKE ? || name LIKE ? || contact liKE ?");
        stm.setString(1,searchText);
        stm.setString(2,searchText);
        stm.setString(3,searchText);
       ResultSet set = stm.executeQuery();
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
        PreparedStatement stm =
                DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM doctor WHERE dId=?");
        stm.setString(1,id);
        return stm.executeUpdate()>0;
    }

    // update Doctor
    public boolean updateDoctor(Doctor doc) throws ClassNotFoundException, SQLException {
        PreparedStatement stm =
                DBConnection.getInstance().getConnection().prepareStatement("UPDATE doctor SET name=?, address=?, contact=? WHERE dId=?");
        stm.setString(1,doc.getName());
        stm.setString(2,doc.getAddress());
        stm.setString(3,doc.getContact());
        stm.setString(4,doc.getDid());
        return stm.executeUpdate()>0;
    }

}
