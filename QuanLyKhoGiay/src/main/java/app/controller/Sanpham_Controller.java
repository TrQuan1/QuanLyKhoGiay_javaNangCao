/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.PhanLoai;
import app.model.SanPham;
import app.DAO.SanPham_DAO;
import app.view.AddSanPham_View;
import app.view.Kho_View;
import app.view.NhapXuatKho_View;
import app.view.SanPham_View;
import app.view.ThongKeBaoCao_View;
import app.view.UpdateSanPham_View;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Sanpham_Controller {
    private SanPham_View mainForm;
    private AddSanPham_View addForm;
    private UpdateSanPham_View updateForm;
    private Kho_View khoForm;
    private SanPham_DAO sanPhamDAO;

    public Sanpham_Controller(SanPham_View mainForm) {
        this.mainForm = mainForm;
        this.addForm = new AddSanPham_View();
        this.updateForm = new UpdateSanPham_View();
        this.khoForm = new Kho_View();
        this.sanPhamDAO = new SanPham_DAO();
        listenActioner();
        listenAddFormActioner();
        listenUpdateFormActioner();
    }
    
    
//====================Sự kiện của giao diện chính===============================
    private void listenActioner(){
//Menu--------------------------------------------------------------------------
        mainForm.getMenuQLSanPham().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.getContentPane().removeAll();
                mainForm.add(mainForm.getMainPannel());
                mainForm.setJMenuBar(mainForm.getMenuBarr());
                mainForm.revalidate();//bố trí lại các thành phần
                mainForm.repaint();//cập nhật giao diện
            }
        });
        
        mainForm.getMenuQLNhapXuatKho().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.getContentPane().removeAll();
                mainForm.add(new NhapXuatKho_View());
                mainForm.setJMenuBar(mainForm.getMenuBarr());
                mainForm.revalidate();
                mainForm.repaint();
            }
        });
        
        mainForm.getMenuThongKeBaoCao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.getContentPane().removeAll();
                mainForm.add(new ThongKeBaoCao_View());
                mainForm.setJMenuBar(mainForm.getMenuBarr());
                mainForm.revalidate();
                mainForm.repaint();
            }
        });
        
        mainForm.getMenuQuanLyKho().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.getContentPane().removeAll();
                mainForm.add(khoForm.getKhoPanel());
                mainForm.setJMenuBar(mainForm.getMenuBarr());
                mainForm.revalidate();
                mainForm.repaint();
            }
        });
//End Menu----------------------------------------------------------------------    

//Các Button--------------------------------------------------------------------
        mainForm.getBtnThemSanPham().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addForm.setVisible(true);
            }
        });
        
        mainForm.getBtnSuaSanPham().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSanPhamToUpdateForm();
            }
        });
        
        mainForm.getBtnXoaSanPham().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPham();
            }
        });
        
        mainForm.getBtnTimKiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemSanPham();
            }
        });
        
        mainForm.getBtnLoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                locTheoGia();
            }
        });
    }
//End=========================================================================== 

    
//====================Sự kiện của giao diện Sửa sản phẩm========================
    private void listenUpdateFormActioner(){
        updateForm.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaSanPham();
            }
        });
        
        updateForm.getBtnThoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateForm.dispose();
            }
        });
        
        
        updateForm.getBtnChonAnh().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonAnhUpdateForm();
            }
        });
    }
//End===========================================================================    

    
//====================Sự kiện của giao diện Thêm sản phẩm=======================
    private void listenAddFormActioner(){
        
        addForm.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPham();
            }
        });
        
        
        addForm.getBtnThoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addForm.dispose();
            }
        });
        
        
        addForm.getBtnChonAnh().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonAnhAddForm();
            }
        });
    }
//End===========================================================================    

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Các phương thức
    public void getAllSanPham() {
        DefaultTableModel model = (DefaultTableModel) mainForm.getTblSanpham().getModel();//lấy mô hình từ view và ép kiểu để cho phép thao tác dữ liệu trên bảng
        model.setRowCount(0);// clear bảng
        List<SanPham> dsSanPham = sanPhamDAO.getAll();
        dsSanPham.sort(Comparator.comparing(SanPham::getId));
    
        for (SanPham sp : dsSanPham) {
            ImageIcon icon = null;
            if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
                File file = new File(sp.getHinhAnh());
                if (file.exists()) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(scaledImg);
                    } catch (IOException ex) {
                        System.out.println("Lỗi tải ảnh: " + sp.getHinhAnh());
                    }
                }else{
                    System.out.println("Không tìm thấy ảnh: " + sp.getHinhAnh());
                }
            }
            DecimalFormat df = new DecimalFormat("#,###");
            double giaNhap = sp.getGiaNhap();
            double giaBan = sp.getGiaBan();
            model.addRow(new Object[]{sp.getId(), sp.getTenSanPham(), sp.getSize(), sp.getMauSac(), sp.getMoTa(), icon, sp.getThuongHieu(), sp.getPhanLoai(), df.format(giaNhap), df.format(giaBan)});
        }
    }    
    
