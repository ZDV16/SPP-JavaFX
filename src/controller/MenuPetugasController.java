/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import static java.lang.System.err;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import spp.FungsiLaporanPinjaman;
import spp.laporanStruk;
import sun.util.calendar.BaseCalendar;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author LABKOM-RPL29
 */
public class MenuPetugasController implements Initializable {

    @FXML
    private TextField fld_nis;
    @FXML
    private TextField fld_nasis;
    @FXML
    private TextField fld_kelas;
    @FXML
    private Button btn_bayar;
    @FXML
    private Label time;
    @FXML
    private ComboBox tahunajaran;
    @FXML
    private CheckBox jan;
    @FXML
    private CheckBox feb;
    @FXML
    private CheckBox mar;
    @FXML
    private CheckBox apr;
    @FXML
    private CheckBox may;
    @FXML
    private CheckBox jun;
    @FXML
    private CheckBox jul;
    @FXML
    private CheckBox aug;
    @FXML
    private CheckBox sep;
    @FXML
    private CheckBox okt;
    @FXML
    private CheckBox nov;
    @FXML
    private CheckBox dec;

    
    ObservableList<String> ta =
            FXCollections.observableArrayList(
            "2018-2019",
            "2019-2020",
            "2020-2021"
            );
    
    ObservableList<String> bulan = FXCollections.observableArrayList();
    java.util.Date currentDate = Calendar.getInstance().getTime();
    java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
    Connection con = ConnectionUtil.connection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Stage stage = new Stage();
    int cg;
    int jum =0;
        
    int tug = 120000;
    
    
    @FXML
    private Button kembali;
    @FXML
    private Button cari;
    @FXML
    private Label message;
    @FXML
    private Label idp;
    @FXML
    private Label hahha;
    @FXML
    private Label tunggak;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       date(); 
       pilihan();
        
        fld_nasis.setEditable(false);
        fld_kelas.setEditable(false);
        String woo = "SELECT id FROM transaksi ORDER BY id DESC LIMIT 1";
        try {
            preparedStatement = con.prepareStatement(woo);
            resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                cg= resultSet.getInt("id");       
                idp.setText(""+cg);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MenuPetugasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void setText(String text){
        hahha.setText(text);
        hahha.setOpacity(0);
    }
    
    public void date() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        time.setText(dateFormat.format(date));
    }
    
