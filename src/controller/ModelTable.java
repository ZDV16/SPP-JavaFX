/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BSPNKBB
 */
public class ModelTable {
    String id,nama,password,nip;

        
        public ModelTable(String id, String nama, String password, String nip){
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.nip = nip;
        
    }

        public String getId(){
            return id;
        }
        public String getNama(){
            return nama;
        }
        public String getPassword(){
            return password;
        }
        public String getNip(){
            return nip;
        }
       
    void setId(String id) {
        String id1 = id;
}
}