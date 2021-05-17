package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class YetkiController {
    @FXML
    private Label id;

    @FXML
    private Label ad;

    @FXML
    private Label soyad;

    @FXML
    private Label telefon;

    @FXML
    private Label mail;

    @FXML
    private TextField sifre;

    @FXML
    void yetkilendir(ActionEvent event) {

    }
    @FXML
    void home(ActionEvent event) {
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
}
