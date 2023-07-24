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
public class MenuPetugasEditWaliKelasController implements Initializable {

    @FXML
    private TableView<ModelTable3> TableDB;
    @FXML
    private TableColumn<ModelTable3, String> col_id;
    @FXML
    private TableColumn<ModelTable3, String> col_nama;
    @FXML
    private TableColumn<ModelTable3, String> col_nip;
    @FXML
    private Button btn_kembali;
    @FXML
    private Button hapus;
    @FXML
    private TableColumn<ModelTable3, String> col_kelas;

    ObservableList<ModelTable3> oblist = FXCollections.observableArrayList();
    
    
    PreparedStatement preparedStatement;
    ConnectionUtil conn;
    Stage stage = new Stage();
    @FXML
    private Button segarrrrr;
    @FXML
    private TextField cari;
    @FXML
    private Button carigaes;
    @FXML
    private Label hahha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String query1 = "SELECT * FROM walikelas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable3(rs.getString("id"), rs.getString("nip"), rs.getString("nama"), rs.getString("kelas")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPetugasEditWaliKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       col_id.setCellFactory(TextFieldTableCell.forTableColumn());
       col_nama.setCellFactory(TextFieldTableCell.forTableColumn());
       col_kelas.setCellFactory(TextFieldTableCell.forTableColumn());
    }    

    public void setText (String text){
        hahha.setText(text);
        hahha.setOpacity(0);
    }
    @FXML
    private void gantiID(TableColumn.CellEditEvent event) {
        ModelTable3 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        if (event.getNewValue().toString().length() ==3){
            try {
            String query = "UPDATE walikelas SET id =? WHERE id=?";
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
                gagal.setContentText("ID diharuskan 3");
                gagal.showAndWait();
        }
    }
    }

    @FXML
    private void gantiNama(TableColumn.CellEditEvent event) {
        ModelTable3 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        try {
            String query = "UPDATE walikelas SET nama =? WHERE nama=? && id =?";
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
    ModelTable3 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        
        
        String query = "DELETE FROM walikelas WHERE id=?";
        preparedStatement = conn.connection().prepareStatement(query);
        preparedStatement.setString(1, modelTableSelected.getId());
        preparedStatement.executeUpdate();
        TableDB.getItems().clear();
        load();
    }

    @FXML
    private void gantiKelas(TableColumn.CellEditEvent event) {
        ModelTable3 modelTableSelected = TableDB.getSelectionModel().getSelectedItem();
        modelTableSelected.setId(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Simpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk simpan, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        try {
            String query = "UPDATE walikelas SET kelas =? WHERE kelas=? && id =?";
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
    }

    @FXML
    private void refrezh(ActionEvent event) {
        String query1 = "SELECT * FROM walikelas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable3(rs.getString("id"), rs.getString("nip"), rs.getString("nama"), rs.getString("kelas")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPetugasEditWaliKelasController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
       col_kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
       
       TableDB.setItems(oblist);
       TableDB.setEditable(true);
       col_id.setCellFactory(TextFieldTableCell.forTableColumn());
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
    
    private void load(){
        String query1 = "SELECT * FROM petugas";
        try {
            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next()){
        oblist.add(new ModelTable3(rs.getString("id"), rs.getString("nip"), rs.getString("nama"), rs.getString("kelas")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
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
        oblist.add(new ModelTable3(rs.getString("id"), rs.getString("nip"), rs.getString("nama"), rs.getString("kelas")));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBuatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
       col_nip.setCellValueFactory(new PropertyValueFactory<>("nip"));
    }
}
