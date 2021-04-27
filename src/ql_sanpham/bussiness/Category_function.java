package ql_sanpham.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ql_sanpham.connect.MyConnection;
import ql_sanpham.entity.Category;
//clutch bucu
/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */
public class Category_function implements Dao<Category> {

    private final String TABLE_NAME = "table_category";
    MyConnection myConnection = MyConnection.getInstance();

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM " + TABLE_NAME;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(); // trả về 1 resultset 
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Category> get(int id) {
        Category category = new Category();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery(); // trả về 1 resultset 
            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(category);
    }

    @Override
    public int insert(Category t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO " + TABLE_NAME + "(name, description) values(?, ?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
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
    public boolean update(Category t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE " + TABLE_NAME + " SET name = ?, description = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getId());
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

    @Override
    public boolean delete(Category t) {
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

    // test chức năng
    public static void main(String[] args) {
        Category_function ca = new Category_function();
        System.out.println("Tất cả dữ liệu:");
        for (Category c : ca.getAll()) {
            System.out.println(c);
        }

        System.out.println("Test insert");
        Category c1 = new Category(0, "Bóng đèn hiện đại", "Mô tả bóng đèn");
        System.out.println(ca.insert(c1));
    }
}
