/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author BSPNKBB
 */
public class StrukController implements Initializable {

    @FXML
    private Label nis;
    @FXML
    private Label nama;
    @FXML
    private Label kelas;
    @FXML
    private Label bulan;
    @FXML
    private Label tanggal;
    @FXML
    private Label nagas;
    @FXML
    private Label id;

    
    PreparedStatement preparedStatement;
    ConnectionUtil conn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         String query1 =  "select siswa.nis,siswa.nama,siswa.kelas,bulan,tanggal,petugas.nama,transaksi.id from transaksi inner join siswa on transaksi.nis=siswa.nis inner join petugas on transaksi.id_petugas=petugas.id ORDER by transaksi.id";
        try {

            preparedStatement = conn.connection().prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            rs.last();
                nis.setText(rs.getString("siswa.nis"));
                nama.setText(rs.getString("siswa.nama"));
                kelas.setText(rs.getString("siswa.kelas"));
                bulan.setText(rs.getString("transaksi.bulan"));
                tanggal.setText(rs.getString("transaksi.tanggal"));
                nagas.setText(rs.getString("petugas.nama"));
                id.setText(rs.getString("transaksi.id"));
                    
        } catch (SQLException ex) {
            Logger.getLogger(StrukController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }    
    
}
