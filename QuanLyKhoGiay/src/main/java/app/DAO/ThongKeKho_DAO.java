package app.DAO;

import app.model.Kho;
import app.connect.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeKho_DAO {
    public List<Kho> getAll(){
            List<Kho> kList = new ArrayList<>();
            try (Connection conn = new MyConnection().getConnection()){
                String sql = "select * from kho";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next()){
                    Kho kho = new Kho();
                    kho.setId(rs.getString("id_kho"));
                    kho.setTenKho(rs.getString("ten_kho"));
                    kList.add(kho);
                }
            } catch (Exception e) {
                System.out.println("Lỗi khi thực hiện getAll");
                e.printStackTrace();
            }
            return kList;
        }

    public List<Object[]> getTonKhoTheoKho(String idKho) {
        List<Object[]> result = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT k.id_kho, p.id_product AS id_san_pham, p.name_product AS ten_san_pham, " +
                     "p.size, COALESCE((SELECT SUM(cpn.so_luong) FROM chitiet_phieu_nhap cpn " +
                     "JOIN phieu_nhap pn ON cpn.id_phieu_nhap = pn.id_phieu_nhap " +
                     "WHERE pn.id_kho = k.id_kho AND cpn.id_product = p.id_product), 0) - " +
                     "COALESCE((SELECT SUM(cpx.so_luong) FROM chitiet_phieu_xuat cpx " +
                     "JOIN phieu_xuat px ON cpx.id_phieu_xuat = px.id_phieu_xuat " +
                     "WHERE px.id_kho = k.id_kho AND cpx.id_product = p.id_product), 0) AS so_luong_ton " +
                     "FROM kho k JOIN products_kho pk ON k.id_kho = pk.id_kho " +
                     "JOIN products p ON pk.id_product = p.id_product " +
                     "WHERE k.id_kho = ? " +
                     "ORDER BY p.id_product";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idKho);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id_kho"), // Lấy id_kho từ bảng kho
                    rs.getString("id_san_pham"),
                    rs.getString("ten_san_pham"),
                    rs.getInt("size"),
                    rs.getInt("so_luong_ton")
                };
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}