//Thêm sản phẩm-----------------------------------------------------------------
    private void themSanPham(){
        SanPham sp = new SanPham();
        String maSp = addForm.getTxtMaSp().getText();
        if (maSp.isEmpty()) {
            JOptionPane.showMessageDialog(addForm, "Mã sản phẩm không được để trống!");
            return;
        }
        sp.setId(maSp);
        sp.setTenSanPham(addForm.getTxtTenSp().getText());
        sp.setSize(Integer.parseInt(addForm.getCbbSize().getSelectedItem().toString().trim()));
        sp.setMauSac(addForm.getCbbMau().getSelectedItem().toString().trim());
        sp.setMoTa(addForm.getTxtMoTa().getText());
        sp.setHinhAnh(addForm.getImagePath());
        sp.setThuongHieu(addForm.getTxtThuongHieu().getText());
        sp.setGiaNhap(Double.parseDouble(addForm.getTxtGiaNhap().getText().replace(",", "").trim()));
        sp.setGiaBan(Double.parseDouble(addForm.getTxtGiaBan().getText().replace(",", "").trim()));
        int idPhanLoai = ((PhanLoai) addForm.getCbbPhanLoai().getSelectedItem()).getId();
        if (sanPhamDAO.addSanPham(sp, idPhanLoai)) {
            JOptionPane.showMessageDialog(addForm, "Thêm sản phẩm thành công!");
            clearForm();
            addForm.dispose();
            mainForm.getModel().setRowCount(0);
            getAllSanPham();
        } else {
            JOptionPane.showMessageDialog(addForm, "Thêm sản phẩm thất bại!");
        }
    }
//End---------------------------------------------------------------------------

//Sửa sản phẩm------------------------------------------------------------------    
    private void suaSanPham(){
        SanPham sp = new SanPham();
        sp.setId(updateForm.getTxtMaSp().getText());
        sp.setTenSanPham(updateForm.getTxtTenSp().getText());
        sp.setSize(Integer.parseInt(updateForm.getCbbSize().getSelectedItem().toString().trim()));
        sp.setMauSac(updateForm.getCbbMau().getSelectedItem().toString().trim());
        sp.setMoTa(updateForm.getTxtMoTa().getText());
        sp.setHinhAnh(updateForm.getImagePath());
        sp.setThuongHieu(updateForm.getTxtThuongHieu().getText());
        sp.setGiaNhap(Double.parseDouble(updateForm.getTxtGiaNhap().getText().replace(",", "").trim()));
        sp.setGiaBan(Double.parseDouble(updateForm.getTxtGiaBan().getText().replace(",", "").trim()));
        int idPhanLoai = ((PhanLoai) updateForm.getCbbPhanLoai().getSelectedItem()).getId();
        if (sanPhamDAO.updateSanPham(sp, idPhanLoai)) {
            JOptionPane.showMessageDialog(addForm, "Sửa sản phẩm thành công!");
            updateForm.dispose();
            mainForm.getModel().setRowCount(0);
            getAllSanPham();
        } else {
            JOptionPane.showMessageDialog(addForm, "Sửa sản phẩm thất bại!");
        }
    }
//End---------------------------------------------------------------------------    
    
