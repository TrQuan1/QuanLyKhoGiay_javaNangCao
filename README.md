# QuanLyKhoGiay
Hướng dẫn trong file README

## Chức năng chính
- Quản lý sản phẩm
- Quản ly kho
- Nhập/Xuất kho
- Phân quyền người dùng (admin, nhân viên)
- Báo cáo tồn kho

## Cài đặt để sử dụng
- Về cơ sở dữ liệu sử dụng MySql WorkBench để tạo database và dữ liệu.
- Để kết nối được với database thì trong file MyConnection sửa:
  

  public MyConnection() {
  
        host = "localhost";
        user = "root";
        pass = "Your_pw";//>>>>>>>>>Thay dổi chỗ này thành mật khẩu SQL WorkBench của bạn<<<<<<<<<<
        url = "jdbc:mysql://localhost:3306/luyentapjava?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    }

- Trong file Login_View chỉnh lại đường dẫn ảnh để cho nó hiển thị logo ở màn hình Login.
