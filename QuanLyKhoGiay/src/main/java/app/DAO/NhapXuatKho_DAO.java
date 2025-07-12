/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DAO;

import app.model.ChiTietPhieuNhap;
import app.model.ChiTietPhieuXuat;
import app.connect.MyConnection;
import app.model.PhieuNhap;
import app.model.PhieuXuat;
import app.model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhapXuatKho_DAO {
    public List<SanPham> getAll(){
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()){
            String sql = "select * from products";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
            spList.add(sp);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện getAll");
            e.printStackTrace();
        }
        return spList;
    }
    
    //----------------------------------------------------------------------------------------
    //code DAO Phieu Nhap
        public boolean themPhieuNhap(PhieuNhap pn) {
            try (Connection conn = new MyConnection().getConnection()) {
                String sql = "INSERT INTO phieu_nhap (id_phieu_nhap, nguoi_lap, ngay_lap, id_kho, ghi_chu) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, pn.getId());
                ps.setString(2, pn.getNguoiLap());
                ps.setDate(3, new java.sql.Date(pn.getNgayLap().getTime()));
                ps.setString(4, pn.getKhoNhap());
                ps.setString(5, pn.getGhiChu());
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
    
        public void themChiTietPhieuNhap(List<ChiTietPhieuNhap> list){
            try (Connection conn = new MyConnection().getConnection()){
                String sql = "INSERT INTO chitiet_phieu_nhap (id_phieu_nhap, id_product, so_luong, don_gia) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (ChiTietPhieuNhap ct : list) {
                ps.setString(1, ct.getIdPhieuNhap());
                ps.setString(2, ct.getIdSanPham());
                ps.setInt(3, ct.getSoLuong());
                ps.setDouble(4, ct.getDonGia());
                ps.addBatch();
            }
            ps.executeBatch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Cập nhật tồn kho
        public void updateTonKhoTheoKho(List<ChiTietPhieuNhap> list, String id_kho) {
            try (Connection conn = new MyConnection().getConnection()) {
                String sql = "INSERT INTO products_kho (id_product, id_kho, so_luong) VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE so_luong = so_luong + ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (ChiTietPhieuNhap ct : list) {
                    ps.setString(1, ct.getIdSanPham());
                    ps.setString(2, id_kho);
                    ps.setInt(3, ct.getSoLuong()); // Ghi số lượng ban đầu
                    ps.setInt(4, ct.getSoLuong()); // Cộng dồn số lượng khi trùng khóa
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (Exception e) {
                e.printStackTrace();
                }
        }
        
        public boolean kiemTraPhieuDaTonTai(String idPhieu) {
            try (Connection conn = new MyConnection().getConnection()) {
                String sql = "SELECT id_phieu_nhap FROM phieu_nhap WHERE id_phieu_nhap = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, idPhieu);
                ResultSet rs = ps.executeQuery();
                return rs.next(); // true nếu tồn tại
            } catch (Exception e) {
                e.printStackTrace();
                return false;
                }
        } 
        
        public boolean luuPhieuNhap(PhieuNhap pn, List<ChiTietPhieuNhap> listChiTiet) {
            if (kiemTraPhieuDaTonTai(pn.getId())) {
                System.out.println("Phiếu đã tồn tại.");
                return false;
            }
            boolean inserted = themPhieuNhap(pn);
            if (inserted) {
                themChiTietPhieuNhap(listChiTiet);
                updateTonKhoTheoKho(listChiTiet, pn.getKhoNhap());
                return true;
            }
            return false;
        }
        
        public String sinhMaPhieuNhap() {
            return "PN" + System.currentTimeMillis();
        }
        public String sinhMaPhieuXuat() {
            return "PX" + System.currentTimeMillis(); 
        }
        
        public boolean luuPhieuXuat(PhieuXuat phieu, List<ChiTietPhieuXuat> chiTietList) {
            try (Connection conn = new MyConnection().getConnection()) {
                conn.setAutoCommit(false);

                // Kiểm tra tồn kho trước khi xuất
                String checkTonKhoSql = "SELECT id_product, so_luong FROM products_kho WHERE id_kho = ? AND id_product = ? FOR UPDATE";
                PreparedStatement psCheck = conn.prepareStatement(checkTonKhoSql);

            for (ChiTietPhieuXuat ct : chiTietList) {
                psCheck.setString(1, phieu.getIdKho());
                psCheck.setString(2, ct.getIdSanPham());
                ResultSet rs = psCheck.executeQuery();
                if (!rs.next() || rs.getInt("so_luong") < ct.getSoLuong()) {
                    conn.rollback();
                    System.out.println("Không đủ số lượng tồn kho cho sản phẩm: " + ct.getIdSanPham());
                    return false;
                }
            }
               
            // Thêm phiếu xuất
            String insertPhieu = "INSERT INTO phieu_xuat(id_phieu_xuat, nguoi_lap, ngay_lap, id_kho, ly_do) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psPhieu = conn.prepareStatement(insertPhieu);
            psPhieu.setString(1, phieu.getId());
            psPhieu.setString(2, phieu.getNguoiLap());
            psPhieu.setDate(3, new java.sql.Date(phieu.getNgayLap().getTime()));
            psPhieu.setString(4, phieu.getIdKho());
            psPhieu.setString(5, phieu.getGhiChu());
            psPhieu.executeUpdate();

            // Thêm chi tiết phiếu xuất
            String insertChiTiet = "INSERT INTO chitiet_phieu_xuat(id_phieu_xuat, id_product, so_luong, don_gia) VALUES (?, ?, ?, ?)";
            PreparedStatement psChiTiet = conn.prepareStatement(insertChiTiet);
            for (ChiTietPhieuXuat ct : chiTietList) {
                psChiTiet.setString(1, ct.getIdPhieu());
                psChiTiet.setString(2, ct.getIdSanPham());
                psChiTiet.setInt(3, ct.getSoLuong());
                psChiTiet.setDouble(4, ct.getDonGia());
                psChiTiet.addBatch();
            }
            psChiTiet.executeBatch();

            // Cập nhật tồn kho
            String updateTonKho = "UPDATE products_kho SET so_luong = so_luong - ? WHERE id_product = ? AND id_kho = ?";
            PreparedStatement psUpdate = conn.prepareStatement(updateTonKho);
            for (ChiTietPhieuXuat ct : chiTietList) {
                psUpdate.setInt(1, ct.getSoLuong());
                psUpdate.setString(2, ct.getIdSanPham());
                psUpdate.setString(3, phieu.getIdKho());
                psUpdate.addBatch();
            }
            psUpdate.executeBatch();
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
//=============================Code DAO của Danh Sách phiếu=================================================================
    public List<Object[]> getDanhSachPhieu(String loaiPhieu, String idKho, String ngayBatDau, String ngayKetThuc) {
        List<Object[]> danhSach = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "";
            PreparedStatement ps;

            // Xây dựng truy vấn cho phiếu nhập
            if (loaiPhieu.equals("Tất cả") || loaiPhieu.equals("Nhập")) {
                sql = "SELECT p.id_phieu_nhap AS id, 'Nhập' AS loai, p.nguoi_lap, p.ngay_lap, k.ten_kho, SUM(ct.so_luong * ct.don_gia) AS tong_gia_tri, SUM(ct.so_luong) AS tong_so_luong " +
                    "FROM phieu_nhap p JOIN kho k ON p.id_kho = k.id_kho " +
                    "JOIN chitiet_phieu_nhap ct ON p.id_phieu_nhap = ct.id_phieu_nhap " +
                    "WHERE 1=1 ";
                if (!idKho.isEmpty()) sql += "AND p.id_kho = ? ";
                if (!ngayBatDau.isEmpty()) sql += "AND p.ngay_lap >= ? ";
                if (!ngayKetThuc.isEmpty()) sql += "AND p.ngay_lap <= ? ";
                sql += "GROUP BY p.id_phieu_nhap";
            }

            // Xây dựng truy vấn cho phiếu xuất
            if (loaiPhieu.equals("Tất cả") || loaiPhieu.equals("Xuất")) {
                if (!sql.isEmpty()) sql += " UNION ALL ";
                sql += "SELECT p.id_phieu_xuat AS id, 'Xuất' AS loai, p.nguoi_lap, p.ngay_lap, k.ten_kho, SUM(ct.so_luong * ct.don_gia) AS tong_gia_tri, SUM(ct.so_luong) AS tong_so_luong " +
                    "FROM phieu_xuat p JOIN kho k ON p.id_kho = k.id_kho " +
                    "JOIN chitiet_phieu_xuat ct ON p.id_phieu_xuat = ct.id_phieu_xuat " +
                    "WHERE 1=1 ";
                if (!idKho.isEmpty()) sql += "AND p.id_kho = ? ";
                if (!ngayBatDau.isEmpty()) sql += "AND p.ngay_lap >= ? ";
                if (!ngayKetThuc.isEmpty()) sql += "AND p.ngay_lap <= ? ";
                sql += "GROUP BY p.id_phieu_xuat";
            }

            ps = conn.prepareStatement(sql);

            int paramIndex = 1;
            // Đặt tham số cho phiếu nhập
            if (loaiPhieu.equals("Tất cả") || loaiPhieu.equals("Nhập")) {
                if (!idKho.isEmpty()) ps.setString(paramIndex++, idKho);
                if (!ngayBatDau.isEmpty()) ps.setString(paramIndex++, ngayBatDau);
                if (!ngayKetThuc.isEmpty()) ps.setString(paramIndex++, ngayKetThuc);
            }
            // Đặt tham số cho phiếu xuất (chỉ nếu có UNION ALL)
            if (loaiPhieu.equals("Tất cả") || loaiPhieu.equals("Xuất")) {
                if (!idKho.isEmpty()) ps.setString(paramIndex++, idKho);
                if (!ngayBatDau.isEmpty()) ps.setString(paramIndex++, ngayBatDau);
                if (!ngayKetThuc.isEmpty()) ps.setString(paramIndex++, ngayKetThuc);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                danhSach.add(new Object[]{
                    rs.getString("id"),
                    rs.getString("loai"),
                    rs.getString("nguoi_lap"),
                    rs.getDate("ngay_lap"),
                    rs.getString("ten_kho"),
                    rs.getDouble("tong_gia_tri"),
                    rs.getInt("tong_so_luong")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public List<Object[]> getChiTietPhieu(String idPhieu, String loaiPhieu) {
        List<Object[]> chiTietList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = loaiPhieu.equals("Nhập") ?
            "SELECT ct.id_product, p.name_product, p.size, ct.so_luong, ct.don_gia, ct.so_luong * ct.don_gia AS thanh_tien " +
            "FROM chitiet_phieu_nhap ct LEFT JOIN products p ON ct.id_product = p.id_product " +
            "WHERE ct.id_phieu_nhap = ?" :
            "SELECT ct.id_product, p.name_product, p.size, ct.so_luong, ct.don_gia, ct.so_luong * ct.don_gia AS thanh_tien " +
            "FROM chitiet_phieu_xuat ct LEFT JOIN products p ON ct.id_product = p.id_product " +
            "WHERE ct.id_phieu_xuat = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPhieu);
            ResultSet rs = ps.executeQuery();
            System.out.println("Thực thi truy vấn cho idPhieu: " + idPhieu + ", loaiPhieu: " + loaiPhieu);
            while (rs.next()) {
                chiTietList.add(new Object[]{
                    rs.getString("id_product"),
                    rs.getString("name_product"),
                    rs.getInt("size"),
                    rs.getInt("so_luong"),
                    rs.getDouble("don_gia"),
                    rs.getDouble("thanh_tien")
                });
                System.out.println("Đã tìm thấy: " + rs.getString("id_product") + ", Tên: " + rs.getString("name_product") + ", Size: " + rs.getInt("size"));
            }
            if (chiTietList.isEmpty()) {
                System.out.println("Không tìm thấy chi tiết cho idPhieu: " + idPhieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi truy vấn chi tiết: " + e.getMessage());
        }
        return chiTietList;
    }

    public boolean xoaPhieu(String idPhieu, String loaiPhieu) {
        try (Connection conn = new MyConnection().getConnection()) {
            conn.setAutoCommit(false);
            String tablePhieu = loaiPhieu.equals("Nhập") ? "phieu_nhap" : "phieu_xuat";
            String tableChiTiet = loaiPhieu.equals("Nhập") ? "chitiet_phieu_nhap" : "chitiet_phieu_xuat";
            String idField = loaiPhieu.equals("Nhập") ? "id_phieu_nhap" : "id_phieu_xuat";

            // Xóa chi tiết phiếu
            String sqlChiTiet = "DELETE FROM " + tableChiTiet + " WHERE " + idField + " = ?";
            PreparedStatement psChiTiet = conn.prepareStatement(sqlChiTiet);
            psChiTiet.setString(1, idPhieu);
            psChiTiet.executeUpdate();

            // Xóa phiếu
            String sqlPhieu = "DELETE FROM " + tablePhieu + " WHERE " + idField + " = ?";
            PreparedStatement psPhieu = conn.prepareStatement(sqlPhieu);
            psPhieu.setString(1, idPhieu);
            psPhieu.executeUpdate();

            // Cập nhật tồn kho (nếu là phiếu xuất, tăng lại số lượng; nếu là phiếu nhập, giảm số lượng)
            String sqlUpdateTonKho = loaiPhieu.equals("Nhập") ?
                "UPDATE products_kho SET so_luong = so_luong - ? WHERE id_product = ? AND id_kho = ?" :
                "UPDATE products_kho SET so_luong = so_luong + ? WHERE id_product = ? AND id_kho = ?";
            PreparedStatement psUpdateTonKho = conn.prepareStatement(sqlUpdateTonKho);

            String sqlGetChiTiet = loaiPhieu.equals("Nhập") ?
                "SELECT id_product, so_luong, id_kho FROM chitiet_phieu_nhap JOIN phieu_nhap ON chitiet_phieu_nhap.id_phieu_nhap = phieu_nhap.id_phieu_nhap WHERE chitiet_phieu_nhap.id_phieu_nhap = ?" :
                "SELECT id_product, so_luong, id_kho FROM chitiet_phieu_xuat JOIN phieu_xuat ON chitiet_phieu_xuat.id_phieu_xuat = phieu_xuat.id_phieu_xuat WHERE chitiet_phieu_xuat.id_phieu_xuat = ?";
            PreparedStatement psGetChiTiet = conn.prepareStatement(sqlGetChiTiet);
            psGetChiTiet.setString(1, idPhieu);
            ResultSet rs = psGetChiTiet.executeQuery();
            while (rs.next()) {
                psUpdateTonKho.setInt(1, rs.getInt("so_luong"));
                psUpdateTonKho.setString(2, rs.getString("id_product"));
                psUpdateTonKho.setString(3, rs.getString("id_kho"));
                psUpdateTonKho.addBatch();
            }
            psUpdateTonKho.executeBatch();

            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

        


