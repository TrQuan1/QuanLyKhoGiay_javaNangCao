package app.view;

import javax.swing.*;
import java.awt.*;

public class Register_View extends JFrame {
    private JTextField txtUsername, txtEmail, txtFullName, txtPhone, txtAddress;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnBack;

    public Register_View() {
        setTitle("Đăng ký - N Shoes");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon("C:\\Users\\vumin\\Documents\\1 JAVA NÂNG CAO\\DangNhapDangky\\src\\app\\logo.jpg");
        Image scaled = icon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaled);
        JLabel lblLogo = new JLabel(resizedIcon);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDangKy = new JLabel("Đăng ký");
        lblDangKy.setFont(new Font("Arial", Font.BOLD, 18));
        lblDangKy.setForeground(new Color(0, 102, 204));
        lblDangKy.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(lblLogo);
        logoPanel.add(lblDangKy);

        mainPanel.add(logoPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(createFormField("Tên đăng nhập:", txtUsername = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFormField("Email:", txtEmail = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFormField("Mật khẩu:", txtPassword = new JPasswordField()));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFormField("Họ tên:", txtFullName = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFormField("Số điện thoại:", txtPhone = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFormField("Địa chỉ:", txtAddress = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(20));

        btnRegister = new JButton("Đăng ký");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setBackground(new Color(0, 102, 204));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setPreferredSize(new Dimension(300, 40));
        btnRegister.setMaximumSize(new Dimension(300, 40));
        btnRegister.setFocusPainted(false);

        btnBack = new JButton("Quay lại");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 13));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setPreferredSize(new Dimension(300, 35));
        btnBack.setMaximumSize(new Dimension(300, 35));
        btnBack.setFocusPainted(false);

        mainPanel.add(btnRegister);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnBack);

        add(mainPanel);
    }

    private JPanel createFormField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label, BorderLayout.NORTH);
        textField.setPreferredSize(new Dimension(300, 28));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtFullName() {
        return txtFullName;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
} 
