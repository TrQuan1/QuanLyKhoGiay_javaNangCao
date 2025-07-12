/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class SanPham {
    private String id;
    private String tenSanPham;
    private int size;
    private String mauSac;
    private String moTa;
    private String hinhAnh;
    private String thuongHieu;
    private String phanLoai;
    private double giaNhap;
    private double giaBan;
    private int soLuongTon;
    
    

    public SanPham() {
    }

    public SanPham(int size, String id, String tenSanPham, String mauSac, String moTa, String hinhAnh, double giaNhap, double giaBan) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.size = size;
        this.mauSac = mauSac;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.thuongHieu = thuongHieu;
        this.phanLoai = phanLoai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    

    @Override
    public String toString() {
        return tenSanPham;
    }



    
}
