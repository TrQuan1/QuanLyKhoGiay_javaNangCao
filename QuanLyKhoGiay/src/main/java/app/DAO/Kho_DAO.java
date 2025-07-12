/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DAO;

import app.model.Kho;
import app.connect.MyConnection;
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
public class Kho_DAO {
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
        
        public void insert(Kho k) {
            try (Connection con = new MyConnection().getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO kho VALUES (?, ?)");
                ps.setString(1, k.getId());
                ps.setString(2, k.getTenKho());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void update(Kho k) {
            try (Connection con = new MyConnection().getConnection()) {
                PreparedStatement ps = con.prepareStatement("UPDATE kho SET ten_kho = ? WHERE id_kho = ?");
                ps.setString(1, k.getTenKho());
                ps.setString(2, k.getId());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void delete(String id) throws SQLException {
        try (Connection con = new MyConnection().getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM kho WHERE id_kho = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } 
    }
        


}
