/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import app.model.User;
import javax.swing.JMenuItem;

/**
 *
 * @author Admin
 */
public class QuanLyQuyen {
    private User user;

    public QuanLyQuyen(User user) {
        this.user = user;
    }

    // Áp dụng phân quyền cho menu Thống kê và báo cáo, menu kho
    public void applyPermissions(JMenuItem menuThongKeBaoCao, JMenuItem menuQuanLyKho) {
        if (!isManagerOrHasManagePermission()) {
            menuThongKeBaoCao.setVisible(false);
            menuQuanLyKho.setVisible(false);
        }
    }

    // Kiểm tra quyền truy cập Thống kê báo cáo và kho
    public boolean canViewReports() {
        return isManagerOrHasManagePermission();
    }

    // Phương thức phụ để kiểm tra vai trò manager hoặc quyền quản lý
    private boolean isManagerOrHasManagePermission() {
        return user.getRole().equals("manager") || user.isCanManage();
    }
}
