/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author LABKOM-RPL29
 */
public class MenuAdminBuatController implements Initializable {
    ConnectionUtil conn;
    Stage stage = new Stage();
    @FXML
    private Button simpan;
    @FXML
    private TextField fld_nis;
    @FXML
    private TextField fld_nama;
    @FXML
    private TextField fld_id;
    @FXML
    private TextField fld_pass;
    @FXML
    private Label nip;
    @FXML
    private Label pass;
    @FXML
    private Label id;
    @FXML
    private Label nama;
    ResultSet resultSet;
    String gud = "ID dapat digunakan";
    String gud1 = "NIP dapat digunakan";
    @FXML
    private Label id1;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
    PreparedStatement preparedStatement = null;


    @FXML
    private void simpan(ActionEvent event) {
        conn = new ConnectionUtil();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah anda akan menyimpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk menyimpan data, Cancel untuk batal.");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (id.getText()==gud && nip.getText()==gud1 && pass.getText()==null && nama.getText()==null) {
            try {
                String query = "INSERT INTO petugas VALUES (?,?,?,?)";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, fld_id.getText());
                preparedStatement.setString(2, fld_pass.getText());
                preparedStatement.setString(3, fld_nama.getText());
                preparedStatement.setString(4, fld_nis.getText());
                preparedStatement.execute();
                Alert berhasil = new Alert(Alert.AlertType.INFORMATION);
                berhasil.setTitle("Informasi Database");
                berhasil.setHeaderText(null);
                berhasil.setContentText("Data "+fld_nama.getText()+" Telah Berhasil Disimpan");   
                berhasil.showAndWait();
                preparedStatement.close();
             
            } catch (SQLException e) {
                Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data "+fld_nama.getText()+" Tidak Dapat Disimpan\n\nSilahkan Di Ulang.\n"
                        + "Error = "+e);
                gagal.showAndWait();
            }
            }
            else{
                Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setTitle("Informasi Database");
                gagal.setHeaderText(null);
                gagal.setContentText("Data "+fld_nama.getText()+" Tidak Dapat Disimpan ");
                gagal.showAndWait();
            }
        }
        
        else {
            alert.close();
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
    private void load(ActionEvent event) {
    
    }

    @FXML
    private void hehnip(MouseEvent event) throws SQLException {
    if (fld_nis.getLength() !=18) {
        nip.setText("NIP harus 18");
        }
        else{
            String sql1 = "SELECT * FROM petugas WHERE nip =? ";
            preparedStatement = conn.connection().prepareStatement(sql1);
            preparedStatement.setString(1,fld_nis.getText());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                nip.setText("Maaf NIP Telah Dipakai");
            }
            else{
                nip.setText(gud1);
            }
        }
    }

    @FXML
    private void hehnama(MouseEvent event) {
        if (fld_nama.getLength()<1) {
            nama.setText("Nama Tidak Boleh Kosong");
        }
        else{
            nama.setText(null);
        }
    }

    @FXML
    private void hehid(MouseEvent event) throws SQLException {
    
        if (fld_id.getLength() !=5) {
        id.setText("Nama pengguna minimal 5 karakter");
        
        }
        else{
            String sql1 = "SELECT * FROM petugas WHERE id =? ";
            preparedStatement = conn.connection().prepareStatement(sql1);
            preparedStatement.setString(1,fld_id.getText());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                id.setText("Maaf ID Telah Dipakai");
            }
            else{
                id.setText(gud);
            }
        }
    }

    @FXML
    private void hehpass(MouseEvent event) {
    if (fld_pass.getLength()<6) {
            pass.setText("Password Minimal 6 Karakter");
        }
        else{
            pass.setText(null);
        }
    }

    @FXML
    private void release(KeyEvent event) {
        if(!fld_nis.getText().matches("[0-9]*")){
           fld_nis.setText("");
    }   
}
}