/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author LABKOM-RPL29
 */
public class Login implements Initializable {

    @FXML
    private TextField fld_id;
    @FXML
    private PasswordField fld_password;
    @FXML
    private Button btn_login;

    
    Stage stage = new Stage();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSetP = null;
    ResultSet resultSetA = null;
    ResultSet resultSetW = null;
    
    public Login()
    {
        con = ConnectionUtil.connection();
    }

    @FXML
    private void login(ActionEvent event) {
        FXMLLoader LoaderP = new FXMLLoader();
        LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml"));
        FXMLLoader LoaderA = new FXMLLoader();
        LoaderA.setLocation(getClass().getResource("/spp/paneMenuUtamaAdmin.fxml"));
        FXMLLoader LoaderW = new FXMLLoader();
        LoaderW.setLocation(getClass().getResource("/spp/paneMenuWaliKelas.fxml"));
        
        String id    = fld_id.getText().toString();
       String pass  = fld_password.getText().toString();
       
       
       String sql1 = "SELECT * FROM petugas WHERE id =? and password =?";
       String sql2 = "SELECT * FROM admin WHERE id =? and password =?";
       String sql3 = "SELECT * FROM walikelas WHERE id =? and password =?";
       try{
            LoaderP.load();
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetP = preparedStatement.executeQuery();
            
            preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetA = preparedStatement.executeQuery();
            LoaderW.load();
            
            preparedStatement = con.prepareStatement(sql3);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetW = preparedStatement.executeQuery();
            
            if(resultSetP.next())
            {
                Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                MenuUtamaPetugasController mupc = LoaderP.getController();
                mupc.setText(fld_id.getText());
                
                stage.close();
                Parent p = LoaderP.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show(); 
            }
            else if(resultSetA.next()) {
                Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                Parent p = LoaderA.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

               }
            else if(resultSetW.next()){Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                MenuWaliKelasController mwkc = LoaderW.getController();
                mwkc.setText(fld_id.getText());
                stage.close();
                Parent p = LoaderW.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }
            
            else{
                 Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setHeaderText(null);
                gagal.setContentText("ID atau Kata Sandi salah");
                gagal.showAndWait();
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void enter(KeyEvent event) {
      if (event.getCode().equals(KeyCode.ENTER)){
      FXMLLoader LoaderP = new FXMLLoader();
        LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml"));
        FXMLLoader LoaderA = new FXMLLoader();
        LoaderA.setLocation(getClass().getResource("/spp/paneMenuUtamaAdmin.fxml"));
        FXMLLoader LoaderW = new FXMLLoader();
        LoaderW.setLocation(getClass().getResource("/spp/paneMenuUtamaWaKel.fxml"));
        
        String id    = fld_id.getText().toString();
       String pass  = fld_password.getText().toString();
       
       
       String sql1 = "SELECT * FROM petugas WHERE id =? and password =?";
       String sql2 = "SELECT * FROM admin WHERE id =? and password =?";
       String sql3 = "SELECT * FROM walikelas WHERE id =? and password =?";
       try{
            LoaderP.load();
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetP = preparedStatement.executeQuery();
            LoaderA.load();
            preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetA = preparedStatement.executeQuery();
            LoaderW.load();
            preparedStatement = con.prepareStatement(sql3);
            preparedStatement.setString(1,id);///set string berfungsi nyaho lah yang angka 1 nya buat index dari field database nya kalau yang 
            preparedStatement.setString(2, pass);//email sama pass dari String yang atas 
            resultSetW = preparedStatement.executeQuery();
            
            if(resultSetP.next())
            {
                Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                MenuUtamaPetugasController mupc = LoaderP.getController();
                mupc.setText(fld_id.getText());
                stage.close();
                Parent p = LoaderP.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show(); 
                
            }
            else if(resultSetA.next()) {
                Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                Parent p = LoaderA.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

               }
            else if(resultSetW.next()){Node node = (Node)event.getSource();
                stage  = (Stage) node.getScene().getWindow();//fungdinys untuk close halaman saat pindah halaman
                stage.close();
                Parent p = LoaderW.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                MenuUtamaWaKelController muwkc = LoaderW.getController();
                muwkc.setText(fld_id.getText());
            }
            
            else{
                 Alert gagal = new Alert(Alert.AlertType.ERROR);
                gagal.setHeaderText(null);
                gagal.setContentText("ID atau Kata Sandi salah");
                gagal.showAndWait();
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
      }
        
    }

    @FXML
    private void reales(KeyEvent event)
    {
        //if(!fld_id.getText().matches("[0-9]*")){
          // fld_id.setText("");
        }

    @FXML
    private void tuap(ActionEvent event) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Keluar");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk keluar, Cancel untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                 
        System.exit(0);
    }
    }
}


   
    

