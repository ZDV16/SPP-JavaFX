/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author LABKOM-RPL29
 */
public class ModelTable3 {
    String id,nip,nama,kelas;
    public ModelTable3(String id, String nip,String nama,String kelas){
        this.id = id;
        this.nip = nip;
        this.nama = nama;
        this.kelas = kelas;
  
    }
    
    public String getId(){
        return id;
    }
    public String getNip(){
        return nip;
    }
    public String getNama(){
        return nama;
    }
    public String getKelas(){
        return kelas;
    }

    void setId(String id) {
    String id1 = id;
    }
    
}
