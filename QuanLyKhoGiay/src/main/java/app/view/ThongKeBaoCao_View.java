package app.view;



import app.controller.ThongKeDoanhThu_Controller;
import app.controller.ThongKeKho_Controller;
import javax.swing.*;

public class ThongKeBaoCao_View extends JPanel{
    public ThongKeBaoCao_View(){
        JTabbedPane tabbedPane = new JTabbedPane();

        ThongKeDoanhThu_View doanhthuView = new ThongKeDoanhThu_View();
        ThongKeDoanhThu_Controller doanhThuController = new ThongKeDoanhThu_Controller(doanhthuView);
        
        ThongKeKho_View sanphamView = new ThongKeKho_View();
        ThongKeKho_Controller khoController = new ThongKeKho_Controller(sanphamView);


        tabbedPane.addTab("Doanh thu", doanhthuView);
        tabbedPane.addTab("Kho hàng", sanphamView);

        this.add(tabbedPane);
    }
}
