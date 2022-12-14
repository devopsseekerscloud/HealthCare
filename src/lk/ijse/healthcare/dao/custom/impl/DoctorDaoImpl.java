package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.CrudUtil;
import lk.ijse.healthcare.dao.custom.DoctorDao;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public boolean save(Doctor doctor) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Doctor VALUES (?,?,?,?)",
                doctor.getDid(),doctor.getName(),
                doctor.getAddress(),doctor.getContact());
    }

    @Override
    public boolean update(Doctor doctor) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE doctor SET name=?, address=?, contact=? WHERE dId=?",
                doctor.getName(),doctor.getAddress(),
                doctor.getContact(),doctor.getDid());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM doctor WHERE dId=?", s);
    }

    @Override
    public Doctor get(String d) {
        return null;
    }

    @Override
    public ArrayList<Doctor> getAllDoctors() {
        return null;
    }

    @Override
    public ArrayList<Doctor> searchDoctors(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";
        ResultSet set = CrudUtil.execute("SELECT * FROM doctor WHERE address LiKE ? || name LIKE ? || contact liKE ?", searchText, searchText, searchText);
        ArrayList<Doctor> lst = new ArrayList<>();
        while (set.next()) {
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
