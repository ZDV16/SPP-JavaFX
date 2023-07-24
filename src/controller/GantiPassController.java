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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author LABKOM-RPL29
 */
public class GantiPassController implements Initializable {

    private TextField nip;
    @FXML
    private TextField paslam;
    @FXML
    private TextField pasbar;
    @FXML
    private Button kembali;
    
    ConnectionUtil conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Stage stage = new Stage();
    @FXML
    private Label hahha;
    @FXML
    private Label aikamu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void setText(String text){
        hahha.setText(text);
    }

    @FXML
    private void simpan(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah anda akan menyimpan data");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk menyimpan data, Cancel untuk batal.");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (aikamu.getText()==null) {
             try {
                String query = "UPDATE walikelas SET password =? WHERE password =? && id =?";
                preparedStatement = conn.connection().prepareStatement(query);
                preparedStatement.setString(1, pasbar.getText());
                preparedStatement.setString(2, paslam.getText());
                preparedStatement.setString(3, hahha.getText());
                preparedStatement.execute();
                Alert berhasil = new Alert(Alert.AlertType.INFORMATION);
                berhasil.setTitle("Informasi Database");
                berhasil.setHeaderText(null);
                berhasil.setContentText("Data Telah Berhasil Disimpan");   
                berhasil.showAndWait();
                preparedStatement.close();
             
            } catch (SQLException e) {
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
                gagal.setContentText("Kata sandi harus 6 karakter atau lebih");
                gagal.showAndWait();
            }
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
    FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaWaKel.fxml")); 
        
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage.initStyle(StageStyle.UNDECORATED);
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderP.getRoot();
                stage.setScene(new Scene(p));
                stage.show(); 
                MenuUtamaWaKelController muwkc = LoaderP.getController();
                muwkc.setText(hahha.getText());
    }

    @FXML
    private void hehpass(MouseEvent event) {
    if (paslam.getLength()<6) {
            aikamu.setText("Password Minimal 6 Karakter");
        }
        else{
            aikamu.setText(null);
        }
    }
}
