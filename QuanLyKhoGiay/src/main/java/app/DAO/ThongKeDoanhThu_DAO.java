package app.DAO;



import app.connect.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDoanhThu_DAO {
public List<Object[]> getThongKeDoanhThu(String ngayBatDau, String ngayKetThuc) {
    List<Object[]> result = new ArrayList<>();
    try (Connection conn = new MyConnection().getConnection()) {
        String sql = "SELECT " +
                     "k.id_kho, " +
                     "p.name_product, " +
                     "COALESCE(SUM(CASE WHEN pn.id_phieu_nhap IS NOT NULL THEN ct.so_luong ELSE 0 END), 0) AS sl_nhap, " +
                     "COALESCE(SUM(CASE WHEN pn.id_phieu_nhap IS NOT NULL THEN ct.so_luong * ct.don_gia ELSE 0 END), 0) AS tong_nhap, " +
                     "COALESCE(SUM(CASE WHEN px.id_phieu_xuat IS NOT NULL THEN cx.so_luong ELSE 0 END), 0) AS sl_xuat, " +
                     "COALESCE(SUM(CASE WHEN px.id_phieu_xuat IS NOT NULL THEN cx.so_luong * cx.don_gia ELSE 0 END), 0) AS tong_xuat " +
                     "FROM kho k " +
                     "LEFT JOIN phieu_nhap pn ON k.id_kho = pn.id_kho AND pn.ngay_lap BETWEEN ? AND ? " +
                     "LEFT JOIN chitiet_phieu_nhap ct ON pn.id_phieu_nhap = ct.id_phieu_nhap " +
                     "LEFT JOIN products p ON ct.id_product = p.id_product " +
                     "LEFT JOIN phieu_xuat px ON k.id_kho = px.id_kho AND px.ngay_lap BETWEEN ? AND ? " +
                     "LEFT JOIN chitiet_phieu_xuat cx ON px.id_phieu_xuat = cx.id_phieu_xuat " +
                     "GROUP BY k.id_kho, p.name_product";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ngayBatDau);
        ps.setString(2, ngayKetThuc);
        ps.setString(3, ngayBatDau);
        ps.setString(4, ngayKetThuc);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result.add(new Object[]{
                rs.getString("id_kho"),
                rs.getString("name_product"),
                rs.getInt("sl_nhap"),
                rs.getDouble("tong_nhap"),
                rs.getInt("sl_xuat"),
                rs.getDouble("tong_xuat")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}
}
