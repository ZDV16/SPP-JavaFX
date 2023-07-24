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
public class MenuPetugasSiswaController implements Initializable {

    @FXML
    private TextField fld_nis;
    @FXML
    private TextField fld_nama;
    @FXML
    private Button simpan;
    @FXML
    private Button kembali;
    
  

    ObservableList<String> jurusan =
            FXCollections.observableArrayList(
            "X Animasi A",
            "X Animasi B",
            "X Animasi C",
            "X RPL A",
            "X RPL B",
            "X Multimedia C"
            );
    
    Stage stage = new Stage();
    PreparedStatement preparedStatement = null;
    ConnectionUtil conn;
    @FXML
    private ComboBox kelass;
    @FXML
    private Label lbl_nis;
    @FXML
    private Label lbl_nama;
    ResultSet resultSet;
    String gud1 = "NIS dapat digunakan";
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
            if (lbl_nis.getText()==gud1 && lbl_nama.getText()==null && kelass!=null) {
                
            
            try {
                String query = "INSERT INTO siswa VALUES (?,?,?)";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, fld_nama.getText());
                preparedStatement.setString(2, kelass.getValue().toString());
                preparedStatement.setString(3, fld_nis.getText());
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
                gagal.setContentText("Data "+fld_nama.getText()+" Tidak Dapat Disimpan Karena Terdapat Data Yang Kosong\n\nSilahkan Di Ulang.\n");
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
    kelass.setItems(jurusan);
    }

    @FXML
    private void hehnis(MouseEvent event) throws SQLException {
    if (fld_nis.getLength() !=10) {
        lbl_nis.setText("NIS harus 10");
        }
        else{
            String sql1 = "SELECT * FROM siswa WHERE nis =? ";
            preparedStatement = conn.connection().prepareStatement(sql1);
            preparedStatement.setString(1,fld_nis.getText());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                lbl_nis.setText("Maaf NIP Telah Dipakai");
            }
            else{
                lbl_nis.setText(gud1);
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
    private void release(KeyEvent event) {
        if(!fld_nis.getText().matches("[0-9]*")){
           fld_nis.setText("");
    }
}
}