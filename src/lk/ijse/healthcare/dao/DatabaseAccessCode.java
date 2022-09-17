package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessCode {
    // save Doctor
    public boolean saveDoctor(Doctor doc) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/HealthCare","root","1234");
        PreparedStatement stm =
                connection.prepareStatement("INSERT INTO Doctor VALUES (?,?,?,?)");
        stm.setString(1,doc.getDid());
        stm.setString(2,doc.getName());
        stm.setString(3,doc.getAddress());
        stm.setString(4,doc.getContact());
        return stm.executeUpdate()>0;
    }

    // load doctors
    public ArrayList<Doctor> searchDoctors(String text) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/HealthCare","root","1234");
        PreparedStatement stm =
                connection.prepareStatement("SELECT * FROM doctor");
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
}
