package sample;

import java.sql.*;
import java.util.LinkedList;

public class MysqlUrunler implements IUrunler {

    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.

    @Override
    public LinkedList<Urunler> GetAll() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT * from parcalar");  //gelen sonuçlar da resultSet e aktarılıyor.

            LinkedList<Urunler> urunler = new LinkedList<Urunler>();
            LinkedList<Urunler> urunler2 = new LinkedList<Urunler>();

            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                urunler.add(new Urunler(resultSet.getString("URUN_KODU"), resultSet.getString("MARKA"), resultSet.getString("URUN_ADI"), resultSet.getString("2021_Guncel"), resultSet.getString("2021_Tahmin"))); //select ile çağırdığımız verileri bir arraylist e attık.
                urunler2.add(new Urunler(resultSet.getString("URUN_KODU"), resultSet.getString("MARKA"), resultSet.getString("URUN_ADI"), resultSet.getString("SATINALMA_FIYATI")+ resultSet.getString("2021_Tahmin"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return urunler;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
                public LinkedList<Urunler> GetAll2() {
                    try {
                        connection = helper.getConnection();  //bağlanma.
                        System.out.println("Bağlantı oluştu.");
                        statement = connection.createStatement();  //statement böyle oluyor.(select için)

                        resultSet = statement.executeQuery("SELECT * from parcalar");  //gelen sonuçlar da resultSet e aktarılıyor.

                        LinkedList<Urunler> urunler2 = new LinkedList<Urunler>();

                        while (resultSet.next()) {//burda da gelen dataları array liste atıyoruz.
                urunler2.add(new Urunler(resultSet.getString("URUN_KODU"), resultSet.getString("MARKA"), resultSet.getString("URUN_ADI"), resultSet.getString("2021_GUNCEL"), resultSet.getString("SATINALMA_FIYATI")+" "+ resultSet.getString("SATINALMA_BIRIM"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return urunler2;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public  void addStock(String ip){
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update parcalar set 2021_Guncel=? where URUN_KODU=?";
            statement2 = connection.prepareStatement(sql_update);

            statement2.setInt(1,getById(ip)+1);
            statement2.setString(2,ip);
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }

    }
    public  void minusStock(String ip){
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update parcalar set 2021_Guncel=? where URUN_KODU=?";
            statement2 = connection.prepareStatement(sql_update);

            statement2.setInt(1,getById(ip)-1);
            statement2.setString(2,ip);
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }

    }

    public int getById(String ip){
        int stock=0;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT 2021_Guncel from parcalar where URUN_KODU='" + ip+"'");  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                stock= resultSet.getInt("2021_Guncel");
            }
            return stock;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return stock;
    }
}
