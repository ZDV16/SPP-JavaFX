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
public class ModelTable4 {
  String nis,nama,kelas;
public ModelTable4(String nama,String kelas, String nis){
    this.nis = nis;
    this.nama= nama;
    this.kelas = kelas;
}
public String getNis(){
    return nis;
}
public String getNama(){
    return nama;
}
public String getKelas(){
    return kelas;
}
void setNis(String nis) {
        String nis1 = nis;
    }
}
