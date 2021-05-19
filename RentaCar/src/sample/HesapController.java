package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HesapController implements Initializable {
    @FXML
    private TextField usernameupdate;

    @FXML
    private TextField nameupdate;
    @FXML
    private TextField surnameupdate;
    @FXML
    private TextField passwordupdate;

    @FXML
    private TextField phoneupdate;

    @FXML
    private TextField mailupdate;


    @FXML
    private Button updatebutton;

    @FXML
    void guncel(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user=null;
        user=getUser();
        final int id= user.getId();
        usernameupdate.setText(user.getUsername());
        nameupdate.setText(user.getName());
        surnameupdate.setText(user.getSurname());
        passwordupdate.setText(user.getPassword());
        phoneupdate.setText(user.getPhone());
        mailupdate.setText(user.getEmail());
        updatebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                update(id,usernameupdate.getText(),nameupdate.getText(),surnameupdate.getText(),passwordupdate.getText(),phoneupdate.getText(),mailupdate.getText());
            }
        });
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

    public User getUser(){
        MysqlUser user = new MysqlUser();
        User new_user = null;
        new_user = user.getUser();
        return new_user;

    }
    public void update(int id,String username,String name,String surname,String password,String phone,String mail) {
        MysqlUser user = new MysqlUser();
        User user_update = null;
        user_update = user.getByID(String.valueOf(id));
        user_update.setName(name);
        user_update.setSurname(surname);
        user_update.setUsername(username);
        user_update.setEmail(mail);
        user_update.setPhone(phone);
        user_update.setPassword(password);
        int count = user.Update(user_update);
        if (count > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Kullanıcı bilgileriniz güncellendi."));
            alert.showAndWait();
        }

    }
}
