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

public class KargoController implements Initializable {
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
    private TextField cargoip;
    @FXML
    private TextField cargoname;
    @FXML
    private TextField cargosurname;
    @FXML
    private TextField cargophone;
    @FXML
    private TextArea cargoaddress;
    ////////////////////////////
    @FXML
    private TextArea cargoaddressshow;

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
    private Label cargofindlabel2;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private Button cupdate;

    @FXML
    private TextField cargonameshow1;

    @FXML
    private TextArea cargoaddressshow1;

    @FXML
    private TextField cargophoneshow1;

    @FXML
    private TextField cargosurnameshow1;

    @FXML
    private Tab kargYolTab;

    @FXML
    void btndurumgoster(ActionEvent event) {
        Cargo cargo=null;
        cargo=getById(deletecargoip.getText());
        if(cargo!=null){
            cargonameshow1.setText(cargo.getName());
            cargosurnameshow1.setText(cargo.getSurname());
            cargophoneshow1.setText(cargo.getPhone());
            cargoaddressshow1.setText(cargo.getAddress());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Girilen ID ile ilişkili bir kargo bulunamadı."));
            alert.showAndWait();
        }
    }

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
                cargo.setAddress(cargoaddressshow.getText());
                UpdateCargo(cargo);
            }
        });
        cshow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cargo cargo=null;
                cargo=getById(cargoidshow.getText());
                if(cargo!=null){
                    cargonameshow.setText(cargo.getName());
                    cargosurnameshow.setText(cargo.getSurname());
                    cargophoneshow.setText(cargo.getPhone());
                    cargoaddressshow.setText(cargo.getAddress());
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
        MysqlUser user = new MysqlUser();
        User user1 = user.getUser();
        if(user1.getRole_id() == 1){
            kargYolTab.setDisable(false);
        }
        else{
            kargYolTab.setDisable(true);
        }
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
        alert.setContentText((name+" adlı kargo sahibi "+ cargo.getLastCargo() +" id numarasıyla sisteme eklendi."));
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

    public void FindCargo(String id){
        MysqlCargo cargo=new MysqlCargo();
        Cargo cargo1=cargo.getByID(id);
        try {
            if(cargo1.getState() == 1){
                cargofindlabel.setText("Kargo Hazırlanıyor.");
                progressbar.setProgress(0.1);
            }
            else if(cargo1.getState() == 2){
                cargofindlabel.setText("Kargo yolda.");
                progressbar.setProgress(0.5);
            }
            else if(cargo1.getState() == 3){
                cargofindlabel.setText("Teslim edildi.");
                progressbar.setProgress(1.0);
            }
            else if(cargo1.getState() != 2){
                cargofindlabel.setText("Durum");
            }
            //cargofindlabel.setStyle("-fx-background-color:white;");
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
