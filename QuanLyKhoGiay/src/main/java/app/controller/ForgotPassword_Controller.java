package app.controller;

import app.EmailSender;
import app.DAO.User_DAO;
import app.view.ForgotPassword_View;

import javax.swing.*;

public class ForgotPassword_Controller {
    private final ForgotPassword_View forgotView;
    private final User_DAO dao;

    public ForgotPassword_Controller(ForgotPassword_View view, User_DAO dao) {
        this.forgotView = view;
        this.dao = dao;

        forgotView.getBtnSubmit().addActionListener(e -> findPassword());
        forgotView.getBtnBack().addActionListener(e -> forgotView.dispose());
    }

    private void findPassword() {
        String email = forgotView.getTxtEmail().getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(forgotView, "Nhập email.");
            return;
        }

        String pass = dao.findPassword(email);
        if (pass != null) {
            String subject = "Khôi phục mật khẩu - N Shoes";
            String content = "Mật khẩu tài khoản của bạn là: " + pass;

            boolean sent = EmailSender.send(email, subject, content);
            if (sent) {
                JOptionPane.showMessageDialog(forgotView, "Mật khẩu đã được gửi đến email.");
            } else {
                JOptionPane.showMessageDialog(forgotView, "Không gửi được email. Vui lòng thử lại sau.");
            }
        } else {
            JOptionPane.showMessageDialog(forgotView, "Không tìm thấy email.");
        }
    }
} 
