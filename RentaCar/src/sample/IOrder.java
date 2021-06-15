package sample;

import java.util.ArrayList;

public interface IOrder {
    void Create(int id, String product);
    void Delete(int id, String name);
    ArrayList<Order> GetById(String id);
}
