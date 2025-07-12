/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuNhap {
    private String id;
    private String nguoiLap;
    private Date ngayLap;
    private String khoNhap;
    private String ghiChu;

    public PhieuNhap() {
    }

    public PhieuNhap(String id, String nguoiLap, Date ngayLap, String khoNhap, String ghiChu) {
        this.id = id;
        this.nguoiLap = nguoiLap;
        this.ngayLap = ngayLap;
        this.khoNhap = khoNhap;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getKhoNhap() {
        return khoNhap;
    }

    public void setKhoNhap(String khoNhap) {
        this.khoNhap = khoNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" + "id=" + id + ", nguoiLap=" + nguoiLap + ", ngayLap=" + ngayLap + ", khoNhap=" + khoNhap + ", ghiChu=" + ghiChu + '}';
    }

    
    
}
