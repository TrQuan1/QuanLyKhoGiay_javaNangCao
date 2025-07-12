package app.view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ThongKeDoanhThu_View extends JPanel {
    private JTable table;
    private JButton btnThongKe, btnInBaoCao;
    private JLabel lbTongSL;
    private JDateChooser dateChooserFrom, dateChooserTo;

    public ThongKeDoanhThu_View() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lbTitle = new JLabel("THỐNG KÊ NHẬP XUẤT", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lbTitle.setOpaque(true);
        lbTitle.setBackground(new Color(0, 153, 255));
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setPreferredSize(new Dimension(800, 40));
        add(lbTitle, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JPanel datePanel = new JPanel(new GridBagLayout());
        datePanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        datePanel.add(new JLabel("Từ ngày:"), gbc);
        gbc.gridx = 1;
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setDateFormatString("yyyy-MM-dd");
        datePanel.add(dateChooserFrom, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        datePanel.add(new JLabel("Đến ngày:"), gbc);
        gbc.gridx = 1;
        dateChooserTo = new JDateChooser();
        dateChooserTo.setDateFormatString("yyyy-MM-dd");
        datePanel.add(dateChooserTo, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnThongKe = new JButton("THỐNG KÊ");
        btnThongKe.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnThongKe.setMaximumSize(new Dimension(150, 30));
        buttonPanel.add(btnThongKe);
        buttonPanel.add(Box.createVerticalStrut(10));

        btnInBaoCao = new JButton("IN BÁO CÁO");
        btnInBaoCao.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInBaoCao.setMaximumSize(new Dimension(150, 30));
        buttonPanel.add(btnInBaoCao);

        topPanel.add(datePanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(800, 40));

        lbTongSL = new JLabel("Tổng doanh thu:");
        lbTongSL.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
        bottomPanel.add(lbTongSL);

        add(bottomPanel, BorderLayout.SOUTH);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID Kho", "Tên sản phẩm", "SL Nhập", "Tổng nhập", "SL Xuất", "Tổng xuất"
        });
        setTableModel(model);
    }

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnThongKe() {
        return btnThongKe;
    }

    public JButton getBtnInBaoCao() {
        return btnInBaoCao;
    }

    public JLabel getLbTongSL() {
        return lbTongSL;
    }

    public JDateChooser getDateChooserFrom() {
        return dateChooserFrom;
    }

    public JDateChooser getDateChooserTo() {
        return dateChooserTo;
    }
    
    
}
