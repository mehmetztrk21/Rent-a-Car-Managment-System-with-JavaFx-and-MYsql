package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class TedarikController {
    @FXML
    private Button home1;

    @FXML
    private Button home2;

    @FXML
    private Button home3;

    @FXML
    private TextField txtdurumip;

    @FXML
    private Button btndurumgoster;

    @FXML
    private Button btndurumiptal;

    @FXML
    private Label lbldurum;

    @FXML
    private ImageView imgdurum;

    @FXML
    private ListView<?> listdurum;

    @FXML
    private Button home4;

    @FXML
    private TextField txtdurumip1;

    @FXML
    private Button btndurumgoster1;

    @FXML
    private Label lbldurum1;

    @FXML
    private ImageView imgdurum1;

    @FXML
    private Button home5;

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
