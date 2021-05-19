package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TedarikController implements Initializable {
    @FXML
    private Button csend;

    @FXML
    private Button cfind;

    @FXML
    private Button cshow;

    @FXML
    private Button cdelete;

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
    private TextField cargoid;
    @FXML
    private TextField cargoname;
    @FXML
    private TextField cargosurname;
    @FXML
    private TextField cargophone;
    @FXML
    private TextArea cargoaddress;

    @FXML
    private TextField deletecargoip;

    @FXML
    private TextField cargoidshow;
    @FXML
    private TextField cargonameshow;
    @FXML
    private TextField cargosurnameshow;
    @FXML
    private TextField cargophoneshow;
    @FXML
    private TextArea cargoadressshow;

    @FXML
    private TextField carrgoipfind;

    @FXML
    private Label cargofindlabel;

    @FXML
    private Button cupdate;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CreateCargo(cargoname.getText(),cargosurname.getText(),cargophone.getText(),cargoaddress.getText());
            }
        });

        cdelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DeleteCargo(deletecargoip.getText());
            }
        });
        cfind.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FindCargo(carrgoipfind.getText());
            }
        });
        cupdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cargo cargo=null;
                cargo=getById(cargoidshow.getText());
                cargo.setName(cargonameshow.getText());
                cargo.setSurname(cargosurnameshow.getText());
                cargo.setPhone(cargophoneshow.getText());
                //cargo.setAddress(cargoaddressshow.getText());
                UpdateCargo(cargo);
                cargonameshow.setVisible(false);
                cargosurnameshow.setVisible(false);
                cargophoneshow.setVisible(false);
                //cargoaddressshow.setVisible(false);
                cupdate.setVisible(false);
            }
        });
        cshow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cargo cargo=null;
                cargo=getById(cargoidshow.getText());
                if(cargo!=null){
                    cargonameshow.setVisible(true);
                    cargosurnameshow.setVisible(true);
                    cargophoneshow.setVisible(true);
                    //cargoaddressshow.setVisible(true);
                    cargonameshow.setText(cargo.getName());
                    cargosurnameshow.setText(cargo.getSurname());
                    cargophoneshow.setText(cargo.getPhone());
                    //cargoaddressshow.setText(cargo.getAddress());
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Sistem Mesajı");
                    alert.setContentText(("Girilen ID ile ilişkili bir kargo bulunamadı."));
                    alert.showAndWait();
                }



            }
        });
        csend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cargo cargo=null;
                cargo=getById(cargoid.getText());
                cargo.setName(cargoname.getText());
                cargo.setSurname(cargosurname.getText());
                cargo.setPhone(cargophone.getText());
                cargo.setAddress(cargoaddress.getText());
                SaveCargo(cargo);
                cargoname.setVisible(false);
                cargosurname.setVisible(false);
                cargophone.setVisible(false);
                cargoaddress.setVisible(false);
                csend.setVisible(false);
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

    public void CreateCargo(String name, String surname,String phone,String address){
        MysqlCargo cargo=new MysqlCargo();
        Cargo new_cargo=new Cargo(name,surname,phone,address);

        cargo.Create(new_cargo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText("Sistem Mesajı");
        alert.setContentText((name+" adlı müşteri sisteme eklendi."));
        alert.showAndWait();
        //tcustomer.setItems(getAll());
    }
    public void DeleteCargo(String id){
        MysqlCargo cargo=new MysqlCargo();
        cargo.Delete(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText("Sistem Mesajı");
        alert.setContentText(("Kargo iptal edildi."));
        alert.showAndWait();
        //tcustomer.setItems(getAll());
    }
    public Cargo getById(String id){
        MysqlCargo cargo=new MysqlCargo();
        return cargo.getByID(id);
    }
    public void SaveCargo(Cargo carg){
        MysqlCargo cargo=new MysqlCargo();
        int count= cargo.Update(carg);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(count>0){
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText((carg.getName()+" adlı kargo güncellendi."));
            alert.showAndWait();
            //tcustomer.setItems(getAll());
        }
        else{
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Hata ile karşılaşıldı."));
            alert.showAndWait();
        }
    }
    public void FindCargo(String id){
        MysqlCargo cargo=new MysqlCargo();
        Cargo cargo1=cargo.getByID(id);
        try {
            cargofindlabel.setText("\t\t\t"+cargo1.getName().toUpperCase()+"  "+cargo1.getSurname().toUpperCase());
            cargofindlabel.setStyle("-fx-background-color:white;");
        }
        catch (Exception e){
            cargofindlabel.setStyle("-fx-background-color:none;");
            cargofindlabel.setText("");
        }

    }
    public void UpdateCargo(Cargo carg){
        MysqlCargo cargo=new MysqlCargo();
        int count= cargo.Update(carg);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(count>0){
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText((carg.getName()+" adlı kargo güncellendi."));
            alert.showAndWait();
            //tcustomer.setItems(getAll());
        }
        else{
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Hata ile karşılaşıldı."));
            alert.showAndWait();
        }
    }



}
