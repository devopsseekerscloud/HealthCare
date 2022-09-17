package lk.ijse.healthcare.dao;

import java.util.ArrayList;

public interface CrudDAO<T,ID> {
    public boolean save(T t);
    public boolean update(T t);
    public boolean delete(ID id);
    public T get(ID d);
    public ArrayList<T> getAllDoctors();
}
