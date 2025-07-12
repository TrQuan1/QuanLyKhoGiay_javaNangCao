/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DAO;

import app.connect.MyConnection;
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
public class SanPham_DAO {
    public List<SanPham> getAll(){
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()){
            String sql = "select * from products p "
                    + "join category_Product cp ON p.id_product = cp.id_product "
                    + "join categories c ON cp.id_category = c.id_category";
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
            sp.setPhanLoai(rs.getString("name_category"));
            sp.setGiaNhap(rs.getDouble("giaNhap"));
            sp.setGiaBan(rs.getDouble("giaBan"));
            spList.add(sp);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện getAllSanPham");
            e.printStackTrace();
        }
        return spList;
    }
    
    
    // Thêm sản phẩm
    public boolean addSanPham(SanPham sanPham, int idCategory) {
        try (Connection conn = new MyConnection().getConnection()){
            // Kiểm tra mã sản phẩm đã tồn tại
            String sqlCheck = "SELECT id_product FROM products WHERE id_product = ?";
            PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
            pstmtCheck.setString(1, sanPham.getId());
            ResultSet rs = pstmtCheck.executeQuery();//đối tượng lưu kết quả câu lệnh truy vấn
            if (rs.next()) {
                return false; // Mã sản phẩm đã tồn tại
            }

            // Thêm vào bảng products
            String sqlProduct = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtProduct = conn.prepareStatement(sqlProduct);//công cụ giúp thực thi câu lệnh sql có tham số ?(chuẩn bị câu lệnh sql)
            pstmtProduct.setString(1, sanPham.getId());
            pstmtProduct.setString(2, sanPham.getTenSanPham());
            pstmtProduct.setInt(3, sanPham.getSize());
            pstmtProduct.setString(4, sanPham.getMauSac());
            pstmtProduct.setString(5, sanPham.getMoTa());
            pstmtProduct.setString(6, sanPham.getHinhAnh());
            pstmtProduct.setString(7, sanPham.getThuongHieu());
            pstmtProduct.setDouble(8, sanPham.getGiaNhap());
            pstmtProduct.setDouble(9, sanPham.getGiaBan());
            int rowsAffected = pstmtProduct.executeUpdate();//trả về số dòng mà sql thao tác đúng(thực thi)
            
            if (rowsAffected > 0) {
                // Thêm vào bảng category_product
                String sqlCategoryProduct = "INSERT INTO category_product (id_product, id_category) VALUES (?, ?)";
                PreparedStatement pstmtCategoryProduct = conn.prepareStatement(sqlCategoryProduct);
                pstmtCategoryProduct.setString(1, sanPham.getId());
                pstmtCategoryProduct.setInt(2, idCategory);
                pstmtCategoryProduct.executeUpdate();
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("Lỗi khi thực hiện addSanPham");
            ex.printStackTrace();
            return false;
        }
    }
    
    //Sửa sản phẩm
    public boolean updateSanPham(SanPham sanPham, int idCategory){
        try (Connection conn = new MyConnection().getConnection()){
            //Cập nhật bảng 
            String sqlProduct = "UPDATE products SET name_product = ?, size = ?, color_product = ?, description = ?, " +
                                "image_url = ?, brand = ?, giaNhap = ?, giaBan = ? WHERE id_product = ?";
            PreparedStatement pstmtProduct = conn.prepareStatement(sqlProduct);
            pstmtProduct.setString(1, sanPham.getTenSanPham());
            pstmtProduct.setInt(2, sanPham.getSize());
            pstmtProduct.setString(3, sanPham.getMauSac());
            pstmtProduct.setString(4, sanPham.getMoTa());
            pstmtProduct.setString(5, sanPham.getHinhAnh());
            pstmtProduct.setString(6, sanPham.getThuongHieu());
            pstmtProduct.setDouble(7, sanPham.getGiaNhap());
            pstmtProduct.setDouble(8, sanPham.getGiaBan());
            pstmtProduct.setString(9, sanPham.getId());
            int rowsAffected = pstmtProduct.executeUpdate();
            
            if (rowsAffected > 0) {
                // Xóa liên kết cũ trong category_product
                String sqlDeleteCategory = "DELETE FROM category_product WHERE id_product = ?";
                PreparedStatement pstmtCategoryProduct = conn.prepareStatement(sqlDeleteCategory);
                pstmtCategoryProduct.setString(1, sanPham.getId());
                pstmtCategoryProduct.executeUpdate();

                // Thêm liên kết mới trong category_product
                String sqlInsertCategory = "INSERT INTO category_product (id_product, id_category) VALUES (?, ?)";
                pstmtCategoryProduct = conn.prepareStatement(sqlInsertCategory);
                pstmtCategoryProduct.setString(1, sanPham.getId());
                pstmtCategoryProduct.setInt(2, idCategory);
                pstmtCategoryProduct.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện updateSanPham");
            e.printStackTrace();
            return false;
        }
    }
    
    // Xóa sản phẩm
    public boolean deleteById(String idProduct) {
        try (Connection conn = new MyConnection().getConnection()){
            // Xóa liên kết trong category_product
            String sqlDeleteCategory = "DELETE FROM category_product WHERE id_product = ?";
            PreparedStatement pstmtCategoryProduct = conn.prepareStatement(sqlDeleteCategory);
            pstmtCategoryProduct.setString(1, idProduct);
            pstmtCategoryProduct.executeUpdate();

            // Xóa sản phẩm trong products
            String sqlDeleteProduct = "DELETE FROM products WHERE id_product = ?";
            PreparedStatement pstmtProduct = conn.prepareStatement(sqlDeleteProduct);
            pstmtProduct.setString(1, idProduct);
            int rowsAffected = pstmtProduct.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực hiện deleteSanPham");
            e.printStackTrace();
            return false;
        } 
    }
    

    //phương thức này dùng cho loadSanPhamToUpdateForm() để lấy đúng ảnh cho sản phẩm mà mình chọn
    public String getHinhAnhById(String id) {
        String hinhAnh = null;
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT image_url FROM products WHERE id_product = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                hinhAnh = rs.getString("image_url");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy hình ảnh: " + e.getMessage());
            e.printStackTrace();
        }
        return hinhAnh;
    }

    //Tìm kiếm    
    public List<SanPham> searchByMaSp(String maSp) {
    List<SanPham> spList = new ArrayList<>();
    try (Connection conn = new MyConnection().getConnection()) {
        String sql = "SELECT * FROM products p " +
                     "JOIN category_product cp ON p.id_product = cp.id_product " +
                     "JOIN categories c ON cp.id_category = c.id_category " +
                     "WHERE p.id_product LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + maSp + "%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setId(rs.getString("id_product"));
            sp.setTenSanPham(rs.getString("name_product"));
            sp.setSize(rs.getInt("size"));
            sp.setMauSac(rs.getString("color_product"));
            sp.setMoTa(rs.getString("description"));
            sp.setHinhAnh(rs.getString("image_url"));
            sp.setThuongHieu(rs.getString("brand"));
            sp.setPhanLoai(rs.getString("name_category"));
            sp.setGiaNhap(rs.getDouble("giaNhap"));
            sp.setGiaBan(rs.getDouble("giaBan"));
            spList.add(sp);
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi tìm kiếm theo mã sản phẩm");
        e.printStackTrace();
    }
    return spList;
}

    public List<SanPham> searchByTenSp(String tenSp) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p " +
                        "JOIN category_product cp ON p.id_product = cp.id_product " +
                        "JOIN categories c ON cp.id_category = c.id_category " +
                        "WHERE p.name_product LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + tenSp + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên sản phẩm");
            e.printStackTrace();
        }
        return spList;
    }

    public List<SanPham> searchBySize(int size) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
           String sql = "SELECT * FROM products p " +
                     "JOIN category_product cp ON p.id_product = cp.id_product " +
                     "JOIN categories c ON cp.id_category = c.id_category " +
                     "WHERE p.size = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, size);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo size");
            e.printStackTrace();
        }
        return spList;
    }

    public List<SanPham> searchByThuongHieu(String thuongHieu) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p " +
                        "JOIN category_product cp ON p.id_product = cp.id_product " +
                        "JOIN categories c ON cp.id_category = c.id_category " +
                        "WHERE p.brand LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + thuongHieu + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo thương hiệu");
            e.printStackTrace();
        }
        return spList;
    }

    public List<SanPham> searchByPhanLoai(String phanLoai) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p " +
                        "JOIN category_product cp ON p.id_product = cp.id_product " +
                        "JOIN categories c ON cp.id_category = c.id_category " +
                        "WHERE c.name_category LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + phanLoai + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo phân loại");
            e.printStackTrace();
        }
        return spList;
    }

    public List<SanPham> searchByMau(String mau) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p " +
                        "JOIN category_product cp ON p.id_product = cp.id_product " +
                        "JOIN categories c ON cp.id_category = c.id_category " +
                        "WHERE p.color_product LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + mau + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo màu");
            e.printStackTrace();
        }
        return spList;
    }
    
    // Tìm kiếm theo giá nhập
    public List<SanPham> searchByGiaNhap(double minGia, double maxGia) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p "
                    + "JOIN category_product cp ON p.id_product = cp.id_product "
                    + "JOIN categories c ON cp.id_category = c.id_category "
                    + "WHERE p.giaNhap BETWEEN ? AND ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, minGia);
            pstmt.setDouble(2, maxGia);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo giá nhập");
            e.printStackTrace();
        }
        return spList;
    }
    
    // Tìm kiếm theo giá bán
    public List<SanPham> searchByGiaBan(double minGia, double maxGia) {
        List<SanPham> spList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT * FROM products p "
                    + "JOIN category_product cp ON p.id_product = cp.id_product "
                    + "JOIN categories c ON cp.id_category = c.id_category "
                    + "WHERE p.giaBan BETWEEN ? AND ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, minGia);
            pstmt.setDouble(2, maxGia);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id_product"));
                sp.setTenSanPham(rs.getString("name_product"));
                sp.setSize(rs.getInt("size"));
                sp.setMauSac(rs.getString("color_product"));
                sp.setMoTa(rs.getString("description"));
                sp.setHinhAnh(rs.getString("image_url"));
                sp.setThuongHieu(rs.getString("brand"));
                sp.setPhanLoai(rs.getString("name_category"));
                sp.setGiaNhap(rs.getDouble("giaNhap"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                spList.add(sp);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo giá bán");
            e.printStackTrace();
        }
        return spList;
    }
    
}
    
