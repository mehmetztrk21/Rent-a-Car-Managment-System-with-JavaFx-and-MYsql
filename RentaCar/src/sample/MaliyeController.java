package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MaliyeController{

    @FXML
    private TextField urunAra;

    @FXML
    private TextField markaAra;

    @FXML
    private ListView<?> SatinUrunler;

    @FXML
    private RadioButton Kapida;

    @FXML
    private ToggleGroup Odeme;

    @FXML
    private RadioButton Kartla;

    @FXML
    private Label topFiyat;

    @FXML
    private TextField SatinAdSoyad;

    @FXML
    private TextField SatinTelf;

    @FXML
    private TextArea SatinAdres;

    @FXML
    private TextField txtIptalip;

    @FXML
    private Button btndurumgoster;

    @FXML
    private Button btndurumiptal;

    @FXML
    private ListView<?> listIptal;

    @FXML
    private TextField txtBilgiIP;

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

    @FXML
    void geri(ActionEvent event) {
        try {
            Main.root = FXMLLoader.load(getClass().getResource("maliye.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(Main.root);
        scene.getStylesheets().add("sample/style.css");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    void SatinAl(ActionEvent event) {
        if(Kapida.isSelected()){
            float fiyat = Float.parseFloat(Kapida.getText());
            fiyat+=9.99;
            Kapida.setText(String.valueOf(fiyat));
        }else if(Kartla.isSelected()){
            try {
                Main.root = FXMLLoader.load(getClass().getResource("card.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(Main.root);
            scene.getStylesheets().add("sample/style.css");
            Main.stage.setScene(scene);
            Main.stage.show();
        }
    }

}
