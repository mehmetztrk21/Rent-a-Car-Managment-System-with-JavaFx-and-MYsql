package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button logout;
    @FXML
    public Button musteri;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                try {
                    Main.root = FXMLLoader.load(getClass().getResource("musteri.fxml"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene=new Scene(Main.root);
                scene.getStylesheets().add("sample/style.css");
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
    }
}
