package app.controller;


import app.DAO.ThongKeDoanhThu_DAO;
import app.view.ThongKeDoanhThu_View;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class ThongKeDoanhThu_Controller {
    private ThongKeDoanhThu_View view;
    private ThongKeDoanhThu_DAO dao = new ThongKeDoanhThu_DAO();

    public ThongKeDoanhThu_Controller(ThongKeDoanhThu_View view) {
        this.view = view;
        listenActioner();
    }
    //sự kiện nút bấm
    private void listenActioner() {
        view.getBtnThongKe().addActionListener(e -> thongKe());
        view.getBtnInBaoCao().addActionListener(e -> inBaoCao());
    }
//quân sửa
    private void thongKe() {
        java.util.Date fromDate = view.getDateChooserFrom().getDate();
        java.util.Date toDate = view.getDateChooserTo().getDate();

        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn khoảng thời gian!");
            return;
        }

        if (fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(view, "Ngày bắt đầu phải trước ngày kết thúc!");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDau = sdf.format(fromDate);
        String ngayKetThuc = sdf.format(toDate);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID Kho", "Tên sản phẩm", "SL Nhập", "Tổng nhập", "SL Xuất", "Tổng xuất"});
        List<Object[]> data = dao.getThongKeDoanhThu(ngayBatDau, ngayKetThuc);
        double tongDoanhThu = 0;
        DecimalFormat df = new DecimalFormat("#,###");

        for (Object[] row : data) {
            int slNhap = (int) row[2];
            double tongNhap = (double) row[3];
            int slXuat = (int) row[4];
            double tongXuat = (double) row[5];

            // Tính giá nhập trung bình (nếu có nhập)
            double giaNhap = (slNhap > 0) ? (tongNhap / slNhap) : 0;
            // Tính chi phí nhập tương ứng với số lượng xuất
            double chiPhiNhap = slXuat * giaNhap;
            // Lợi nhuận của sản phẩm = Tổng xuất - (Số lượng xuất * Giá nhập)
            double loiNhuan = tongXuat - chiPhiNhap;
            tongDoanhThu += loiNhuan; // Cộng dồn lợi nhuận vào tổng doanh thu

            model.addRow(new Object[]{
                row[0], // ID Kho
                row[1], // Tên sản phẩm
                slNhap,
                df.format(tongNhap) + " VND",
                slXuat,
                df.format(tongXuat) + " VND"
            });
        }

        view.setTableModel(model);
        view.getLbTongSL().setText("Tổng doanh thu: " + df.format(tongDoanhThu) + " VND");
    }


    private void inBaoCao() {
        try {
            JPanel printPanel = new JPanel();
            printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));

            JLabel lblTitle = new JLabel("BÁO CÁO DOANH THU", JLabel.CENTER);
            lblTitle.setFont(new Font("Serif", Font.BOLD, 20));
            lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            printPanel.add(Box.createVerticalStrut(10));
            printPanel.add(lblTitle);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String tuNgay = view.getDateChooserFrom().getDate() != null ? sdf.format(view.getDateChooserFrom().getDate()) : "N/A";
            String denNgay = view.getDateChooserTo().getDate() != null ? sdf.format(view.getDateChooserTo().getDate()) : "N/A";
            JLabel lblNgay = new JLabel("Từ ngày: " + tuNgay + "    đến ngày: " + denNgay);
            lblNgay.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
            printPanel.add(Box.createVerticalStrut(5));
            printPanel.add(lblNgay);

            JTable tableCopy = new JTable(view.getTable().getModel());
            tableCopy.setRowHeight(20);
            tableCopy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            for (int i = 0; i < tableCopy.getColumnCount(); i++) {
                tableCopy.getColumnModel().getColumn(i).setPreferredWidth(120);
            }

            JScrollPane tableScroll = new JScrollPane(tableCopy);
            tableScroll.setPreferredSize(new Dimension(800, tableCopy.getRowCount() * 22 + 30));
            printPanel.add(Box.createVerticalStrut(10));
            printPanel.add(tableScroll);

            JLabel lblDoanhThu = new JLabel(view.getLbTongSL().getText(), JLabel.CENTER);
            lblDoanhThu.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblDoanhThu.setAlignmentX(Component.CENTER_ALIGNMENT);
            printPanel.add(Box.createVerticalStrut(15));
            printPanel.add(lblDoanhThu);

            JFrame frame = new JFrame();
            frame.getContentPane().add(printPanel);
            frame.pack();
            frame.setVisible(false);

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("In báo cáo doanh thu");

            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) return Printable.NO_SUCH_PAGE;

                Graphics2D g2d = (Graphics2D) graphics;

                double panelWidth = printPanel.getWidth();
                double panelHeight = printPanel.getHeight();

                double pageWidth = pageFormat.getImageableWidth();
                double pageHeight = pageFormat.getImageableHeight();

                double scaleX = pageWidth / panelWidth;
                double scaleY = pageHeight / panelHeight;
                double scale = Math.min(scaleX, scaleY);

                double x = pageFormat.getImageableX() + (pageWidth - panelWidth * scale) / 2;
                double y = pageFormat.getImageableY() + 20;

                g2d.translate(x, y);
                g2d.scale(scale, scale);

                printPanel.printAll(g2d);
                return Printable.PAGE_EXISTS;
            });

            if (job.printDialog()) {
                job.print();
                JOptionPane.showMessageDialog(view, "In thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Đã hủy in.");
            }

            frame.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi in: " + e.getMessage());
        }
    }

}