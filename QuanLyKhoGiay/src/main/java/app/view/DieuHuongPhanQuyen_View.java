package app.view;

import app.model.User;

import javax.swing.*;
import java.awt.*;

public class DieuHuongPhanQuyen_View extends JFrame {
    private JButton btnManageUsers, btnLogout, btnHome, btnDanhSachNhanVien, btnDoiMatKhau;

    public DieuHuongPhanQuyen_View(User user) {
        setTitle("Bảng điều khiển - Xin chào " + user.getFullName());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblWelcome = new JLabel("Xin chào, " + user.getFullName() + "!");
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        
        btnHome = new JButton("Trang chủ");
        btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHome.setPreferredSize(new Dimension(250, 40));
        btnHome.setMaximumSize(new Dimension(250, 40));

        btnManageUsers = new JButton("Phân quyền người dùng");
        btnManageUsers.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnManageUsers.setPreferredSize(new Dimension(250, 40));
        btnManageUsers.setMaximumSize(new Dimension(250, 40));
        
        btnDanhSachNhanVien = new JButton("Danh sách nhân viên");
        btnDanhSachNhanVien.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDanhSachNhanVien.setPreferredSize(new Dimension(250, 40));
        btnDanhSachNhanVien.setMaximumSize(new Dimension(250, 40));
        
        btnDoiMatKhau = new JButton("Thay đổi mật khẩu");
        btnDoiMatKhau.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDoiMatKhau.setPreferredSize(new Dimension(250, 40));
        btnDoiMatKhau.setMaximumSize(new Dimension(250, 40));

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setPreferredSize(new Dimension(250, 40));
        btnLogout.setMaximumSize(new Dimension(250, 40));

        panel.add(lblWelcome);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnHome);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnManageUsers);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnDoiMatKhau);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnDanhSachNhanVien);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnLogout);

        add(panel);
    }

    public JButton getBtnManageUsers() {
        return btnManageUsers;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public void setBtnHome(JButton btnHome) {
        this.btnHome = btnHome;
    }

    public JButton getBtnDanhSachNhanVien() {
        return btnDanhSachNhanVien;
    }

    public void setBtnDanhSachNhanVien(JButton btnDanhSachNhanVien) {
        this.btnDanhSachNhanVien = btnDanhSachNhanVien;
    }

    public JButton getBtnDoiMatKhau() {
        return btnDoiMatKhau;
    }

    public void setBtnDoiMatKhau(JButton btnDoiMatKhau) {
        this.btnDoiMatKhau = btnDoiMatKhau;
    }
    
}
