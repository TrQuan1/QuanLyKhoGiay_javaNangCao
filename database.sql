create database luyentapjava;
use luyentapjava;
create table products(
	id_product varchar(255) primary key,
	name_product varchar(255),
	size int,
    color_product varchar(255),
	description text,
	image_url varchar(255), -- url image
	brand varchar(255),
	giaNhap DOUBLE,
	giaBan DOUBLE
);

CREATE TABLE categories (
    id_category INT PRIMARY KEY AUTO_INCREMENT,
    name_category VARCHAR(100),
    description TEXT
);

CREATE TABLE category_product (
    id_product varchar(255),
    id_category INT,
    PRIMARY KEY (id_product, id_category),
    FOREIGN KEY (id_product) REFERENCES products(id_product),
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('staff', 'manager') NOT NULL DEFAULT 'staff',
    can_manage BOOLEAN DEFAULT FALSE,
    full_name VARCHAR(100),
    phone VARCHAR(20),
    address TEXT
);

CREATE TABLE kho (
	id_kho VARCHAR(255) PRIMARY KEY,
	ten_kho VARCHAR(255)
);

CREATE TABLE products_kho (
	id_product VARCHAR(255),
	id_kho VARCHAR(255),
	so_luong INT DEFAULT 0,
	PRIMARY KEY (id_product, id_kho),
	FOREIGN KEY (id_product) REFERENCES products(id_product),
	FOREIGN KEY (id_kho) REFERENCES kho(id_kho)
);

CREATE TABLE phieu_nhap (
	id_phieu_nhap VARCHAR(255) PRIMARY KEY,
	nguoi_lap VARCHAR(255),
	ngay_lap DATE,
	id_kho VARCHAR(255),
	ghi_chu TEXT,
	FOREIGN KEY (id_kho) REFERENCES kho(id_kho)
);

CREATE TABLE chitiet_phieu_nhap (
	id_ctpn INT AUTO_INCREMENT PRIMARY KEY,
	id_phieu_nhap VARCHAR(255),
	id_product VARCHAR(255),
	so_luong INT,
	don_gia DOUBLE,
	FOREIGN KEY (id_phieu_nhap) REFERENCES phieu_nhap(id_phieu_nhap),
	FOREIGN KEY (id_product) REFERENCES products(id_product)
);

CREATE TABLE phieu_xuat (
	id_phieu_xuat VARCHAR(255) PRIMARY KEY,
	nguoi_lap VARCHAR(255),
	ngay_lap DATE,
	id_kho VARCHAR(255),
	ly_do TEXT,
	FOREIGN KEY (id_kho) REFERENCES kho(id_kho)
);

CREATE TABLE chitiet_phieu_xuat (
	id_ctpx INT AUTO_INCREMENT PRIMARY KEY,
	id_phieu_xuat VARCHAR(255),
	id_product VARCHAR(255),
	so_luong INT,
	don_gia DOUBLE,
	FOREIGN KEY (id_phieu_xuat) REFERENCES phieu_xuat(id_phieu_xuat),
	FOREIGN KEY (id_product) REFERENCES products(id_product)
);

INSERT INTO users (username, email, password, role, can_manage, full_name, phone, address)
VALUES (
    'admin',
    'vuminhduc27804@gmail.com',
    'admin123',
    'manager',
    TRUE,
    'Admin User',
    '0123456789',
    'Hanoi, Vietnam'
);

INSERT INTO categories (name_category, description) VALUES
('Giày chạy bộ', 'Giày thể thao, thường dùng cho hoạt động hàng ngày hoặc thể dục'),
('Giày bóng đá', 'Giày bóng đá chất lượng, thường dùng cho nhưng hoạt động trên sân cỏ, sân cỏ nhân tạo'),
('Giày bóng chuyền', 'Giày bóng chuyền đẳng cấp');

INSERT INTO kho VALUES 
('KHO_A', 'Kho Hà Nội'), 
('KHO_B', 'Kho Đà Nẵng'), 
('KHO_C', 'Kho TP.HCM');


