package app.view;



import javax.swing.*;
import java.awt.*;

public class NhapXuatKho_View extends JPanel{
    
    public NhapXuatKho_View(){
        
        this.setLayout(new BorderLayout());
        // Thanh tiêu đề
            JLabel header = new JLabel("HỆ THỐNG QUẢN LÝ NHẬP - XUẤT KHO", JLabel.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 24));
            header.setForeground(Color.BLUE);
            header.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
            this.add(header, BorderLayout.NORTH);

            // Tab Panel
            JTabbedPane tabbedPane = new JTabbedPane();
            
            // === Tab Danh Sách Phiếu === 
            DanhSachPhieu_View danhSachPhieuPanel = new DanhSachPhieu_View();
            tabbedPane.addTab("Danh Sách Phiếu", danhSachPhieuPanel);

            // === Tab Nhập kho ===
            NhapKho_View nhapKhoPanel = new NhapKho_View();
            tabbedPane.addTab("Phiếu Nhập Kho", nhapKhoPanel);

            // === Tab Xuất kho ===
            XuatKho_View xuatKhoPanel = new XuatKho_View();
            tabbedPane.addTab("Phiếu Xuất Kho", xuatKhoPanel);
            this.add(tabbedPane, BorderLayout.CENTER);
    }


}
