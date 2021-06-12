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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.print.DocFlavor;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MusteriController implements Initializable {
    @FXML
    public TextField caname;
    @FXML
    public TextField casurname;
    @FXML
    public TextField caphone;
    @FXML
    public TextField camail;
    @FXML
    public Button cadd;
    @FXML
    public TextField emmail;
    @FXML
    public TextField emname;
    @FXML
    public TextField emsurname;
    @FXML
    public TextField emphone;
    @FXML
    public TextField emid;

    @FXML
    public Button emsave;
    @FXML
    public Button emfind;
    @FXML
    private Button solOk;
    @FXML
    public Button home;

    @FXML
    public TableView<Customer> tcustomer;

    @FXML
    private TableColumn<Customer, Integer> cid;
    @FXML
    private TableColumn<Customer, String> cname;
    @FXML
    private TableColumn<Customer, String> csurname;
    @FXML
    private TableColumn<Customer, String> cphone;
    @FXML
    private TableColumn<Customer, String> cmail;
    @FXML
    private TableColumn<Customer, Double> ctotal;
    @FXML
    private TableColumn<Customer, Double> clast;


    @FXML
    public TextField dcid;

    @FXML
    public Button controlc;

    @FXML
    public Button deletec;

    @FXML
    public Label controlcustomername;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn.setDisable(true);
        list= FXCollections.observableArrayList();
        initTables();
        loadTableData();

        //Customer sınıfı sütun adları.
        cid.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        cname.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        csurname.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
        cphone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        cmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        ctotal.setCellValueFactory(new PropertyValueFactory<Customer, Double>("total_order"));
        clast.setCellValueFactory(new PropertyValueFactory<Customer, Double>("last_order"));

        tcustomer.setItems(getAll());  //tabloya ekleme.

        emname.setVisible(false);
        emsurname.setVisible(false);
        emphone.setVisible(false);
        emmail.setVisible(false);
        emsave.setVisible(false);
        controlcustomername.setStyle("-fx-background-color:none;");


        cadd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CreateCustomer(caname.getText(),casurname.getText(),camail.getText(),caphone.getText());
            }
        });

        deletec.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DeleteCustomer(dcid.getText());
            }
        });
        controlc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ControlCustomer(dcid.getText());
            }
        });


        emfind.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              Customer customer=null;
              customer=getById(emid.getText());
              if(customer!=null){
                  emname.setVisible(true);
                  emsurname.setVisible(true);
                  emphone.setVisible(true);
                  emmail.setVisible(true);
                  emsave.setVisible(true);
                  emname.setText(customer.getName());
                  emsurname.setText(customer.getSurname());
                  emphone.setText(customer.getPhone());
                  emmail.setText(customer.getEmail());
              }
              else{
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Hata");
                  alert.setHeaderText("Sistem Mesajı");
                  alert.setContentText(("Girilen ID ile ilişkili bir kullanıcı bulunamadı."));
                  alert.showAndWait();
              }



          }
      });
        emsave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Customer customer=null;
                customer=getById(emid.getText());
                customer.setName(emname.getText());
                customer.setSurname(emsurname.getText());
                customer.setPhone(emphone.getText());
                customer.setEmail(emmail.getText());
                SaveCustomer(customer);
                emname.setVisible(false);
                emsurname.setVisible(false);
                emphone.setVisible(false);
                emmail.setVisible(false);
                emsave.setVisible(false);
            }
        });


        //emname.setStyle("-fx-background-radius: 250px; -fx-background-color:black;");
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
    public ObservableList<Customer> getAll(){
        MysqlCustomer customer=new MysqlCustomer();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        LinkedList<Customer> get_customers=new LinkedList<Customer>();
        get_customers=customer.GetAll();
        for (int i=0;i<get_customers.size();i++){
            customers.add(get_customers.get(i));
        }
        return customers;
    }
    public void CreateCustomer(String name, String surname,String mail,String phone){
        MysqlCustomer customer=new MysqlCustomer();
        Customer new_customer=new Customer(name,surname,mail,phone);

        customer.Create(new_customer);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText("Sistem Mesajı");
        alert.setContentText((name+" adlı müşteri sisteme eklendi."));
        alert.showAndWait();
        tcustomer.setItems(getAll());
    }
    public void ControlCustomer(String id){
        MysqlCustomer customer=new MysqlCustomer();
        Customer customer1=customer.getByID(id);
        try {
            controlcustomername.setText("\t\t\t"+customer1.getName().toUpperCase()+"  "+customer1.getSurname().toUpperCase());
            controlcustomername.setStyle("-fx-background-color:white;");
        }
        catch (Exception e){
            controlcustomername.setStyle("-fx-background-color:none;");
            controlcustomername.setText("");
        }

    }
    public void DeleteCustomer(String id){
        MysqlCustomer customer=new MysqlCustomer();
        customer.Delete(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText("Sistem Mesajı");
        alert.setContentText(("Müşteri silindi."));
        alert.showAndWait();
        tcustomer.setItems(getAll());
    }
    public Customer getById(String id){
        MysqlCustomer customer=new MysqlCustomer();
        return customer.getByID(id);
    }
    public void SaveCustomer(Customer cust){
        MysqlCustomer customer=new MysqlCustomer();
        int count= customer.Update(cust);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(count>0){
            alert.setTitle("Başarılı");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText((cust.getName()+" adlı müşteri güncellendi."));
            alert.showAndWait();
            tcustomer.setItems(getAll());
        }
        else{
            alert.setTitle("Hata");
            alert.setHeaderText("Sistem Mesajı");
            alert.setContentText(("Hata ile karşılaşıldı."));
            alert.showAndWait();
        }
    }
}
