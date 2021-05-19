package sample;

public interface IUser {
    boolean search(String username,String password);
    void savelogin(String username);
    User getUser();

}
