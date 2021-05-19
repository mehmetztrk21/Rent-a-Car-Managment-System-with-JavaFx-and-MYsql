package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class MysqlCargo implements ICargo, IGeneric<Cargo> {

    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.
    ResultSet resultSet2;


    @Override
    public LinkedList<Cargo> findCargo(int id) {
        return null;
    }
    @Override
    public LinkedList<Cargo> GetAll() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from cargo");  //gelen sonuçlar da resultSet e aktarılıyor.

            LinkedList<Cargo> cargos = new LinkedList<Cargo>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                cargos.add(new Cargo(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("phone"), resultSet.getString("address"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return cargos;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Cargo getByID(String id) {
        String person = " ";
        Cargo cargo=null;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from cargo where id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                cargo=new Cargo(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("phone"),resultSet.getString("address")); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return cargo;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cargo;
    }

    @Override
    public void Delete(String id) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql_delete = "delete from cargo where id=?";
            statement2 = connection.prepareStatement(sql_delete);
            statement2.setString(1, id);
            int result = statement2.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
//    public Customer(int id, String name, String surname, String phone, String email,                           double last_order, double total_order) {

    @Override
    public void Create(Cargo entity) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into cargo (name,surname,phone,address) values(?,?,?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getPhone());
            statement2.setString(4, entity.getAddress());

            int result = statement2.executeUpdate();  //etkilenen satır sayısını verir.

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public int Update(Cargo entity) {
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update cargo set name=? ,surname=?, phone=? , address=? where id=?";
            statement2 = connection.prepareStatement(sql_update);
            statement2.setString(1, entity.getName());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, entity.getSurname());
            statement2.setString(3, entity.getPhone());
            statement2.setString(4, entity.getAddress());
            statement2.setInt(5,entity.getId());
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı
            return result;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        return 0;
    }
}
