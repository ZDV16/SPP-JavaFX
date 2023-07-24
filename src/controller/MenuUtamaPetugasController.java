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
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class MenuUtamaPetugasController implements Initializable {

    @FXML
    private Button spp;
    @FXML
    private Button tambahsiswa;
    @FXML
    private Button logout;
    
    Stage stage = new Stage();
    @FXML
    private Button walikelas;
    @FXML
    private Button editwakel;
    @FXML
    private Button editsis;
    @FXML
    private Label hahha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setText(String text) {
        hahha.setText(text);
       hahha.setOpacity(0);
    }
    @FXML
    private void spp(ActionEvent event) throws IOException {
                FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuPetugasBayar.fxml")); 
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage.initStyle(StageStyle.UNDECORATED);
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                MenuPetugasController mpc = LoaderP.getController();
                mpc.setText(hahha.getText());
               
                stage.close();
                Parent p = LoaderP.getRoot();
                stage.setScene(new Scene(p));
                
                stage.show(); 
    }

    @FXML
    private void tambah(ActionEvent event) throws IOException {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuPetugasSiswa.fxml")); 
        
                Loader.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuPetugasSiswaController m = Loader.getController();
                m.setText(hahha.getText());
               
    }

    @FXML
    private void logout(ActionEvent event) {
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
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneLogin.fxml")); 
                
                
                Node node = (Node)event.getSource();
                stage.initStyle(StageStyle.UNDECORATED);
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                
                Parent p = LoaderL.getRoot();
                stage.setScene(new Scene(p));
                
                stage.show(); 
        }
        } catch (IOException ex) {
            Logger.getLogger(MenuUtamaPetugasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void walikelas(ActionEvent event) throws IOException {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuPetugasWaKel.fxml")); 
        
                Loader.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuPetugasWaKelController mwkc = Loader.getController();
                mwkc.setText(hahha.getText());
    }

    @FXML
    private void editws(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuPetugasEditWaliKelas.fxml")); 
        
                Loader.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuPetugasEditWaliKelasController mpewkc = Loader.getController();
                mpewkc.setText(hahha.getText());
    }

    @FXML
    private void edits(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/spp/paneMenuPetugasEditSiswa.fxml")); 
        
                Loader.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
               
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuPetugasEditSiswaController mpesc = Loader.getController();
                mpesc.setText(hahha.getText());
    }

    @FXML
    private void ganpass(ActionEvent event) throws IOException {
                FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneGantiPass1.fxml")); 
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage.initStyle(StageStyle.UNDECORATED);
                stage = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                GantiPass1Controller ganpas =LoaderP.getController();
                ganpas.setText(hahha.getText());
                stage.close();
                Parent p = LoaderP.getRoot();
                stage.setScene(new Scene(p));
                
                stage.show();  
    }

}
    
    