//Xóa sản phẩm------------------------------------------------------------------    
    private void xoaSanPham() {
        int selectedRow = mainForm.getTblSanpham().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(mainForm, "Vui lòng chọn sản phẩm để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Giả sử cột 0 là ID sản phẩm
        String idProduct = mainForm.getTblSanpham().getValueAt(selectedRow, 0).toString();
        if (sanPhamDAO.deleteById(idProduct)) {
            JOptionPane.showMessageDialog(mainForm, "Xóa sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            getAllSanPham(); // Load lại danh sách
        } else {
            JOptionPane.showMessageDialog(mainForm, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
//End---------------------------------------------------------------------------

//Tìm kiếm sản phẩm-------------------------------------------------------------  
    private void timKiemSanPham(){
        String searchText = mainForm.getTxtTimKiem().getText().trim();
        List<SanPham> results = new ArrayList<>();
        
        if (searchText.isEmpty()) {
            getAllSanPham();
            return;
        }
        // Tìm kiếm theo text 
        try {
            int size = Integer.parseInt(searchText); // Thử parse thành size
            results.addAll(sanPhamDAO.searchBySize(size));
        } catch (NumberFormatException e1) {
            results.addAll(sanPhamDAO.searchByMaSp(searchText));
            results.addAll(sanPhamDAO.searchByTenSp(searchText));
            results.addAll(sanPhamDAO.searchByThuongHieu(searchText));
            results.addAll(sanPhamDAO.searchByPhanLoai(searchText));
            results.addAll(sanPhamDAO.searchByMau(searchText));
        }
    
        // Hiển thị kết quả
        DefaultTableModel model = (DefaultTableModel) mainForm.getTblSanpham().getModel();
        model.setRowCount(0);
        for (SanPham sp : results) {
            ImageIcon icon = null;
            if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
                File file = new File(sp.getHinhAnh());
                if (file.exists()) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(scaledImg);
                    } catch (IOException ex) {
                        System.out.println("Lỗi tải ảnh: " + sp.getHinhAnh());
                    }
                }
            }
            DecimalFormat df = new DecimalFormat("#,###");
            double giaNhap = sp.getGiaNhap();
            double giaBan = sp.getGiaBan();
            model.addRow(new Object[]{
                sp.getId(), sp.getTenSanPham(), sp.getSize(),
                sp.getMauSac(), sp.getMoTa(), icon, sp.getThuongHieu(),
                sp.getPhanLoai(), df.format(giaNhap), df.format(giaBan)
            });
        }
    }
//End---------------------------------------------------------------------------    
            
    //lấy dữ liệu từ bảng SP rồi gán vào các ô text trong UpdateSanPham_View        
    private void loadSanPhamToUpdateForm(){
        int selectedRow = mainForm.getTblSanpham().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(mainForm, "Vui lòng chọn sản phẩm để sửa!");
            return;
        }
        // Lấy dữ liệu từ hàng được chọn
        DefaultTableModel model = (DefaultTableModel) mainForm.getTblSanpham().getModel();
        String id = Objects.toString(model.getValueAt(selectedRow, 0), "");
        String tenSp = Objects.toString(model.getValueAt(selectedRow, 1), "");
        int size = (int) model.getValueAt(selectedRow, 2);
        String mauSac = Objects.toString(model.getValueAt(selectedRow, 3), "");
        String moTa = Objects.toString(model.getValueAt(selectedRow, 4), "");
        String hinhAnh = sanPhamDAO.getHinhAnhById(id); // Lấy từ DB
        String thuongHieu = Objects.toString(model.getValueAt(selectedRow, 6), "");
        String phanLoai = Objects.toString(model.getValueAt(selectedRow, 7), "");
        String giaNhap = Objects.toString(model.getValueAt(selectedRow, 8), "") ;
        String giaBan = Objects.toString(model.getValueAt(selectedRow, 9), "") ;

        
        // Điền dữ liệu vào UpdateSanPhamForm
        updateForm.getTxtMaSp().setText(id);
        updateForm.getTxtTenSp().setText(tenSp);
        updateForm.getCbbSize().setSelectedItem(String.valueOf(size));
        updateForm.getCbbMau().setSelectedItem(mauSac);
        updateForm.getTxtMoTa().setText(moTa);
        updateForm.getTxtThuongHieu().setText(thuongHieu);
        updateForm.getTxtGiaNhap().setText(String.valueOf(giaNhap));
        updateForm.getTxtGiaBan().setText(String.valueOf(giaBan));
        
        // Điền phân loại (cần tìm id_category tương ứng) Vì JComboBox<PhanLoai> chứa đối tượng, bạn phải duyệt từng PhanLoai để tìm cái có tenPhanLoai đúng rồi chọn.
        for (int i = 0; i < updateForm.getCbbPhanLoai().getItemCount(); i++) {
            PhanLoai pl = (PhanLoai) updateForm.getCbbPhanLoai().getItemAt(i);
            if (pl.getTenPhanLoai().equals(phanLoai)) {
                updateForm.getCbbPhanLoai().setSelectedIndex(i);
                break;
            }
        }
        // Hiển thị ảnh (nếu có)
        if (!hinhAnh.isEmpty()) {
            File file = new File(hinhAnh);
            if (file.exists()) {
                try {
                    BufferedImage img = ImageIO.read(file);
                    Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    updateForm.getLblImagePreview().setIcon(new ImageIcon(scaledImg));
                } catch (IOException ex) {
                    System.out.println("Lỗi tải ảnh: " + ex.getMessage());
                }
            }
        }
        updateForm.setImagePath(hinhAnh); // Lưu đường dẫn ảnh
        updateForm.setVisible(true);
    }
    
    private void chonAnhAddForm(){
        JFileChooser chonFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");
        chonFile.setFileFilter(filter);
        int result = chonFile.showOpenDialog(addForm);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chonFile.getSelectedFile();
            addForm.setImagePath(selectedFile.getAbsolutePath()); // Lưu imagePath

            try {
                BufferedImage img = ImageIO.read(selectedFile);
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                addForm.getLblImagePreview().setIcon(new ImageIcon(scaledImg));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addForm, "Lỗi khi tải ảnh: " + ex.getMessage());
            }
        }
    }
    
    private void chonAnhUpdateForm(){
        JFileChooser chonFile = new JFileChooser();//tạo cửa sổ chọn ảnh
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");//lọc đuôi file
        chonFile.setFileFilter(filter);
        int result = chonFile.showOpenDialog(updateForm);//Hiển thị của sổ chọn file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chonFile.getSelectedFile();//Lấy file mà người dùng đã chọn
            updateForm.setImagePath(selectedFile.getAbsolutePath()); // Lưu imagePath

            try {
                BufferedImage img = ImageIO.read(selectedFile);//đọc ảnh từ file
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                updateForm.getLblImagePreview().setIcon(new ImageIcon(scaledImg));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(updateForm, "Lỗi khi tải ảnh: " + ex.getMessage());
            }
        }
    }
    
    private void clearForm(){
        // Clear form
        addForm.getTxtMaSp().setText("");
        addForm.getTxtTenSp().setText("");
        addForm.getCbbSize().setSelectedIndex(0); // Reset về giá trị đầu tiên
        addForm.getCbbMau().setSelectedIndex(0); // Reset về giá trị đầu tiên
        addForm.getTxtMoTa().setText("");
        addForm.setImagePath(""); // Xóa đường dẫn ảnh
        addForm.getLblImagePreview().setIcon(null); // Xóa ảnh preview
        addForm.getTxtThuongHieu().setText("");
        addForm.getTxtGiaNhap().setText("");
        addForm.getTxtGiaBan().setText("");
        addForm.getCbbPhanLoai().setSelectedIndex(0); // Reset phân loại
    }
    
    private void locTheoGia() {
    DefaultTableModel model = (DefaultTableModel) mainForm.getTblSanpham().getModel();
    model.setRowCount(0);

    List<SanPham> results = new ArrayList<>();

    if (mainForm.getRadGiaNhap().isSelected() || mainForm.getRadGiaBan().isSelected()) {
        try {
            double minGia = Double.parseDouble(mainForm.getTxtTu().getText().trim());
            double maxGia = Double.parseDouble(mainForm.getTxtDen().getText().trim());
            
            if (minGia > maxGia) {
                JOptionPane.showMessageDialog(mainForm, "Giá từ phải nhỏ hơn giá đến!");
                return;
            }
            if (mainForm.getRadGiaNhap().isSelected()) {
                results.addAll(sanPhamDAO.searchByGiaNhap(minGia, maxGia));
            }
            if (mainForm.getRadGiaBan().isSelected()) {
                results.addAll(sanPhamDAO.searchByGiaBan(minGia, maxGia));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainForm, "Vui lòng nhập giá hợp lệ!");
            return;
        }
    } else {
        JOptionPane.showMessageDialog(mainForm, "Vui lòng chọn loại giá (Giá Nhập hoặc Giá Bán)!");
        return;
    }

    // Hiển thị kết quả
    System.out.println("Số kết quả lọc: " + results.size());
    if (results.isEmpty()) {
        JOptionPane.showMessageDialog(mainForm, "Không tìm thấy sản phẩm nào trong khoảng giá này!");
    } else {
        for (SanPham sp : results) {
            ImageIcon icon = null;
            if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
                File file = new File(sp.getHinhAnh());
                if (file.exists()) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(scaledImg);
                    } catch (IOException ex) {
                        System.out.println("Lỗi tải ảnh: " + sp.getHinhAnh());
                    }
                }
            }
            DecimalFormat df = new DecimalFormat("#,###");
            double giaNhap = sp.getGiaNhap();
            double giaBan = sp.getGiaBan();
            model.addRow(new Object[]{
                sp.getId(), sp.getTenSanPham(), sp.getSize(),
                sp.getMauSac(), sp.getMoTa(), icon, sp.getThuongHieu(),
                sp.getPhanLoai(), df.format(giaNhap), df.format(giaBan)
            });
            
        }
    }
}

}
