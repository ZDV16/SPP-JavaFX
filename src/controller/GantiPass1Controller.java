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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class GantiPass1Controller implements Initializable {

    @FXML
    private Label hahha;
    @FXML
    private PasswordField paslam;
    @FXML
    private PasswordField pasbar;

    Stage stage = new Stage();
    ConnectionUtil conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @FXML
    private Label aikamu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setText (String text){
        hahha.setText(text);
        
    }
    
    @FXML
    private void kembali(ActionEvent event) throws IOException {
    FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml")); 
        
                Loader.load();
                 stage.initStyle(StageStyle.UNDECORATED);
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                
                stage.setScene(new Scene(p));
                stage.show();
                MenuUtamaPetugasController mupc = Loader.getController();
                mupc.setText(hahha.getText());
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
                String query = "UPDATE petugas SET password =? WHERE password =? && id =?";
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
                gagal.setContentText("Kata Sandi harus 6 karakter atau lebih");
                gagal.showAndWait();
            }
    
    }
    
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
