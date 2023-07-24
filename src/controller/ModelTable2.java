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
public class ModelTable2 {
    String bulan,ta,id,tanggal;
    
    public ModelTable2(String bulan, String ta, String id, String tanggal){
        this.id = id;
        this.ta = ta;
        this.tanggal = tanggal;
        this.bulan = bulan;
}
    public String getId(){
            return id;
        }
        public String getTa(){
            return ta;
        }
        public String getTanggal(){
            return tanggal;
        }
        public String getBulan(){
            return bulan;
        }
}
