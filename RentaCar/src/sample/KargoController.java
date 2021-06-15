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
    private Button sendcargo;

    @FXML
    private Button cshow;

    @FXML
    private Button cdelete;

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
    private TextField carrgoipfind;
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
        Cargo cargo = null;
        cargo = getById(deletecargoip.getText());
        if (cargo != null) {
            cargonameshow1.setText(cargo.getName());
            cargosurnameshow1.setText(cargo.getSurname());
            cargophoneshow1.setText(cargo.getPhone());
            cargoaddressshow1.setText(cargo.getAddress());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Girilen ID ile ilişkili bir kargo bulunamadı."));
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sendcargo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cargo cargo=null;
                cargo=getById(cargoip.getText());
                cargoname.setText(cargo.getName());
                System.out.println("x: "+cargo.getName());
                cargosurname.setText(cargo.getSurname());
                cargophone.setText(cargo.getPhone());
                cargoaddress.setText(cargo.getAddress());
            }
        });

        csend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CreateCargo(Integer.parseInt(cargoip.getText()),cargoname.getText(), cargosurname.getText(), cargophone.getText(), cargoaddress.getText());
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
                Cargo cargo = null;
                cargo = getById(cargoidshow.getText());
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
                Cargo cargo = null;
                cargo = getById(cargoidshow.getText());
                if (cargo != null) {
                    cargonameshow.setText(cargo.getName());
                    cargosurnameshow.setText(cargo.getSurname());
                    cargophoneshow.setText(cargo.getPhone());
                    cargoaddressshow.setText(cargo.getAddress());
                } else {
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
        if (user1.getRole_id() == 1) {
            kargYolTab.setDisable(false);
        } else {
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
        Scene scene = new Scene(Main.root);
        scene.getStylesheets().add("sample/style.css");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    public void CreateCargo(int id,String name, String surname, String phone, String address) {
        MysqlCargo cargo = new MysqlCargo();
        Cargo new_cargo = new Cargo(id,name, surname, phone, address);
        int temp = cargo.send(new_cargo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (temp > 0) {
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText((name + " adlı kargo sahibine " + id + " sipariş kargolandı."));
            cargoname.setText("");
            cargosurname.setText("");
            cargophone.setText("");
            cargoaddress.setText("");
            cargoip.setText("");

        }
        else{
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Sistem Hatası."));
        }
        alert.showAndWait();

        //tcustomer.setItems(getAll());
    }

    public void DeleteCargo(String id) {
        MysqlCargo cargo = new MysqlCargo();
        cargo.Delete(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText("Sistem Mesajı");
        alert.setContentText(("Kargo iptal edildi."));
        alert.showAndWait();
        cargonameshow1.setText("");
        cargosurnameshow1.setText("");
        cargophoneshow1.setText("");
        cargoaddressshow1.setText("");
        deletecargoip.setText("");

    }

    public Cargo getById(String id) {
        MysqlCargo cargo = new MysqlCargo();
        return cargo.getByID(id);
    }

    public void FindCargo(String id) {
        MysqlCargo cargo = new MysqlCargo();
        Cargo cargo1 = cargo.getByID(id);
        try {
            if (cargo1.getState() == 1) {
                progressbar.setProgress(0.1);
            } else if (cargo1.getState() == 2) {
                progressbar.setProgress(0.5);
            } else if (cargo1.getState() == 3) {
                progressbar.setProgress(1.0);
            }
        } catch (Exception e) {
        }

    }

    public void UpdateCargo(Cargo carg) {
        MysqlCargo cargo = new MysqlCargo();
        int count = cargo.Update(carg);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (count > 0) {
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText((carg.getName() + " adlı kargo güncellendi."));
            alert.showAndWait();
            //tcustomer.setItems(getAll());
        } else {
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Hata ile karşılaşıldı."));
            alert.showAndWait();
        }
    }


}
