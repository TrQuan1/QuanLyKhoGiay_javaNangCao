/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuXuat {
    private String idPhieu;
    private String idSanPham;
    private int soLuong;
    private double donGia;

    public ChiTietPhieuXuat() {
    }

    
    public ChiTietPhieuXuat(String idPhieu, String idSanPham, int soLuong, double donGia) {
        this.idPhieu = idPhieu;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getIdPhieu() {
        return idPhieu;
    }

    public void setIdPhieu(String idPhieu) {
        this.idPhieu = idPhieu;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuat{" + "idPhieu=" + idPhieu + ", idSanPham=" + idSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
}
