package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String user = "root";
    private String pass = "ztrk2134";
    private String dburl = "jdbc:mysql://localhost/car?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException { //veritabanı bağlantısı fonksiyonu.
        return DriverManager.getConnection(dburl, user, pass);
    }

    public void showErrorMessage(SQLException exception) {  //hata durumunda ekrana yazılacaklar
        System.out.println("Error : " + exception.getMessage());
        System.out.println("Error Code : " + exception.getErrorCode());
    }


}
