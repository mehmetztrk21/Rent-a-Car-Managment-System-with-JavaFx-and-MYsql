package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class YillikController implements Initializable {

    @FXML
    private TableView<Urunler> table;

    @FXML
    private TableColumn<Urunler, String> colIP;
    @FXML
    private TableColumn<Urunler, String> colMarka;
    @FXML
    private TableColumn<Urunler, String> colAd;
    @FXML
    private TableColumn<Urunler, Integer> colSuan;
    @FXML
    private TableColumn<Urunler, Integer> colGelecek;

    @FXML
    private TextField araTxt;

    //TODO tablea veritabanından bilgileri gir, gelecek stok sütununa excelde 2021 tahmin yazan gelcek şuanki stoğa da 2021Güncel olan yazılacak
    //TODO 2022 gelecek planlaması yaparım daha sonra onu koyabiliriz
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
        colSuan.setCellValueFactory(new PropertyValueFactory<>("stokSuan"));
        colGelecek.setCellValueFactory(new PropertyValueFactory<>("stokGelecek"));
        table.setItems(getAll());

        FilteredList<Urunler> filteredList = new FilteredList<>(urunler,b ->true);

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

    }
    public ObservableList<Urunler> getAll(){
        MysqlUrunler urun=new MysqlUrunler();

        LinkedList<Urunler> get_urun=new LinkedList<Urunler>();
        get_urun=urun.GetAll();
        for (int i=0;i<get_urun.size();i++){
            urunler.add(get_urun.get(i));
        }
        return urunler;
    }
}