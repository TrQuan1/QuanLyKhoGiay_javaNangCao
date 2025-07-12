package app.view;

import javax.swing.*;
import java.awt.*;

public class ChangePassword_View extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtOldPass, txtNewPass, txtConfirmPass;
    private JButton btnChange, btnCancel;

    public ChangePassword_View() {
        setTitle("Đổi mật khẩu");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("Đổi mật khẩu");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(createLabel("Tên đăng nhập:"));
        txtUsername = new JTextField(20);
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(createLabel("Mật khẩu cũ:"));
        txtOldPass = new JPasswordField(20);
        mainPanel.add(txtOldPass);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(createLabel("Mật khẩu mới:"));
        txtNewPass = new JPasswordField(20);
        mainPanel.add(txtNewPass);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(createLabel("Xác thực mật khẩu mới:"));
        txtConfirmPass = new JPasswordField(20);
        mainPanel.add(txtConfirmPass);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnChange = new JButton("Đổi mật khẩu");
        btnChange.setBackground(new Color(0, 102, 204));
        btnChange.setForeground(Color.WHITE);
        btnChange.setFont(new Font("Arial", Font.BOLD, 14));
        btnChange.setFocusPainted(false);
        buttonPanel.add(btnChange);

        btnCancel = new JButton("Hủy");
        btnCancel.setBackground(Color.LIGHT_GRAY);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        buttonPanel.add(btnCancel);

        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private JPanel createLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label);
        return panel;
    }

    // Getters
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtOldPass() {
        return txtOldPass;
    }

    public JPasswordField getTxtNewPass() {
        return txtNewPass;
    }

    public JPasswordField getTxtConfirmPass() {
        return txtConfirmPass;
    }

    public JButton getBtnChange() {
        return btnChange;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }
}
