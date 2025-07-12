/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.User;
import app.DAO.User_DAO;
import app.RememberHelper;
import app.view.ChangePassword_View;
import app.view.DieuHuongPhanQuyen_View;
import app.view.EmployeeList_View;
import app.view.Login_View;
import app.view.SanPham_View;
import app.view.UserManagement_View;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DieuHuong_Controller {
    private final DieuHuongPhanQuyen_View view;
    private final SanPham_View spView;
    private final User user;
    
    public DieuHuong_Controller(DieuHuongPhanQuyen_View view, User user) {
        this.view = view;
        this.user = user;
        this.spView = new SanPham_View(user);

        initController();                      
    }
    private void initController() {
        // Ẩn 2 nút "Danh sách nhân viên" nếu không có quyền
        if (!user.getRole().equals("manager") && !user.isCanManage()) {
            view.getBtnDanhSachNhanVien().setVisible(false);
            view.getBtnManageUsers().setVisible(false);
        }
        
        view.getBtnHome().addActionListener((e) -> {spView.setVisible(true);});
        view.getBtnDanhSachNhanVien().addActionListener(e -> openEmployeeList());
        view.getBtnManageUsers().addActionListener(e -> openUserManagement());
        view.getBtnDoiMatKhau().addActionListener(e -> DoiMatKhau());
        view.getBtnLogout().addActionListener(e -> logout());
    }

    private void openEmployeeList() {
        EmployeeList_View empView = new EmployeeList_View();
        new Employee_Controller(empView);
        empView.setVisible(true);
    }

    private void openUserManagement() {
        User_DAO dao = new User_DAO();
        List<User> users = dao.getAllUsers(); 
        UserManagement_View umView = new UserManagement_View(users);
        new UserManagement_Controller(umView);
        umView.setVisible(true);
    }
    
    private void DoiMatKhau(){
        ChangePassword_View view = new ChangePassword_View();
        User_DAO dao = new User_DAO();
        User currentUser = RememberHelper.currentUser; // hoặc biến user bạn đang dùng
        new ChangePassword_Controller(view, dao, currentUser);
        view.setVisible(true);
    }

    private void logout() {
        view.dispose();
        Login_View loginView = new Login_View();
        new Login_Controller(loginView);
        loginView.setVisible(true);
    }
}
