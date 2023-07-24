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
 * @author agung95
 */
public class FungsiLaporanPinjaman {
    ConnectionUtil conn;
    
    public  FungsiLaporanPinjaman (String namareport){
        try{
            
            
            InputStream is = getClass().getResourceAsStream(namareport);
//            
            String sql = "SELECT * FROM transaksi INNER JOIN petugas ON transaksi.id_petugas = petugas.id ORDER BY transaksi.id";
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
