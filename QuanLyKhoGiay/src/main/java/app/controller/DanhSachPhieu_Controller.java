package app.controller;

import app.model.Kho;
import app.DAO.NhapXuatKho_DAO;
import app.view.DanhSachPhieu_View;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class DanhSachPhieu_Controller {
    private NhapXuatKho_DAO nhapXuatKhoDAO;
    private DanhSachPhieu_View view;
    private Kho_Controller khoController;
    

    public DanhSachPhieu_Controller(DanhSachPhieu_View view) {
        this.view = view;
        this.nhapXuatKhoDAO = new NhapXuatKho_DAO();
        this.khoController = new Kho_Controller();
        loadDanhSachPhieu();
        ActionListeners();
    }
    
    //======================Sự kiện nút bấm======================
    private void ActionListeners() {
        view.getBtnLoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDanhSachPhieu();
            }
        });

        view.getTable().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    String idPhieu = (String) view.getTableModel().getValueAt(row, 0);
                    String loaiPhieu = (String) view.getTableModel().getValueAt(row, 1);
                    showChiTietPhieu(idPhieu, loaiPhieu);
                } else {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn một phiếu!");
                }
            }
        }
    });

        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    String idPhieu = (String) view.getTableModel().getValueAt(row, 0);
                    String loaiPhieu = (String) view.getTableModel().getValueAt(row, 1);
                    int confirm = JOptionPane.showConfirmDialog(view, "Xóa phiếu " + idPhieu + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (nhapXuatKhoDAO.xoaPhieu(idPhieu, loaiPhieu)) {
                            JOptionPane.showMessageDialog(view, "Xóa phiếu thành công!");
                            loadDanhSachPhieu();
                        } else {
                            JOptionPane.showMessageDialog(view, "Xóa phiếu thất bại!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phiếu để xóa!");
                }
            }
        });
    }
//====================================================================================================
    
    public void loadKhoComboBox() {
        DefaultComboBoxModel<Kho> modelKho = (DefaultComboBoxModel<Kho>) view.getComboKho().getModel();
        modelKho.removeAllElements();
        modelKho.addElement(new Kho("", "Tất cả"));
        khoController.getAllKho(modelKho);
    }

    private void loadDanhSachPhieu() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        String loaiPhieu = (String) view.getComboLoaiPhieu().getSelectedItem();
        Kho kho = (Kho) view.getComboKho().getSelectedItem();
        String idKho = (kho != null) ? kho.getId() : "";
        String ngayBatDau = view.getTxtNgayBatDau().getText().trim();
        String ngayKetThuc = view.getTxtNgayKetThuc().getText().trim();

        List<Object[]> danhSach = nhapXuatKhoDAO.getDanhSachPhieu(loaiPhieu, idKho, ngayBatDau, ngayKetThuc);
        for (Object[] row : danhSach) {
            model.addRow(new Object[]{
                row[0], row[1], row[2], row[3], row[4], row[6], new java.text.DecimalFormat("#,###").format(row[5])
            });
        }
    }
private void showChiTietPhieu(String idPhieu, String loaiPhieu) {
    JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(view), "Chi tiết phiếu " + idPhieu, true);
    dialog.setLayout(new BorderLayout());
    dialog.setSize(700, 500);

    DefaultTableModel chiTietModel = new DefaultTableModel(new String[]{"ID Sản phẩm", "Tên sản phẩm", "Size", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
    JTable chiTietTable = new JTable(chiTietModel);
    chiTietTable.setRowHeight(25);
    chiTietTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
    chiTietTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    chiTietTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    List<Object[]> chiTietList = nhapXuatKhoDAO.getChiTietPhieu(idPhieu, loaiPhieu);
    if (chiTietList.isEmpty()) {
        chiTietModel.addRow(new Object[]{"Không có dữ liệu", "", "", "", "", ""});
        System.out.println("Danh sách chi tiết rỗng cho idPhieu: " + idPhieu);
    } else {
        for (Object[] row : chiTietList) {
            String tenSanPham = (row[1] != null) ? (String) row[1] : "Chưa có tên";
            Integer size = (row[2] != null) ? (Integer) row[2] : 0;
            chiTietModel.addRow(new Object[]{
                row[0], tenSanPham, size, row[3], new java.text.DecimalFormat("#,###").format(row[4]), new java.text.DecimalFormat("#,###").format(row[5])
            });
        }
    }

    JScrollPane scrollPane = new JScrollPane(chiTietTable);
    dialog.add(scrollPane, BorderLayout.CENTER);

    JButton btnDong = new JButton("Đóng");
    btnDong.addActionListener(e -> dialog.dispose());
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(btnDong);
    dialog.add(buttonPanel, BorderLayout.SOUTH);

    dialog.setLocationRelativeTo(view);
    dialog.setVisible(true);
}

    
}