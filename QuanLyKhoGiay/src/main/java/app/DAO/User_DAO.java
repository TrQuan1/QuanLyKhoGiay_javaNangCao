package app.DAO;

import app.model.Employee;
import app.connect.MyConnection;
import app.model.User;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class User_DAO {

    public boolean exists(String username, String email) {
        try (Connection conn = new MyConnection().getConnection()){
            String query = "SELECT * FROM users WHERE username = ? OR email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean add(User user) {
        if (exists(user.getUsername(), user.getEmail())) {
            return false;
        }

        try (Connection conn = new MyConnection().getConnection()){
            String query = "INSERT INTO users(username, email, password, full_name, phone, address, role, can_manage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFullName());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, "staff"); // mặc định là nhân viên
            stmt.setBoolean(8, false);  // mặc định không có quyền quản lý

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User check(String username, String password) {
        try (Connection conn = new MyConnection().getConnection()){
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCanManage(rs.getBoolean("can_manage"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findPassword(String email) {
        try (Connection conn = new MyConnection().getConnection()){
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String username, String newPassword) {
        try (Connection conn = new MyConnection().getConnection()){
            String query = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()){
            String query ="select * from users where role = 'staff'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setCanManage(rs.getBoolean("can_manage"));
                list.add(u);

            }
        }catch (SQLException e){
                e.printStackTrace();
                }
        return list;
    }
    public boolean updatePermission(int userId, boolean canManage) {
        try (Connection conn = new MyConnection().getConnection()){
            String query = "UPDATE users SET can_manage = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, canManage);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = new MyConnection().getConnection()) {
            String sql = "SELECT username, full_name, email, phone, role, can_manage FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setUsername(rs.getString("username"));
            emp.setFullName(rs.getString("full_name"));
            emp.setEmail(rs.getString("email"));
            emp.setPhone(rs.getString("phone"));
            emp.setRole(rs.getString("role"));
            emp.setCanManage(rs.getBoolean("can_manage")); 
            employees.add(emp);
    }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return employees;
}

}
  
