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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class MenuAdminEditController implements Initializable {

    @FXML
    private TableView<ModelTable> TableDB;
    @FXML
    private TableColumn<ModelTable, String> col_id;
    @FXML
    private TableColumn<ModelTable, String> col_nama;
    private TableColumn<ModelTable, String> col_pass;
    @FXML
    private TableColumn<ModelTable, String> col_nip;
    @FXML
    private Button btn_kembali;
    
    
    
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    ConnectionUtil conn;
    Stage stage = new Stage();
    PreparedStatement preparedStatement = null;
    private Label test;
    @FXML
    private Button hapus;
    @FXML
    private TextField cari;
    @FXML
    private Button carigaes;
    @FXML
    private Button segarrrrr;
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        String query1 = "SELECT * FROM petugas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable(rs.getString("id"), rs.getString("nama"), rs.getString("password"), rs.getString("nip")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       col_id.setCellFactory(TextFieldTableCell.forTableColumn());
       col_nama.setCellFactory(TextFieldTableCell.forTableColumn());
       col_nip.setCellFactory(TextFieldTableCell.forTableColumn());
       
       if(!col_nip.getText().matches("[0-9]*")){
           col_nip.setText("");
       }
    }    

    


    @FXML
    private void kembali(ActionEvent event) throws IOException {
        FXMLLoader LoaderA = new FXMLLoader();
                LoaderA.setLocation(getClass().getResource("/spp/paneMenuUtamaAdmin.fxml")); 
        
                LoaderA.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderA.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show(); 
    }


    @FXML
    private void gantiID(CellEditEvent event) {
        ModelTable modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (event.getNewValue().toString().length() >5) {
        try {
            String query = "UPDATE petugas SET id =? WHERE id=? ";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, (String) event.getNewValue());
                preparedStatement.setString(2, (String) event.getOldValue());
                preparedStatement.execute();
        } catch (Exception e) {
            Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data Tidak Dapat Disimpan\n\nSilahkan Di Ulang.\n"
                        + "Error = "+e);
                gagal.showAndWait();
        }
            }
            else{
                 Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("ID harus 5 karakter!");
                gagal.showAndWait();
                TableDB.getItems().clear();
                load();
            }
    }
    }
    
    @FXML
    private void gantiNama(CellEditEvent event) throws SQLException {
        ModelTable modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (event.getNewValue().toString().length()>1) {  
        try {
            String query = "UPDATE petugas SET nama =? WHERE nama=? && id =?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, (String) event.getNewValue());
                preparedStatement.setString(2, (String) event.getOldValue());
                preparedStatement.setString(3, modelTableSelected.getId());
                preparedStatement.execute();
        } catch (Exception e) {
            Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data Tidak Dapat Disimpan\n\nSilahkan Di Ulang.\n"
                        + "Error = "+e);
                gagal.showAndWait();
        }
            }
            else{
                Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data tidak boleh kosong!");
                gagal.showAndWait();
                TableDB.getItems().clear();
                load();
            }
    }
    }

    
    
   
    @FXML
    private void gantiNIP(CellEditEvent event) throws SQLException{
         ModelTable modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (event.getNewValue().toString().length() ==18){
        try {
            String query = "UPDATE petugas SET nip =? WHERE nip=?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, (String) event.getNewValue());
                preparedStatement.setString(2, (String) event.getOldValue());
                preparedStatement.execute();
        } catch (Exception e) {
            Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data Tidak Dapat Disimpan\n\nSilahkan Di Ulang.\n"
                        + "Error = "+e);
                gagal.showAndWait();
        }
            }
            else{
                Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("NIP diharuskan 18");
                gagal.showAndWait();
                TableDB.getItems().clear();
                load();
            }
    }
    }

    @FXML
    private void hapusgan(ActionEvent event) throws SQLException {
    ModelTable modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        
        
        String query = "DELETE FROM petugas WHERE id=?";
        preparedStatement = conn.connection().prepareStatement(query);
        preparedStatement.setString(1, modelTableSelected.getId());
        preparedStatement.executeUpdate();
        TableDB.getItems().clear();
        load();
    }
    
    private void load(){
        String query1 = "SELECT * FROM petugas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable(rs.getString("id"), rs.getString("nama"), rs.getString("password"), rs.getString("nip")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
    }


    @FXML
    private void refrezh(ActionEvent event) throws SQLException {
        TableDB.getItems().clear();
        segarkansadja();
    }

    @FXML
    private void refrezh(KeyEvent event) throws SQLException {
    if (event.getCode()==KeyCode.F5){
        segarkansadja();
    }
    }
    
    public void segarkansadja() throws SQLException{
        String query1 = "SELECT * FROM petugas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable(rs.getString("id"), rs.getString("nama"), rs.getString("password"), rs.getString("nip")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       col_id.setCellFactory(TextFieldTableCell.forTableColumn());
       col_nama.setCellFactory(TextFieldTableCell.forTableColumn());
       
      
    }

    @FXML
    private void careeeeee(ActionEvent event) throws SQLException {
        carikuy();
    }
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode()==KeyCode.ENTER){
            carikuy();
    }
    }
    
    public void carikuy(){
        TableDB.getItems().clear();
        try { 
        String q = "SELECT * FROM petugas WHERE id=? OR nama=? OR nip=?";
        preparedStatement = conn.connection().prepareStatement(q);
        preparedStatement.setString(1, cari.getText());
        preparedStatement.setString(2, cari.getText());
        preparedStatement.setString(3, cari.getText());
         ResultSet rs = preparedStatement.executeQuery();
         while(rs.next()){
        oblist.add(new ModelTable(rs.getString("id"), rs.getString("nama"), rs.getString("password"), rs.getString("nip")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
    }
    }

     



   