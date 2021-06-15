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
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TedarikController implements Initializable {

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
    private TextField araTxt;


    @FXML
    void SatinAl(ActionEvent event) {  //burası değişti.
        if((SatinAdSoyad.getText().equals("") || SatinTelf.getText().equals("")) || SatinAdres.equals("")){//TODO 2 tanesi girnce giriyor bunu düzelitcem
            //todo butonlardan biri mutlaka seçili olmalı şartı eklenecek
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Eksik Giriş!");
            alert.setContentText("Lütfen bilgileri eksiksiz girin.");
            alert.showAndWait();
        }else
            {
            System.out.println(SatinAdSoyad.getText());
            CreateCargo(SatinAdSoyad.getText().split(" ")[0],SatinAdSoyad.getText().split(" ")[1],SatinTelf.getText(),SatinAdres.getText(),topFiyat.getText().split(" ")[0]);  //değişti.
            MysqlCargo cargo=new MysqlCargo();
            CreateOrdes(cargo.getLastCargo(),SatinUrunler.getItems());
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

    ObservableList<Urunler> urunler = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        colMarka.setCellValueFactory(new PropertyValueFactory<>("marka"));
        colIP.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        table.setItems(getAll2());

        FilteredList<Urunler> filteredList = new FilteredList<>(urunler, b ->true);

        araTxt.textProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(employee ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(employee.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(employee.getMarka().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else if(employee.getID().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else//TODO burada ben int değerler için arama yapabilirim
                    return false;
            });
        });

        SortedList<Urunler> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

        table.setOnMouseClicked(e ->{
            if(e.getClickCount()==2){
                for(Urunler urun: table.getSelectionModel().getSelectedItems()){
                    SatinUrunler.getItems().add(urun.getMarka()+" - "+urun.getAd());
                    DecimalFormat df = new DecimalFormat("###.##");      //burası değişti.
                    topFiyat.setText(String.valueOf(df.format(Double.parseDouble(urun.getFiyat().split(" ")[0].replace(",","."))+Double.parseDouble(topFiyat.getText().split(" ")[0]))).replace(",",".")+" TL");
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
    public ObservableList<Urunler> getAll2(){
        MysqlUrunler urun=new MysqlUrunler();
        LinkedList<Urunler> get_urun=new LinkedList<Urunler>();
        get_urun=urun.GetAll2();
        for (int i=0;i<get_urun.size();i++){
            urunler.add(get_urun.get(i));
        }
        return urunler;
    }
    void CreateCargo(String name,String surname,String phone,String adress,String price){
        MysqlCargo cargo=new MysqlCargo();
        Cargo cargo1=new Cargo(name,surname,phone,adress,1,Double.parseDouble(price));

        cargo.Create2(cargo1);
    }
    void CreateOrdes(int id,ObservableList<String> orders){

        for (int i=0;i<orders.size();i++){

            MysqlOrders orders1=new MysqlOrders();
            orders1.Create(id,orders.get(i));
        }
    }
}
