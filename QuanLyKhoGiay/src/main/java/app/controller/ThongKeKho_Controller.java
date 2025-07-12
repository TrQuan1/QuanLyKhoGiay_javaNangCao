package app.controller;

import app.model.Kho;
import app.DAO.Kho_DAO;
import app.DAO.ThongKeKho_DAO;
import app.view.ThongKeKho_View;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ThongKeKho_Controller {
    private ThongKeKho_View view;
    private ThongKeKho_DAO dao = new ThongKeKho_DAO();
    private Kho_DAO khoDAO = new Kho_DAO();

    public ThongKeKho_Controller(ThongKeKho_View view) {
        this.view = view;
        listenActioner();
    }
    //sự kiện nút bấm
    private void listenActioner() {
        view.getBtnCapNhat().addActionListener(e -> capNhatKho());
        view.getBtnIn().addActionListener(e -> inBaoCao());
    }
    
    //lấy tên kho cho ra JComboBox
    public void getAllKho(DefaultComboBoxModel modelKho){
        List<Kho> dsKho = khoDAO.getAll();
        dsKho.sort(Comparator.comparing(Kho::getId));
        
        for(Kho kho : dsKho){
            modelKho.addElement(kho);
        }
    }
    
    private void capNhatKho() {
        Kho selectedKho = (Kho) view.getComboKho().getSelectedItem();
        if (selectedKho == null) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn kho!");
            return;
        }

        String idKho = selectedKho.getId();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID Kho", "ID Sản phẩm", "Tên sản phẩm", "Size", "Số lượng tồn"});
        List<Object[]> data = dao.getTonKhoTheoKho(idKho);
        for (Object[] row : data) {
            model.addRow(row);
        }
        view.getTable().setModel(model);
    }

    private void inBaoCao() {
        try {
            boolean complete = view.getTable().print();
            if (complete) {
                JOptionPane.showMessageDialog(null, "In thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "In bị hủy!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi in: " + e.getMessage());
        }
    }


}