package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class YetkiController implements Initializable {
    @FXML
    private TableView<User> contactView;

    @FXML
    private Button btn;

    @FXML
    private TextField sifre;

    @FXML
    private Label soyadLbl;

    @FXML
    private Label KAdiLbl;

    @FXML
    private Label telfLbl;

    @FXML
    private Label mailLbl;

    @FXML
    private Label adLbl;

    @FXML
    private TableColumn<User, String> colAd;

    @FXML
    private TableColumn<User, String> colSoyad;

    @FXML
    private TableColumn<User, String> colKAd;

    @FXML
    private TableColumn<User, String> colTel;

    @FXML
    private TableColumn<User, Integer> colId;

    @FXML
    private TableColumn<User, Integer> colDurum;

    private ObservableList<User> list;

    @FXML
    void yetkilendirBtn(ActionEvent event) {//TODO seçilen satırdaki kişinin şifre girilip butona basılınca role_id'si 1 olacak 1=yetkili 0=müşteri şeklinde
        //todo durum sütunu role_id ye eşit eğer role_id=1 se veritabanında durum sütununda Yetkili yazısı yazacak
        //todo büyük ihtimalle satır seçilince label'lara yazma kısmını yaptım olmamışsa da daha sonra tekrar yaparım
        ////todo events() daki gibi yetki.getRole_id dersen o satırın o sütunu getirilmiş olur

    }

    private void initTables(){
        colAd.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        colSoyad.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));
        colKAd.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        colTel.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        colId.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colDurum.setCellValueFactory(new PropertyValueFactory<User,Integer>("role_id"));
    }

    private void loadTableData(){
        MysqlUser user=new MysqlUser();
        ObservableList<User> users = FXCollections.observableArrayList();
        LinkedList<User> get_users=new LinkedList<User>();
        get_users=user.GetAll();
        for (int i=0;i<get_users.size();i++){
            users.add(get_users.get(i));
        }
        contactView.setItems(users);
        contactView.setOnMouseClicked(e ->{
            events();
        });

    }

    private void events(){
        btn.setDisable(false);
        int id;
        for (User yetki:contactView.getSelectionModel().getSelectedItems()){
            adLbl.setText(yetki.getName());
            soyadLbl.setText(yetki.getSurname());
            KAdiLbl.setText(yetki.getUsername());
            telfLbl.setText(yetki.getPhone());
            mailLbl.setText(String.valueOf(yetki.getId()));
            mailLbl.setVisible(false);
        }
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MysqlUser user=new MysqlUser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                if(user.getUser().getPassword().equals(sifre.getText())){
                    User  user_update=user.getByID(mailLbl.getText());
                    user_update.setRole_id(1);
                    user.Update(user_update);
                    alert.setTitle("Başarılı");
                    alert.setHeaderText("Sistem Mesajı");
                    alert.setContentText(("Seçilen kullanıcı Admin olarak yetkilendirildi."));
                    alert.showAndWait();
                }
                else{
                    alert.setTitle("HATA");
                    alert.setHeaderText("Sistem Mesajı");
                    alert.setContentText(("Yanlış şifre girildi."));
                    alert.showAndWait();
                }

            }
        });
        events();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn.setDisable(true);
        list= FXCollections.observableArrayList();
        initTables();
        loadTableData();

    }
}
