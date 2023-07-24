/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author BSPNKBB
 */
public class ModelTable1 {
    String nama,nis,ket;

        
        public ModelTable1(String nama, String nis, String ket){
       
        this.nama = nama;
       
        this.nis = nis;
        this.ket = ket;
        
    }

        public String getNama(){
            return nama;
        }
        public String getNis(){
            return nis;
        }
        public String getKet(){
            return ket;
        }
        
        void setNis(String nis){
            String nis1 = nis;
        }
}