    @FXML
    public void pilihan() {
    tahunajaran.setItems(ta);
        String selected = (String) tahunajaran.getValue();
        if (selected =="2018-2019") {
            refcb();
         try {
        String search = "SELECT * FROM transaksi WHERE nis = ? AND tahunajaran = ?";
        preparedStatement = con.prepareStatement(search);
            preparedStatement.setString(1,fld_nis.getText());
            preparedStatement.setString(2, "2018-2019");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Januari")) {
                    jan.setSelected(false);
                    jan.setDisable(true);
                    bulan.remove(jan.getText());
                }
                if (ladida.contains("Februari")) {
                    feb.setSelected(false);
                    feb.setDisable(true);
                    bulan.remove(feb.getText());
                    
                }
                if (ladida.contains("Maret")) {
                    mar.setSelected(false);
                    mar.setDisable(true);
                    bulan.remove(mar.getText());
                    
                }
                if (ladida.contains("April")) {
                    apr.setSelected(false);
                    apr.setDisable(true);
                    bulan.remove(apr.getText());
                    
                }
                if (ladida.contains("Mei")) {
                    may.setSelected(false);
                    may.setDisable(true);
                    bulan.remove(may.getText());
                    
                }
                if (ladida.contains("Juni")) {
                    jun.setSelected(false);
                    jun.setDisable(true);
                    bulan.remove(jun.getText());
                    
                }
                if (ladida.contains("Juli")) {
                    jul.setSelected(false);
                    jul.setDisable(true);
                    bulan.remove(jul.getText());
                    
                }
                if (ladida.contains("Agustus")) {
                    aug.setSelected(false);
                    aug.setDisable(true);
                    bulan.remove(aug.getText());
                    
                }
                if (ladida.contains("September")) {
                    sep.setSelected(false);
                    sep.setDisable(true);
                    bulan.remove(sep.getText());
                    
                }
                if (ladida.contains("Oktober")) {
                    okt.setSelected(false);
                    okt.setDisable(true);
                    bulan.remove(okt.getText());
                    
                }
                if (ladida.contains("November")) {
                    nov.setSelected(false);
                    nov.setDisable(true);
                    bulan.remove(nov.getText());
                    
                }
                if (ladida.contains("Desember")) {
                    dec.setSelected(false);
                    dec.setDisable(true);
                    bulan.remove(dec.getText());
                    
                }
        }
            check();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        }
        if (selected =="2019-2020") {
            refcb();
         try {
        String search = "SELECT * FROM transaksi WHERE nis = ? AND tahunajaran = ?";
        preparedStatement = con.prepareStatement(search);
            preparedStatement.setString(1,fld_nis.getText());
            preparedStatement.setString(2, "2019-2020");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                
            String ladida = rs.getString("bulan");
                if (ladida.contains("Januari")) {
                    jan.setSelected(false);
                    jan.setDisable(true);
                    bulan.remove(jan.getText());
                    
                }
                if (ladida.contains("Februari")) {
                    feb.setSelected(false);
                    feb.setDisable(true);
                    bulan.remove(feb.getText());
                                    }
                if (ladida.contains("Maret")) {
                    mar.setSelected(false);
                    mar.setDisable(true);
                    bulan.remove(mar.getText());
                    
                }
                if (ladida.contains("April")) {
                    apr.setSelected(false);
                    apr.setDisable(true);
                    bulan.remove(apr.getText());
                    
                }
                if (ladida.contains("Mei")) {
                    may.setSelected(false);
                    may.setDisable(true);
                    bulan.remove(may.getText());
                    
                }
                if (ladida.contains("Juni")) {
                    jun.setSelected(false);
                    jun.setDisable(true);
                    bulan.remove(jun.getText());
                    
                }
                if (ladida.contains("Juli")) {
                    jul.setSelected(false);
                    jul.setDisable(true);
                    bulan.remove(jul.getText());
                    
                }
                if (ladida.contains("Agustus")) {
                    aug.setSelected(false);
                    aug.setDisable(true);
                    bulan.remove(aug.getText());
                    
                }
                if (ladida.contains("September")) {
                    sep.setSelected(false);
                    sep.setDisable(true);
                    bulan.remove(sep.getText());
                    
                }
                if (ladida.contains("Oktober")) {
                    okt.setSelected(false);
                    okt.setDisable(true);
                    bulan.remove(okt.getText());
                                    }
                if (ladida.contains("November")) {
                    nov.setSelected(false);
                    nov.setDisable(true);
                    bulan.remove(nov.getText());
                    
                }
                if (ladida.contains("Desember")) {
                    dec.setSelected(false);
                    dec.setDisable(true);
                    bulan.remove(dec.getText());
                    
                }
                
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        }
        if (selected =="2020-2021") {
            refcb();
            
         try {
        String search = "SELECT * FROM transaksi WHERE nis = ? AND tahunajaran = ?";
        preparedStatement = con.prepareStatement(search);
            preparedStatement.setString(1,fld_nis.getText());
            preparedStatement.setString(2, "2020-2021");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            String ladida = rs.getString("bulan");
                if (ladida.contains("Januari")) {
                    jan.setSelected(false);
                    jan.setDisable(true);
                    bulan.remove(jan.getText());
                    
                }
                if (ladida.contains("Februari")) {
                    feb.setSelected(false);
                    feb.setDisable(true);
                    bulan.remove(feb.getText());
                    
                    
                }
                if (ladida.contains("Maret")) {
                    mar.setSelected(false);
                    mar.setDisable(true);
                    bulan.remove(mar.getText());
                    
                }
                if (ladida.contains("April")) {
                    apr.setSelected(false);
                    apr.setDisable(true);
                    bulan.remove(apr.getText());
                    
                }
                if (ladida.contains("Mei")) {
                    may.setSelected(false);
                    may.setDisable(true);
                    bulan.remove(may.getText());
                    
                }
                if (ladida.contains("Juni")) {
                    jun.setSelected(false);
                    jun.setDisable(true);
                    bulan.remove(jun.getText());
                    
                }
                if (ladida.contains("Juli")) {
                    jul.setSelected(false);
                    jul.setDisable(true);
                    bulan.remove(jul.getText());
                    
                }
                if (ladida.contains("Agustus")) {
                    aug.setSelected(false);
                    aug.setDisable(true);
                    bulan.remove(aug.getText());
                    
                }
                if (ladida.contains("September")) {
                    sep.setSelected(false);
                    sep.setDisable(true);
                    bulan.remove(sep.getText());
                    
                }
                if (ladida.contains("Oktober")) {
                    okt.setSelected(false);
                    okt.setDisable(true);
                    bulan.remove(okt.getText());
                    
                }
                if (ladida.contains("November")) {
                    nov.setSelected(false);
                    nov.setDisable(true);
                    bulan.remove(nov.getText());
                    
                }
                if (ladida.contains("Desember")) {
                    dec.setSelected(false);
                    dec.setDisable(true);
                    bulan.remove(dec.getText());
                    
                }
                
        }
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        }
        
    }

