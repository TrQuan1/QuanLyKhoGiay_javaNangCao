/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DAO;

import app.connect.MyConnection;
import app.model.PhanLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhanLoai_DAO {
    public List<PhanLoai> getAll(){
        List<PhanLoai> plList = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()){
            String sql = "select * from categories";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                PhanLoai pl = new PhanLoai();
                pl.setId(rs.getInt("id_category"));
                pl.setTenPhanLoai(rs.getString("name_category"));
                pl.setMoTa(rs.getString("description"));
                plList.add(pl);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện getAllPhanLoai");
            e.printStackTrace();
        }
        return plList;
    }
}
