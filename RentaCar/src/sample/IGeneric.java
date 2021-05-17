package sample;

import java.util.LinkedList;

public interface IGeneric<T> {

    LinkedList<T> GetAll();

    T getByID(String id);

    void Delete(String id);

    void Create(T entity);

    int Update(T entity);  //veritabanı sorgu uzunluğu için int dönsün.

}
