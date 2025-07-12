package app.view;

import javax.swing.*;
import java.awt.*;

public class Login_View extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister, btnForgot;

    public Login_View() {
        setTitle("Đăng nhập - N Shoes");
        setSize(420, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        //logo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        ImageIcon icon = new ImageIcon("D:\\JAVA NÂNG CAO\\NetBeans_Project\\QuanLyKhoGiay\\src\\main\\java\\app\\logo.jpg");
        Image scaled = icon.getImage().getScaledInstance(90, 70, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblLogo, BorderLayout.CENTER);


        mainPanel.add(topPanel, BorderLayout.NORTH);

        //đăng nhập 
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername = createRoundedTextField();

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword = createRoundedPasswordField();

        formPanel.add(lblUsername);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblPassword);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(30));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));

        btnLogin = createStyledButton("Đăng nhập");
        btnRegister = createStyledButton("Đăng ký");

        btnForgot = new JButton("Quên mật khẩu?");
        btnForgot.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnForgot.setBackground(Color.WHITE);
        btnForgot.setForeground(new Color(100, 100, 100));
        btnForgot.setBorderPainted(false);
        btnForgot.setFocusPainted(false);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForgot.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(btnLogin);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(btnRegister);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(btnForgot);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    private JTextField createRoundedTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnRegister() { return btnRegister; }
    public JButton getBtnForgot() { return btnForgot; }
}
