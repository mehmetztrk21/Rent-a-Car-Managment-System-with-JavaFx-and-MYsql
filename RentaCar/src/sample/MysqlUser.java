package sample;

import java.util.LinkedList;

public class MysqlUser implements IUser,IGeneric<User> {
    @Override
    public LinkedList<User> GetAll() {
        return null;
    }

    @Override
    public User getByID(String id) {
        return null;
    }

    @Override
    public void Delete(String id) {

    }

    @Override
    public void Create(User entity) {

    }

    @Override
    public int Update(User entity) {
        return 0;
    }
}
