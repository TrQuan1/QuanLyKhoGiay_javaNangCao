package app.controller;

import app.model.User;
import app.DAO.User_DAO;
import app.view.Register_View;

import javax.swing.*;

public class Register_Controller {
    private final Register_View registerView;
    private final User_DAO dao;

    public Register_Controller(Register_View view, User_DAO dao) {
        this.registerView = view;
        this.dao = dao;

        registerView.getBtnRegister().addActionListener(e -> register());
        registerView.getBtnBack().addActionListener(e -> registerView.dispose());
    }

    private void register() {
        String u = registerView.getTxtUsername().getText().trim();
        String eMail = registerView.getTxtEmail().getText().trim();
        String p = new String(registerView.getTxtPassword().getPassword());
        String fullName = registerView.getTxtFullName().getText().trim();
        String phone = registerView.getTxtPhone().getText().trim();
        String address = registerView.getTxtAddress().getText().trim();

        if (u.isEmpty() || eMail.isEmpty() || p.isEmpty() || fullName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(registerView, "Điền đầy đủ thông tin.");
            return;
        }

        User user = new User(u, eMail, p, fullName, phone, address);
        if (dao.add(user)) {
            JOptionPane.showMessageDialog(registerView, "Đăng ký thành công.");
            registerView.dispose();
        } else {
            JOptionPane.showMessageDialog(registerView, "Tài khoản hoặc email đã tồn tại.");
        }
    }
} 
