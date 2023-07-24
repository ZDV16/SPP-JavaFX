/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;





public class MenuWaliKelasController implements Initializable {
    ObservableList<ModelTable1> oblist = FXCollections.observableArrayList();
    ObservableList<ModelTable2> oblist2 = FXCollections.observableArrayList();
    ConnectionUtil conn;
    Stage stage = new Stage();
    PreparedStatement preparedStatement = null;
    @FXML
    private TableView<ModelTable1> TableDB;
    @FXML
    private TableColumn<ModelTable1, String> col_nama;
    @FXML
    private TableColumn<ModelTable1, String> col_nis;
    @FXML
    private Button detail;
    @FXML
    private Button Keluar;
    String ladida;
    String ket;
    @FXML
    private TableColumn<ModelTable1, String> keterangan;
    @FXML
    private TableView<ModelTable2> TableDB1;
    @FXML
    private TableColumn<ModelTable2, String> col_idp;
    @FXML
    private TableColumn<ModelTable2, String> col_bp;
    @FXML
    private TableColumn<ModelTable2, String> col_ta;
    @FXML
    private TableColumn<ModelTable2, String> col_tang;
    @FXML
    private TextField fld_cari;
    @FXML
    private Button btn_cari;
    @FXML
    private Label hahha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            String querygu =  "select * from walikelas";
            preparedStatement = conn.connection().prepareStatement(querygu);
            ResultSet rsg = preparedStatement.executeQuery();
            rsg.last();
            String kelas = rsg.getString("kelas");
            String query1 =  "select * from siswa inner join transaksi on siswa.nis=transaksi.nis where kelas='"+kelas+"' group by siswa.nis ";
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Desember")) {
                    ket=("Lunas");
                }
                else{
                    ket=("Belum Lunas");
                }
            
        oblist.add(new ModelTable1( rs.getString("nama"),rs.getString("nis"),ket));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
       keterangan.setCellValueFactory(new PropertyValueFactory<>("ket"));
       TableDB.setItems(oblist);
    }    
    
    public void setText(String text){
        hahha.setText(text);
        hahha.setOpacity(0);
    }

    @FXML
    private void detail(ActionEvent event) throws IOException {
    ModelTable1 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
      modelTableSelected.getNis();
        try {
            String query = "SELECT * FROM transaksi where nis=?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, modelTableSelected.getNis());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next())
                {
               oblist2.add(new ModelTable2(rs.getString("bulan"),rs.getString("tahunajaran"),rs.getString("tanggal"),rs.getString("id")));   
            }    
       
                col_bp.setCellValueFactory(new PropertyValueFactory<>("bulan") );
                col_idp.setCellValueFactory(new PropertyValueFactory<>("tanggal") );
                col_ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
                col_tang.setCellValueFactory(new PropertyValueFactory<>("id") );
                TableDB1.setItems(oblist2);
                
                reset();
         } catch (Exception e) {
        }
    }
    
    

    @FXML
    private void keluar(ActionEvent event) throws IOException {
          FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuUtamaWaKel.fxml")); 
        
                Loader.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuUtamaWaKelController muwkc = Loader.getController();
                muwkc.setText(hahha.getText());
    }
   
    public void reset(){
    TableDB1.getItems().clear();
     load();
    
    }
    public void load(){
        ModelTable1 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
      modelTableSelected.getNis();
        try {
            String query = "SELECT * FROM transaksi where nis=?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, modelTableSelected.getNis());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next())
                {
               oblist2.add(new ModelTable2(rs.getString("bulan"),rs.getString("tahunajaran"),rs.getString("tanggal"),rs.getString("id")));   
            }    
       
                col_bp.setCellValueFactory(new PropertyValueFactory<>("bulan") );
                col_idp.setCellValueFactory(new PropertyValueFactory<>("tanggal") );
                col_ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
                col_tang.setCellValueFactory(new PropertyValueFactory<>("id") );
                TableDB1.setItems(oblist2);
                
         } catch (Exception e) {
        }
    }

    @FXML
    private void enter(ActionEvent event) {
         try {
            TableDB.getItems().clear();
            
            String querygu =  "select * from walikelas";
            preparedStatement = conn.connection().prepareStatement(querygu);
            ResultSet rsg = preparedStatement.executeQuery();
            rsg.last();
            String kelas = rsg.getString("kelas");
            String query1 =  "select * from siswa inner join transaksi on siswa.nis=transaksi.nis where siswa.nis =? && kelas='"+kelas+"' group by siswa.nis ";
            preparedStatement = conn.connection().prepareStatement(query1);
            preparedStatement.setString(1, fld_cari.getText());
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Januari")) {
                    ket=("Lunas");
                }
                else{
                    ket=("Belum Lunas");
                }
            
        oblist.add(new ModelTable1( rs.getString("nama"),rs.getString("nis"),ket));
                System.out.println(ket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
       keterangan.setCellValueFactory(new PropertyValueFactory<>("ket"));
       TableDB.setItems(oblist);
    }

    @FXML
    private void cari(ActionEvent event) {
        try {
            TableDB.getItems().clear();
            
            String querygu =  "select * from walikelas";
            preparedStatement = conn.connection().prepareStatement(querygu);
            ResultSet rsg = preparedStatement.executeQuery();
            rsg.last();
            String kelas = rsg.getString("kelas");
            String query1 =  "select * from siswa inner join transaksi on siswa.nis=transaksi.nis where siswa.nis =? && kelas='"+kelas+"' group by siswa.nis ";
            preparedStatement = conn.connection().prepareStatement(query1);
            preparedStatement.setString(1, fld_cari.getText());
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Desember")) {
                    ket=("Lunas");
                }
                else{
                    ket=("Belum Lunas");
                }
            
        oblist.add(new ModelTable1( rs.getString("nama"),rs.getString("nis"),ket));
                System.out.println(ket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
       keterangan.setCellValueFactory(new PropertyValueFactory<>("ket"));
       TableDB.setItems(oblist);
    }

    @FXML
    private void ulang(ActionEvent event) {
        segarkan();
    }

    @FXML
    private void press(KeyEvent event) {
        if (event.getCode()==KeyCode.F5) {
           segarkan(); 
        }
    }
    public void segarkan()
    {
        TableDB.getItems().clear();
        TableDB1.getItems().clear();
        try {
            String querygu =  "select * from walikelas";
            preparedStatement = conn.connection().prepareStatement(querygu);
            ResultSet rsg = preparedStatement.executeQuery();
            rsg.last();
            String kelas = rsg.getString("kelas");
            String query1 =  "select * from siswa inner join transaksi on siswa.nis=transaksi.nis where kelas='"+kelas+"' group by siswa.nis ";
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Desember")) {
                    ket=("Lunas");
                }
                else{
                    ket=("Belum Lunas");
                }
            
        oblist.add(new ModelTable1( rs.getString("nama"),rs.getString("nis"),ket));
                System.out.println(ket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
       keterangan.setCellValueFactory(new PropertyValueFactory<>("ket"));
       TableDB.setItems(oblist);
    }

    private void ganpass(ActionEvent event) throws IOException {
       FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneGantiPass.fxml")); 
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                GantiPassController ganpas =LoaderP.getController();
                ganpas.setText(hahha.getText());
                stage.close();
                Parent p = LoaderP.getRoot();
                stage.setScene(new Scene(p));
                stage.show();  
    }
}

