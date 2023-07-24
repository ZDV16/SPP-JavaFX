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
import javafx.scene.control.TableColumn;
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
 * @author LABKOM-RPL29
 */
public class MenuPetugasEditSiswaController implements Initializable {

    @FXML
    private TableView<ModelTable4> TableDB;
    @FXML
    private TableColumn<ModelTable4, String> col_nama;
    @FXML
    private TableColumn<ModelTable4, String> col_kelas;
    @FXML
    private TableColumn<ModelTable4, String> col_nip;
    @FXML
    private Button btn_kembali;
    @FXML
    private Button hapus;
    @FXML
    private Button segarrrrr;
    @FXML
    private TextField cari;
    @FXML
    private Button carigaes;
    
    PreparedStatement preparedStatement;
    ConnectionUtil conn;
    Stage stage = new Stage();
    ObservableList<ModelTable4> oblist = FXCollections.observableArrayList();
    @FXML
    private Label hahha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String query1 = "SELECT * FROM siswa";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable4(rs.getString("nama"), rs.getString("kelas"), rs.getString("nis")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPetugasEditWaliKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nis"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       
       col_nama.setCellFactory(TextFieldTableCell.forTableColumn());
       col_kelas.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
        public void setText(String text){
            hahha.setText(text);
            hahha.setOpacity(0);
        }
    @FXML
    private void gantiNama(TableColumn.CellEditEvent event) {
        ModelTable4 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setNis(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (event.getNewValue().toString().length() >1){
            try {
            String query = "UPDATE siswa SET nama =? WHERE nama=? && nis=?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, (String) event.getNewValue());
                preparedStatement.setString(2, (String) event.getOldValue());
                 preparedStatement.setString(3, modelTableSelected.getNis());
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
                gagal.setContentText("Data Tidak Dapat Disimpan Karena Nama Kosong\n\nSilahkan Di Ulang.\n");
                gagal.showAndWait();
            }
    }
    }

    @FXML
    private void gantiKelas(TableColumn.CellEditEvent event) {
    ModelTable4 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setNis(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        if (event.getNewValue().toString().length() >1){
            try {
            String query = "UPDATE siswa SET kelas =? WHERE kelas=? && nis=?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, (String) event.getNewValue());
                preparedStatement.setString(2, (String) event.getOldValue());
                preparedStatement.setString(3, (String) modelTableSelected.getNis());
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
                gagal.setContentText("Kelas tidak boleh kosong");
                gagal.showAndWait();
        }
    }
    }

    @FXML
    private void kembali(ActionEvent event) throws IOException {
         FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml")); 
                
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage.initStyle(StageStyle.UNDECORATED);
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderP.getRoot();
                stage.setScene(new Scene(p));
                
                stage.show(); 
                MenuUtamaPetugasController mupc = LoaderP.getController();
                mupc.setText(hahha.getText());
    }

    @FXML
    private void hapusgan(ActionEvent event) throws SQLException {
        
    ModelTable4 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        
        
        String query = "DELETE FROM siswa WHERE nis=?";
        preparedStatement = conn.connection().prepareStatement(query);
        preparedStatement.setString(1, modelTableSelected.getNis());
        preparedStatement.executeUpdate();
        TableDB.getItems().clear();
        load();
    }

    @FXML
    private void refrezh(ActionEvent event) {
        String query1 = "SELECT * FROM siswa";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable4(rs.getString("nis"), rs.getString("nama"), rs.getString("kelas")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPetugasEditWaliKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nis"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       
       col_nama.setCellFactory(TextFieldTableCell.forTableColumn());
       col_kelas.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode()==KeyCode.ENTER){
            carikuy();
    }
    }

    @FXML
    private void careeeeee(ActionEvent event) {
   carikuy();
    }
    public void carikuy(){
        TableDB.getItems().clear();
        try { 
        String q = "SELECT * FROM siswa WHERE id=? OR nama=? OR nis=?";
        preparedStatement = conn.connection().prepareStatement(q);
        preparedStatement.setString(1, cari.getText());
        preparedStatement.setString(2, cari.getText());
        preparedStatement.setString(3, cari.getText());
         ResultSet rs = preparedStatement.executeQuery();
         while(rs.next()){
        oblist.add(new ModelTable4(rs.getString("nama"), rs.getString("kelas"), rs.getString("nis")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nis"));
    }
    private void load(){
        String query1 = "SELECT * FROM siswa";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable4(rs.getString("nama"), rs.getString("kelas"), rs.getString("nis")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nis"));
    }
}