    @FXML
    public void bayar(ActionEvent event) throws SQLException, IOException {
        cg++;
        idp.setText(""+cg);
        
        
        String sql = "INSERT INTO transaksi VALUES (?,?,?,?,?,?,?)";
        
        
        if (jan.isSelected()){
            bulan.add(jan.getText());
            jan.setDisable(true);
            jan.setSelected(false);
            
            
        }
        
        if (feb.isSelected()){
            feb.getText();
            bulan.add(feb.getText());
            feb.setDisable(true);
            feb.setSelected(false);
        }

        if (mar.isSelected()){
            mar.getText();
            bulan.add(mar.getText());
            mar.setDisable(true);
            mar.setSelected(false);
        }
        
        if (apr.isSelected()){
            apr.getText();
            bulan.add(apr.getText());
            apr.setDisable(true);
            apr.setSelected(false);
        }
        
        if (may.isSelected()){
            may.getText();
            bulan.add(may.getText());
            may.setDisable(true);
            may.setSelected(false);
        }
        
        if (jun.isSelected()){
            jun.getText();
            bulan.add(jun.getText());
            jun.setDisable(true);
            jun.setSelected(false);
        }
        
        if (jul.isSelected()){
            jul.getText();
            bulan.add(jul.getText());
            jul.setDisable(true);
            jul.setSelected(false);
        }
        
        if (aug.isSelected()){
            aug.getText();
            bulan.add(aug.getText());
            aug.setDisable(true);
            aug.setSelected(false);
        }
        
        if (sep.isSelected()){
            sep.getText();
            bulan.add(sep.getText());
            sep.setDisable(true);
            sep.setSelected(false);
        }
        
        if (okt.isSelected()){
            okt.getText();
            bulan.add(okt.getText());
            okt.setDisable(true);
            okt.setSelected(false);
        }
        
        if (nov.isSelected()){
            nov.getText();
            bulan.add(nov.getText());
            nov.setDisable(true);
            nov.setSelected(false);
        }
        
        if (dec.isSelected()){
            dec.getText();
            bulan.add(dec.getText());
            dec.setDisable(true);
            dec.setSelected(false);
        }
        
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, fld_nis.getText());
                preparedStatement.setString(2, bulan.toString());
                preparedStatement.setString(3, (String) tahunajaran.getValue());
                preparedStatement.setString(4, message.getText());
                preparedStatement.setString(5, idp.getText());
                preparedStatement.setDate  (6, sqlDate);
                preparedStatement.setString(7, hahha.getText());
                preparedStatement.execute();
                bulan.clear();
               
