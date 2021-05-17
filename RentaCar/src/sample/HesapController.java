package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HesapController {
    @FXML
    private TextField soncelSoyad;

    @FXML
    private TextField guncelad;

    @FXML
    private TextField guncelTelf;

    @FXML
    private TextField guncelMail;

    @FXML
    private Button emsave;

    @FXML
    void guncel(ActionEvent event) {

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
