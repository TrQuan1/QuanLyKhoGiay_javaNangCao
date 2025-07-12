package app.controller;

import app.RememberHelper;
import app.model.User;
import app.DAO.User_DAO;
import app.view.*;

import javax.swing.*;

public class Login_Controller {
    private final Login_View loginView;
    private final User_DAO dao;

    public Login_Controller(Login_View view) {
        this.loginView = view;
        this.dao = new User_DAO();

        loginView.getTxtUsername().setText(RememberHelper.loadUsername());

        loginView.getBtnLogin().addActionListener(e -> login());
        loginView.getBtnRegister().addActionListener(e -> openRegister());
        loginView.getBtnForgot().addActionListener(e -> openForgot());
    }

    private void login() {
        String u = loginView.getTxtUsername().getText().trim();
        String p = new String(loginView.getTxtPassword().getPassword());

        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Nhập đầy đủ thông tin.");
            return;
        }

        User user = dao.check(u, p);
        if (user != null) {
            RememberHelper.currentUser = user;
            RememberHelper.saveUsername(user.getUsername());

            JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công. Chào " + user.getUsername() + "!");
            loginView.dispose();
            openDashboard(user);
        } else {
            JOptionPane.showMessageDialog(loginView, "Sai tài khoản hoặc mật khẩu.");
        }
    }

    private void openDashboard(User user) { 
        DieuHuongPhanQuyen_View dashboard = new DieuHuongPhanQuyen_View(user);
        new DieuHuong_Controller(dashboard, user);  // Giao việc xử lý sự kiện cho controller chuyên trách
        dashboard.setVisible(true);

    }
    
    private void openRegister() {
        Register_View registerView = new Register_View();
        new Register_Controller(registerView, dao);
        registerView.setVisible(true);
    }

    private void openForgot() {
        ForgotPassword_View forgotView = new ForgotPassword_View();
        new ForgotPassword_Controller(forgotView, dao);
        forgotView.setVisible(true);
    }

    private void openChangePassword(User user) {
        ChangePassword_View changeView = new ChangePassword_View();
        new ChangePassword_Controller(changeView, dao, user);
        changeView.setVisible(true);
    }
}
