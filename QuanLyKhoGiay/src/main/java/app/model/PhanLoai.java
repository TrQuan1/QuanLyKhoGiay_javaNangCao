/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class PhanLoai {
    private int id;
    private String tenPhanLoai;
    private String moTa;

    public PhanLoai() {
    }

    public PhanLoai(int id, String tenPhanLoai, String moTa) {
        this.id = id;
        this.tenPhanLoai = tenPhanLoai;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhanLoai() {
        return tenPhanLoai;
    }

    public void setTenPhanLoai(String tenPhanLoai) {
        this.tenPhanLoai = tenPhanLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return tenPhanLoai;
    }


    
}
