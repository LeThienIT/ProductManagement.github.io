package ql_sanpham.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ql_sanpham.connect.MyConnection;
import ql_sanpham.entity.Product;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */
public class Product_function implements Dao<Product> {

    private final String TABLE_NAME = "table_product";
    MyConnection myConnection = MyConnection.getInstance();

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + TABLE_NAME;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getInt("categoryid"),
                        rs.getString("productid"),
                        rs.getString("productname"),
                        rs.getInt("unitprice"),
                        rs.getInt("quantity"),
                        rs.getString("description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Product> get(int id) {
        Product product = new Product();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("id"),
                        rs.getInt("categoryid"),
                        rs.getString("productid"),
                        rs.getString("productname"),
                        rs.getInt("unitprice"),
                        rs.getInt("quantity"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(product);
    }

    // ghi đè thêm 
    public Optional<Product> get(String productId) {
        Product product = new Product();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE productId = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("id"),
                        rs.getInt("categoryid"),
                        rs.getString("productid"),
                        rs.getString("productname"),
                        rs.getInt("unitprice"),
                        rs.getInt("quantity"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(product);
    }

    @Override
    public int insert(Product t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO " + TABLE_NAME + "(categoryid, productid, productname, unitprice, quantity, description) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getCategoryid());
            ps.setString(2, t.getProductid());
            ps.setString(3, t.getName());
            ps.setFloat(4, t.getUnitPrice());
            ps.setInt(5, t.getQuantity());
            ps.setString(6, t.getDescription());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(Product t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE " + TABLE_NAME + " SET categoryid = ?, productid = ?, productname = ?, unitprice = ?, quantity = ?, description = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getCategoryid());
            ps.setString(2, t.getProductid());
            ps.setString(3, t.getName());
            ps.setFloat(4, t.getUnitPrice());
            ps.setInt(5, t.getQuantity());
            ps.setString(6, t.getDescription());
            ps.setInt(7, t.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                } else if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Product t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                } else if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // test chức năng của product
    public static void main(String[] args) {
        System.out.println("Tất cả dữ liệu");
        Product_function p = new Product_function();
        for (Product pr : p.getAll()) {
            System.out.println(pr);
        }
    }
}
