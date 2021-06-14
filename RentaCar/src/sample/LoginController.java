package sample;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class    LoginController implements Initializable {

    @FXML
    public Button login;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Label date;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datewrite();
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (control(username.getText(),password.getText())) {
                    MysqlUser user = new MysqlUser();
                    user.savelogin(username.getText());

                    try {
                        Main.root = FXMLLoader.load(getClass().getResource("RentaCar.fxml"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene=new Scene(Main.root);
                    scene.getStylesheets().add("sample/style.css");
                    Main.stage.setScene(scene);
                    Main.stage.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Hatalı Giriş!");
                    alert.setContentText("Kullanıcı adı veya parola yanlış.");
                    alert.showAndWait();
                }
            }


        });
    }
    public boolean control(String username, String password){
        MysqlUser user = new MysqlUser();
        System.out.println(username+ password);
        return user.search(username,password);
    }
    public void datewrite() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Date datex=new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    date.setText(formatter.format(datex).toString());
                });
            }
        }, 0, 1000);

    }
}
