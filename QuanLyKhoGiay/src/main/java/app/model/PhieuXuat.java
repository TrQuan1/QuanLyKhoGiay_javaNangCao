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
public class PhieuXuat {
    private String id;
    private String nguoiLap;
    private Date ngayLap;
    private String idKho;
    private String ghiChu;

    public PhieuXuat() {
    }

    public PhieuXuat(String id, String nguoiLap, Date ngayLap, String idKho, String ghiChu) {
        this.id = id;
        this.nguoiLap = nguoiLap;
        this.ngayLap = ngayLap;
        this.idKho = idKho;
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

    public String getIdKho() {
        return idKho;
    }

    public void setIdKho(String idKho) {
        this.idKho = idKho;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "PhieuXuat{" + "id=" + id + ", nguoiLap=" + nguoiLap + ", ngayLap=" + ngayLap + ", idKho=" + idKho + ", ghiChu=" + ghiChu + '}';
    }
    
}
