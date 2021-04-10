package sample;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public Button login;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (username.getText().equals("mehmet") && password.getText().equals( "123")) {

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
}
