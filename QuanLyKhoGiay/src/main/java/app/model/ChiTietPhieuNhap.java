/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap {
    private String idPhieuNhap;
    private String idSanPham;
    private int soLuong;
    private double donGia;
    private String tenSanPham;
    private int size;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String idPhieuNhap, String idSanPham, int soLuong, double donGia) {
        this.idPhieuNhap = idPhieuNhap;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
//    public ChiTietPhieuNhap(String idPhieuNhap, String idSanPham, int soLuong, double donGia, String tenSanPham, int size) {
//        this.idPhieuNhap = idPhieuNhap;
//        this.idSanPham = idSanPham;
//        this.soLuong = soLuong;
//        this.donGia = donGia;
//        this.tenSanPham = tenSanPham;
//        this.size = size;
//    }

    public String getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(String idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhap{" + "idPhieuNhap=" + idPhieuNhap + ", idSanPham=" + idSanPham + ", soLuong=" + soLuong + ", donGia=" + donGia + ", tenSanPham=" + tenSanPham + ", size=" + size + '}';
    }



   
    
}
