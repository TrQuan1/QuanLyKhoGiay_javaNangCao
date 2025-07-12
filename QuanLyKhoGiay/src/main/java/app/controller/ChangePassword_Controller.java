package app.controller;

import app.model.User;
import app.DAO.User_DAO;
import app.RememberHelper;
import app.view.ChangePassword_View;

import javax.swing.*;

public class ChangePassword_Controller {
    private final ChangePassword_View view;
    private final User_DAO dao;
    private final User user;

    public ChangePassword_Controller(ChangePassword_View view, User_DAO dao, User user) {
        this.view = view;
        this.dao = dao;
        this.user = user;

        view.getTxtUsername().setText(user.getUsername());
        view.getTxtUsername().setEnabled(false);

        view.getBtnChange().addActionListener(e -> changePassword());
        view.getBtnCancel().addActionListener(e -> view.dispose());
    }

    private void changePassword() {
        String oldP = new String(view.getTxtOldPass().getPassword());
        String newP = new String(view.getTxtNewPass().getPassword());
        String confirm = new String(view.getTxtConfirmPass().getPassword());

        if (oldP.isEmpty() || newP.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Điền đầy đủ thông tin.");
            return;
        }

        if (!oldP.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(view, "Mật khẩu cũ không đúng.");
            return;
        }

        if (!newP.equals(confirm)) {
            JOptionPane.showMessageDialog(view, "Mật khẩu mới không khớp.");
            return;
        }

        if (dao.updatePassword(user.getUsername(), newP)) {
            JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công.");
            User updatedUser = dao.check(user.getUsername(), newP);
            RememberHelper.currentUser = updatedUser;
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Lỗi đổi mật khẩu.");
        }
    }
} 
