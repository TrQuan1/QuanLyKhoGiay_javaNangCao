package app.view;

import javax.swing.*;
import java.awt.*;

public class ForgotPassword_View extends JFrame {
    private JTextField txtEmail;
    private JButton btnSubmit, btnBack;

    public ForgotPassword_View() {
        setTitle("Quên mật khẩu - N Shoes");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding

        // Logo
        ImageIcon icon = new ImageIcon("D:\\JAVA NÂNG CAO\\NetBeans_Project\\MVC\\src\\main\\java\\app\\logo.jpg");
        Image scaled = icon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblLogo);

        // Title
        JLabel lblTitle = new JLabel("Khôi phục mật khẩu");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(lblTitle);

        // Email label
        JPanel emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        emailLabelPanel.setBackground(Color.WHITE);
        JLabel lblEmail = new JLabel("Nhập Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabelPanel.add(lblEmail);
        mainPanel.add(emailLabelPanel);


        // Email text field
        txtEmail = new JTextField();
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(txtEmail);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Submit button
        btnSubmit = new JButton("Khôi phục mật khẩu");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(0, 102, 204));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setPreferredSize(new Dimension(300, 40));
        btnSubmit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        mainPanel.add(btnSubmit);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 13));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setFocusPainted(false);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setPreferredSize(new Dimension(300, 35));
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        mainPanel.add(btnBack);

        // Add panel to frame
        add(mainPanel);
    }

    // Getters
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public JButton getBtnBack() {
        return btnBack;
    }
}
