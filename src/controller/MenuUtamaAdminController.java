/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import spp.FungsiLaporanPinjaman;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class MenuUtamaAdminController implements Initializable {

    @FXML
    private Button buat;
    @FXML
    private Button edit;
    @FXML
    private Button keluar;

    
     Stage stage = new Stage();
    @FXML
    private Text namasibangsat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buat(ActionEvent event) throws IOException {
        FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuAdminBuat.fxml")); 
        
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderP.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show(); 
    }

    @FXML
    private void edit(ActionEvent event) throws IOException {
        FXMLLoader LoaderA = new FXMLLoader();
                LoaderA.setLocation(getClass().getResource("/spp/paneMenuAdminEdit.fxml")); 
        
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
    private void keluar(ActionEvent event) {
        try {
        FXMLLoader LoaderL = new FXMLLoader();
                LoaderL.setLocation(getClass().getResource("/spp/paneLogin.fxml"));
                
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Keluar");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk keluar, Cancel untuk batal");
        Optional result = alert.showAndWait();
       
            LoaderL.load();
        
        if (result.get() == ButtonType.OK){
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderL.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show(); 
        }
        } catch (IOException ex) {
            Logger.getLogger(MenuUtamaPetugasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void report(ActionEvent event) {
        new FungsiLaporanPinjaman("/spp/reportgan.jasper");
        System.out.println("Laporan Berhasil Ditampilkan");
    }
    
}