                new laporanStruk("/spp/Struk.jasper"); 
                
                
                check();
    }
    
    @FXML
    private void kembali(ActionEvent event) throws IOException {
                FXMLLoader LoaderP = new FXMLLoader();
                LoaderP.setLocation(getClass().getResource("/spp/paneMenuUtamaPetugas.fxml")); 
                
                LoaderP.load();
                Node node = (Node)event.getSource();
                stage = (Stage) node.getScene().getWindow();
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
    private void cari(ActionEvent event) {
        refcb();
        pilihan();
        check();
        try {
        String search = "SELECT * FROM siswa WHERE nis = ? ";
        preparedStatement = con.prepareStatement(search);
            preparedStatement.setString(1,fld_nis.getText());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
               String nama = rs.getString("nama");
               String kelas= rs.getString("kelas");
                fld_nasis.setText(nama);
                fld_kelas.setText(kelas);
                
                
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
    }

    @FXML
    private void price(MouseEvent event) {
       
        if (jan.isSelected()) 
        {
            jum = jum + 10000;
            
            
        }
        if (feb.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (mar.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (apr.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (may.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (jun.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (jul.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (aug.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (sep.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (okt.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        if (nov.isSelected()) 
        {
            jum = jum + 10000;
        }
        if (dec.isSelected()) 
        {
            jum = jum + 10000;
            
        }
        message.setText(""+jum);
    }
    private void refcb(){
            jan.setSelected(false);
            feb.setSelected(false);
            mar.setSelected(false);
            apr.setSelected(false);
            may.setSelected(false);
            jun.setSelected(false);
            jul.setSelected(false);
            aug.setSelected(false);
            sep.setSelected(false);
            okt.setSelected(false);
            nov.setSelected(false);
            dec.setSelected(false);
            jan.setDisable(false);
            feb.setDisable(false);
            mar.setDisable(false);
            apr.setDisable(false);
            may.setDisable(false);
            jun.setDisable(false);
            jul.setDisable(false);
            aug.setDisable(false);
            sep.setDisable(false);
            okt.setDisable(false);
            nov.setDisable(false);
            dec.setDisable(false);
    }

    @FXML
    private void enter(KeyEvent event) {
    refcb();
        pilihan();
        try {
        String search = "SELECT * FROM siswa WHERE nis = ? ";
        preparedStatement = con.prepareStatement(search);
            preparedStatement.setString(1,fld_nis.getText());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
               String nama = rs.getString("nama");
               String kelas= rs.getString("kelas");
                fld_nasis.setText(nama);
                fld_kelas.setText(kelas);
                
                
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void release(KeyEvent event) {
        if(!fld_nis.getText().matches("[0-9]*")){
           fld_nis.setText("");
    }
   
    }
    public void check() {
        
        if (jan.isDisabled()) {
            tug = tug - 10000;
        }
        if (feb.isDisabled()) {
            tug = tug - 10000;
        }
        if (mar.isDisabled()) {
            tug = tug - 10000;
        }
        if (apr.isDisabled()) {
            tug = tug - 10000;
        }
        if (may.isDisabled()) {
            tug = tug - 10000;
        }
        if (jun.isDisabled()) {
            tug = tug - 10000;
        }
        if (jul.isDisabled()) {
            tug = tug - 10000;
        }
        if (aug.isDisabled()) {
            tug = tug - 10000;
        }
        if (sep.isDisabled()) {
            tug = tug - 10000;
        }
        if (okt.isDisabled()) {
            tug = tug - 10000;
        }
        if (nov.isDisabled()) {
            tug = tug - 10000;
        }
        if (dec.isDisabled()) {
            tug = tug - 10000;
        }
        tunggak.setText(""+tug);
    }
    private void report() {
        new FungsiLaporanPinjaman("/spp/Struk.jasper");
}
}

