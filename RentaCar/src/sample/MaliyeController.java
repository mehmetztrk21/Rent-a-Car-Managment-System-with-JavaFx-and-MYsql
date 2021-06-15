package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MaliyeController implements Initializable {
    public static int xid;
    public static int count=0;

    @FXML
    private ListView<String> SatinUrunler;

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
    private ListView<String> listIptal;//todo burda satın al butonuna basınca ürünler eklenecek ve herhangi eklenen birine basıp iptal et butonuna
    //todo basıldığında o ürün satın alınanlardan çıkarılacak ve 2021 güncel yazan sütunda azaltılacak sayı


    @FXML
    private Button btniptal;

    @FXML
    private TableView<Urunler> table;

    @FXML
    private TableColumn<Urunler, String> colIP;

    @FXML
    private TableColumn<Urunler, String> colMarka;

    @FXML
    private TableColumn<Urunler, String> colAd;

    @FXML
    private TableColumn<Urunler, Float> colFiyat;
    @FXML
    private TableColumn<Urunler, Integer> colStok;

    @FXML
    private TextField araTxt;

    @FXML
    void Kapida(ActionEvent event) {  ///değişti
        if(count==0){
            double fiyat = Double.parseDouble(topFiyat.getText().split(" ")[0]);
            fiyat += 10;
            topFiyat.setText(String.valueOf(fiyat) + " TL");
            count+=1;
        }

    }

    @FXML
    void Kartla(ActionEvent event) {
        if(count==1){
            double fiyat = Double.parseDouble(topFiyat.getText().split(" ")[0])-10;
            topFiyat.setText(String.valueOf(fiyat) + " TL");
            count=0;
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

    @FXML
    void SatinAl(ActionEvent event) {
        MysqlUser user = new MysqlUser();
        MysqlCustomer cust = new MysqlCustomer();
        Customer customer = null;
        customer = cust.getByUsername(user.getUser().getUsername());

        if ((SatinAdSoyad.getText().equals("") || SatinTelf.getText().equals("")) || SatinAdres.equals("")) {//TODO 2 tanesi girnce giriyor bunu düzelitcem
            //todo butonlardan biri mutlaka seçili olmalı şartı eklenecek
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Eksik Giriş!");
            alert.setContentText("Lütfen bilgileri eksiksiz girin.");
            alert.showAndWait();
        } else {
            CreateCargo(SatinAdSoyad.getText().split(" ")[0], SatinAdSoyad.getText().split(" ")[1], SatinTelf.getText(), SatinAdres.getText(), topFiyat.getText().split(" ")[0]);  //değişti.
            if(customer!=null){
                customer.setLast_order(Double.parseDouble(topFiyat.getText().split(" ")[0]));
                customer.setTotal_order(customer.getTotal_order()+Double.parseDouble(topFiyat.getText().split(" ")[0]));
                System.out.println(customer.getLast_order());
                System.out.println(customer.getTotal_order());
                MysqlCustomer customer1=new MysqlCustomer();
                customer1.Update(customer);
            }
            MysqlCargo cargo = new MysqlCargo();  //burayı ekledim.
            CreateOrdes(cargo.getLastCargo(), SatinUrunler.getItems());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Giriş");
            alert.setHeaderText("Başarılı Giriş!");
            alert.setContentText("Bilgileriniz alındı. Ürünleriniz en yakın zamanda adresinize gönderilecektir");
            alert.showAndWait();
            SatinAdSoyad.setText("");
            SatinTelf.setText("");
            SatinAdres.setText("");
            SatinUrunler.getItems().clear();
            topFiyat.setText("0 TL");
            listIptal.getItems().clear();
        }
    }

    @FXML
    void btndurumgoster(ActionEvent event) {
        listIptal.getItems().clear();
        MysqlOrders orders = new MysqlOrders();
        ArrayList<Order> orders1 = null;


        orders1 = orders.GetById(txtIptalip.getText());
        System.out.println(txtIptalip.getText());
        xid = orders1.get(0).getId();
        for (int i = 0; i < orders1.size(); i++) {
            System.out.println(orders1.get(i));
            listIptal.getItems().add(orders1.get(i).getName());
        }
        txtIptalip.setText("");

    }

    ObservableList<Urunler> urunler = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        colMarka.setCellValueFactory(new PropertyValueFactory<>("marka"));
        colIP.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        colStok.setCellValueFactory(new PropertyValueFactory<>("stokSuan"));
        table.setItems(getAll2());



        FilteredList<Urunler> filteredList = new FilteredList<>(urunler, b -> true);
        listIptal.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 1) {
                    btniptal.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            deleteitems(listIptal.getSelectionModel().getSelectedItem());
                            addStock(listIptal.getSelectionModel().getSelectedItem().split("-")[0]);
                            listIptal.getItems().remove(listIptal.getSelectionModel().getSelectedItem());

                        }
                    });
                }

            }
        });


        araTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getMarka().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else//TODO burada ben int değerler için arama yapabilirim
                    return false;
            });
        });

        SortedList<Urunler> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);
        table.setOnMouseClicked(e -> {
            MysqlCargo cargo = new MysqlCargo();
            if (e.getClickCount() == 2) {

                for (Urunler urun : table.getSelectionModel().getSelectedItems()) {
                    SatinUrunler.getItems().add(urun.getMarka() + " - " + urun.getAd());
                    String ip = urun.getID();

                    minusStock(ip);
                    System.out.println(ip);
                    listIptal.getItems().add(urun.getID() + "- " + urun.getAd());  //ekledim
                    DecimalFormat df = new DecimalFormat("###.##");      //burası değişti.
                    topFiyat.setText(String.valueOf(df.format(Double.parseDouble(urun.getFiyat().split(" ")[0].replace(",", ".")) + Double.parseDouble(topFiyat.getText().split(" ")[0]))).replace(",", ".") + " TL");
                }
            }
        });
        SatinUrunler.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    SatinUrunler.getItems().remove(SatinUrunler.getSelectionModel().getSelectedItem());
                }

            }
        });

    }

    public ObservableList<Urunler> getAll2() {
        MysqlUrunler urun = new MysqlUrunler();
        LinkedList<Urunler> get_urun = new LinkedList<Urunler>();
        get_urun = urun.GetAll2();
        for (int i = 0; i < get_urun.size(); i++) {
            int stock;
            try{
                stock=Integer.parseInt(get_urun.get(i).getStokSuan());
            }
            catch (Exception ex){
                stock=0;
            }
            if(stock<0 || get_urun.get(i).getStokSuan().equals(""))
                get_urun.get(i).setStokSuan("0");
            urunler.add(get_urun.get(i));
        }
        return urunler;
    }

    void CreateCargo(String name, String surname, String phone, String adress, String price) {
        MysqlCargo cargo = new MysqlCargo();
        Cargo cargo1 = new Cargo(name, surname, phone, adress, 1, Double.parseDouble(price));

        cargo.Create2(cargo1);
    }

    void CreateOrdes(int id, ObservableList<String> orders) {  //burayı ekledim.

        for (int i = 0; i < orders.size(); i++) {

            MysqlOrders orders1 = new MysqlOrders();
            orders1.Create(id, orders.get(i));
        }
    }

    void deleteitems(String name) {
        MysqlCargo cargo = new MysqlCargo();
        int id = 0;
        if (txtIptalip.getText().equals("")) {
            id = cargo.getLastCargo();
        } else {
            id = Integer.parseInt(txtIptalip.getText());
        }

        MysqlOrders orders = new MysqlOrders();
        orders.Delete(id, name);

    }

    public void addStock(String ip) {
        MysqlUrunler urunler = new MysqlUrunler();
        urunler.addStock(ip);
    }

    public void minusStock(String ip) {
        MysqlUrunler urunler = new MysqlUrunler();
        urunler.minusStock(ip);
    }
}