/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spp;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.ConnectionUtil;

/**
 *
 * @author BSPNKBB
 */
public class laporanStruk {
    ConnectionUtil conn;
    
    public laporanStruk(String namareport){
        try{
            
            
            InputStream is = getClass().getResourceAsStream(namareport);
//            
            String sql = "select siswa.nis,siswa.nama,siswa.kelas,bulan,tanggal,petugas.nama as namaPetugas,transaksi.id from transaksi inner join siswa on transaksi.nis=siswa.nis inner join petugas on transaksi.id_petugas=petugas.id ORDER by transaksi.id desc limit 1";
            ResultSet rs = conn.connection().createStatement().executeQuery(sql);
           
            JRDataSource ds = new JRResultSetDataSource(rs);
            JasperPrint jp = JasperFillManager.fillReport(
                    is, new HashMap(), ds);
     
     
            JasperViewer.viewReport(jp,false);
        }
          catch (Exception e){
            e.printStackTrace();
    }
    }
}


