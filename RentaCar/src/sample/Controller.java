package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    public Button logout;
    @FXML
    public Button musteri;
    @FXML
    private Button tedarik;
    @FXML
    private Button maliye;
    @FXML
    private Button tahmin;
    @FXML
    private Button kargo;
    @FXML
    private Button ayarlar;
    @FXML
    private Label date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datewrite();
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.root = FXMLLoader.load(getClass().getResource("login.fxml"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene=new Scene(Main.root);
                scene.getStylesheets().add("sample/style.css");
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
        musteri.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MysqlUser user = new MysqlUser();
                User user1 = user.getUser();
                if(user1.getRole_id() == 1){
                    try{
                        Main.root = FXMLLoader.load(getClass().getResource("musteri.fxml"));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Scene scene=new Scene(Main.root);
                    scene.getStylesheets().add("sample/style.css");
                    Main.stage.setScene(scene);
                    Main.stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Yetkisiz Giriş!");
                    alert.setContentText("Bu sayfaya yetkili harici giriş yapılmamaktadır.");
                    alert.showAndWait();
                }
            }
        });
        tedarik.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MysqlUser user = new MysqlUser();
                User user1 = user.getUser();
                if(user1.getRole_id() == 1){
                    try{
                        Main.root = FXMLLoader.load(getClass().getResource("tedarik.fxml"));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Scene scene=new Scene(Main.root);
                    scene.getStylesheets().add("sample/style.css");
                    Main.stage.setScene(scene);
                    Main.stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Yetkisiz Giriş!");
                    alert.setContentText("Bu sayfaya yetkili harici giriş yapılmamaktadır.");
                    alert.showAndWait();
                }
            }
        });
        kargo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Main.root = FXMLLoader.load(getClass().getResource("kargo.fxml"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                Scene scene=new Scene(Main.root);
                scene.getStylesheets().add("sample/style.css");
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
        maliye.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Main.root = FXMLLoader.load(getClass().getResource("maliye.fxml"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                Scene scene=new Scene(Main.root);
                scene.getStylesheets().add("sample/style.css");
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });

        ayarlar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Main.root = FXMLLoader.load(getClass().getResource("hesap.fxml"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                Scene scene=new Scene(Main.root);
                scene.getStylesheets().add("sample/style.css");
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
        tahmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MysqlUser user = new MysqlUser();
                User user1 = user.getUser();
                if(user1.getRole_id() == 1){
                    try{
                        Main.root = FXMLLoader.load(getClass().getResource("yillik.fxml"));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Scene scene=new Scene(Main.root);
                    scene.getStylesheets().add("sample/style.css");
                    Main.stage.setScene(scene);
                    Main.stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Yetkisiz Giriş!");
                    alert.setContentText("Bu sayfaya yetkili harici giriş yapılmamaktadır.");
                    alert.showAndWait();
                }
            }
        });

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
