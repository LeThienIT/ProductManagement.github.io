package ql_sanpham.connect;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */
public class MyConnection {

    // thông tin kết nối
    String hostName = "localhost";
    String dbName = "quanlisanpham";
    String userName = "root";
    String password = "123456";
    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
    Connection conn = null;

    // tạo theo mẫu Singleton
    private static final MyConnection instance = new MyConnection(); // khong cho ai truy cập

    private MyConnection() {
        // mục đích mất hàm tạo mặc định, luôn luôn có 1 thể hiện
    }

    // để lấy về conn cần 1 hàm getConnection
    public Connection getConnection() {
        if (conn == null) {
            return open();
        }
        return conn;
    }

    // hàm kết nối
    private Connection open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    // hàm đóng kết nối
    private void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm để lấy về instance
    public static MyConnection getInstance() {
        return instance;
        // mỗi lần return về getInstance sẽ return về 1 đối tượng
    }

    public static void main(String[] args) {
        Connection conn = MyConnection.getInstance().getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
