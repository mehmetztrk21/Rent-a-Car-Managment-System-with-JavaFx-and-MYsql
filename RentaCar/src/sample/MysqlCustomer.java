package sample;

import java.sql.*;
import java.util.LinkedList;

public class MysqlCustomer implements ICustomer, IGeneric<Customer> {

    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.


    @Override
    public LinkedList<Customer> findMusteri(String name, String surname, int kayıt) {
        return null;
    }

    @Override
    public LinkedList<Customer> GetAll() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from customer");  //gelen sonuçlar da resultSet e aktarılıyor.

            LinkedList<Customer> customers = new LinkedList<Customer>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                customers.add(new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("username"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getDouble("last_order"), resultSet.getDouble("total_order"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return customers;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getByID(String id) {
        Customer customer = null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from customer where id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getDouble("last_order"), resultSet.getDouble("total_order")); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return customer;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customer;
    }

    @Override
    public void Delete(String id) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql_delete = "delete from customer where id=?";
            statement2 = connection.prepareStatement(sql_delete);
            statement2.setString(1, id);
            statement2.executeUpdate();
            String sql_delete2 = "delete from user where id=?";
            statement2 = connection.prepareStatement(sql_delete2);
            statement2.setString(1, id);
            int result = statement2.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void Create(Customer entity) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into customer (name,surname,phone,username,password,email,last_order,total_order) values(?,?,?,?,?,?,?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getPhone());
            statement2.setString(4, entity.getKullanici_adi());
            statement2.setString(5, entity.getSifre());
            statement2.setString(6, entity.getEmail());
            statement2.setDouble(7, 0);
            statement2.setDouble(8, 0);
            int result = statement2.executeUpdate();  //etkilenen satır sayısını verir.

            String sqll = "insert into user (name,surname,username,password,phone,email,role_id) values(?,?,?,?,?,?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sqll); //sql kodunun çalışması için
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getKullanici_adi());
            statement2.setString(4, entity.getSifre());
            statement2.setString(5, entity.getPhone());
            statement2.setString(6, entity.getEmail());
            statement2.setDouble(7, 0);
            int resultt = statement2.executeUpdate();  //etkilenen satır sayısını verir.

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



    @Override
    public int Update(Customer entity) {
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update customer set name=? ,surname=?, phone=? , email=? , last_order=? ,total_order=? where id=?";
            statement2 = connection.prepareStatement(sql_update);
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getPhone());
            statement2.setString(4, entity.getEmail());
            statement2.setDouble(5, entity.getLast_order());
            statement2.setDouble(6, entity.getTotal_order());
            statement2.setInt(7, entity.getId());
            statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı


            String sql_update2 = "update user set name=? ,surname=?, phone=?, email=? where id=?";
            statement2 = connection.prepareStatement(sql_update2);
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getPhone());
            statement2.setString(4, entity.getEmail());
            statement2.setInt(5, entity.getId());
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı

            return result;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        return 0;
    }

    public Customer getByUsername(String username) {
        Customer customer = null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from customer where username='" + username + "'");  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getDouble("last_order"), resultSet.getDouble("total_order")); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return customer;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customer;
    }
}
