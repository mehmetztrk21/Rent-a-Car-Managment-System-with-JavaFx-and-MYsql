package sample;


import java.sql.*;
import java.util.ArrayList;

public class MysqlOrders implements IOrder {
    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.

    @Override
    public void Create(int id, String product) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into orders (name,cargo_id) values(?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, product);  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setInt(2, id);

            int result = statement2.executeUpdate();  //etkilenen satır sayısını verir.

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void Delete(int id, String name) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql_delete = "delete from orders where cargo_id=? and name=?";
            statement2 = connection.prepareStatement(sql_delete);
            statement2.setInt(1, id);
            statement2.setString(2, name);
            int result = statement2.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public ArrayList<Order> GetById(String id) {
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from orders where cargo_id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                orders.add(new Order(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("cargo_id"))); //select ile çağırdığımız verileri bir arraylist e attık.); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return orders;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return orders;
    }


}
