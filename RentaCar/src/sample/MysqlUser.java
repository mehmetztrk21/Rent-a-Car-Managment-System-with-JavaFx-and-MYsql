package sample;

import java.sql.*;
import java.util.LinkedList;

public class MysqlUser implements IUser,IGeneric<User> {

    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.
    ResultSet resultSet2;
    @Override
    public LinkedList<User> GetAll() {

        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from user");  //gelen sonuçlar da resultSet e aktarılıyor.

            LinkedList<User> users = new LinkedList<User>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getInt("role_id"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return users;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByID(String id) {
        User user=null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from user where id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                user=new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("phone"), resultSet.getString("email"),resultSet.getInt("role_id")); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public void Delete(String id) {

    }

    @Override
    public void Create(User entity) {}


    @Override
    public int Update(User entity) {
        int result=0;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update user set name=? ,surname=?,username=?, password=?, phone=? , email=? , role_id=? where id=?";
            statement2 = connection.prepareStatement(sql_update);
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getUsername());
            statement2.setString(4, entity.getPassword());
            statement2.setString(5, entity.getPhone());
            statement2.setString(6, entity.getEmail());
            statement2.setDouble(7, entity.getRole_id());
            statement2.setInt(8,entity.getId());
            statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı
            savelogin(entity.getUsername());

            String sql_update2 = "update customer set name=? ,surname=?,username=?, password=?, phone=? , email=? where id=?";
            statement2 = connection.prepareStatement(sql_update2);
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getUsername());
            statement2.setString(4, entity.getPassword());
            statement2.setString(5, entity.getPhone());
            statement2.setString(6, entity.getEmail());
            statement2.setInt(7,entity.getId());
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı

            return result;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        return 0;
    }

    @Override
    public boolean search(String username, String password) {
        User user=null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from user where username='" + username + "'and password='" +password+"'");  //gelen sonuçlar da resultSet e aktarılıyor.

            int count = 0;
            while (resultSet.next()) {
                count++;
                //burda da gelen dataları array liste atıyoruz.
            }
            if(count>0){
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public void savelogin(String username) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into login_history (user_name) values(?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, username);  //bunlar da koddaki soru işaretleri yerine gelecekler.

            int result = statement2.executeUpdate();  //etkilenen satır sayısını verir.

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public User getUser() {
        User user=null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * FROM login_history ORDER BY id DESC LIMIT 1");  //gelen sonuçlar da resultSet e aktarılıyor.

            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                String x=resultSet.getString("user_name");
                resultSet = statement.executeQuery("SELECT * from user where username='"+x+"'");  //gelen sonuçlar da resultSet e aktarılıyor.
                //    public User(int id, String name, String surname, String username, String password, String phone, String email, int role_id) {
                while(resultSet.next()){
                    user=new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),resultSet.getString("username"),resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getInt("role_id")); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.

                }
            }
            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }
}