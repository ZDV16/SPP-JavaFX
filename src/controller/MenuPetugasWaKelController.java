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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class MenuPetugasWaKelController implements Initializable {

    @FXML
    private TextField fld_nip;
    @FXML
    private TextField fld_nama;
    @FXML
    private TextField fld_id;
    @FXML
    private TextField fld_pass;
    @FXML
    private Button simpan;
    @FXML
    private ComboBox<String> kelas;

    ResultSet resultSet;
    
    ObservableList<String> jurusan =
            FXCollections.observableArrayList(
            "X Animasi A",
            "X Animasi B",
            "X Animasi C",
            "X RPL A",
            "X RPL B",
            "X Multimedia C"
            );
    
    ConnectionUtil conn;
    Stage stage = new Stage();
    PreparedStatement preparedStatement = null;
    @FXML
    private Label lbl_nip;
    @FXML
    private Label lbl_nama;
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_pass;

    String gud1 ="NIP dapat digunakan";
    String gud ="ID dapat digunakan";
    @FXML
    private Label hahha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    kelas();
    }    

    public void setText(String text){
        hahha.setText(text);
        hahha.setOpacity(0);
    }
    @FXML
    private void simpan(ActionEvent event) {
    conn = new ConnectionUtil();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah anda akan menyimpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk menyimpan data, Cancel untuk batal.");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (lbl_id.getText()==gud && lbl_nama.getText()==null && lbl_nip.getText()==gud1 && lbl_pass.getText()==null) {
                
            }
            try {
                String query = "INSERT INTO walikelas VALUES (?,?,?,?,?)";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, fld_id.getText());
                preparedStatement.setString(2, fld_pass.getText());
                preparedStatement.setString(3, fld_nama.getText());
                preparedStatement.setString(4, kelas.getValue());
                preparedStatement.setString(5, fld_nip.getText());
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
    }

    @FXML
    private void kembali(ActionEvent event) throws IOException {
    FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml")); 
        
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderP.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuUtamaPetugasController mupc = LoaderP.getController();
                mupc.setText(hahha.getText());
    }


    @FXML
    private void kelas() {
    kelas.setItems(jurusan);
    }
    
    @FXML
    private void load(ActionEvent event) {
}

    @FXML
    private void hehnip(MouseEvent event) throws SQLException {
         if (fld_nip.getLength() !=18) {
        lbl_nip.setText("NIP harus 18");
        }
        else{
            String sql1 = "SELECT * FROM walikelas WHERE nip =? ";
            preparedStatement = conn.connection().prepareStatement(sql1);
            preparedStatement.setString(1,fld_nip.getText());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                lbl_nip.setText("Maaf NIP Telah Dipakai");
            }
            else{
                lbl_nip.setText(gud1);
            }
        }
    }

    @FXML
    private void hehnama(MouseEvent event) {
         if (fld_nama.getLength()<1) {
            lbl_nama.setText("Nama Tidak Boleh Kosong");
        }
        else{
            lbl_nama.setText(null);
        }
    }

    @FXML
    private void hehid(MouseEvent event) throws SQLException {
     if (fld_id.getLength() !=3) {
        lbl_id.setText("ID harus 3!");
        }
        else{
            String sql1 = "SELECT * FROM walikelas WHERE id =? ";
            preparedStatement = conn.connection().prepareStatement(sql1);
            preparedStatement.setString(1,fld_id.getText());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                lbl_id.setText("Maaf ID Telah Dipakai");
            }
            else{
                lbl_id.setText(gud);
            }
        }
    }

    @FXML
    private void hehpass(MouseEvent event) {
        if (fld_pass.getLength()<6) {
            lbl_pass.setText("Kata sandi harus 6 karakter atau lebih");
        }
        else{
            lbl_pass.setText(null);
        }
    }

    @FXML
    private void release(KeyEvent event) {
        if(!fld_nip.getText().matches("[0-9]*")){
           fld_nip.setText("");
    }
}
}