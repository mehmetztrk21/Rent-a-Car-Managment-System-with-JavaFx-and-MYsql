package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MusteriController implements Initializable {
    @FXML
    public TextField emname;
    @FXML
    public TextField emsurname;
    @FXML
    public TextField emtel;
    @FXML
    public TextField emmail;
    @FXML
    public Button emsave;
    @FXML
    public Button emfind;
    @FXML
    public Button home;
    @FXML
    public Button home2;
    @FXML
    public Button home3;
    @FXML
    public Button home4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emname.setVisible(false);
        emsurname.setVisible(false);
        emtel.setVisible(false);
        emmail.setVisible(false);
        emsave.setVisible(false);

        emfind.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              emname.setVisible(true);
              emsurname.setVisible(true);
              emtel.setVisible(true);
              emmail.setVisible(true);
              emsave.setVisible(true);

          }
      });
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

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
        });
        home2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

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
        });
        home3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

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
        });
        home4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

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
        });

        //emname.setStyle("-fx-background-radius: 250px; -fx-background-color:black;");
    }
}
