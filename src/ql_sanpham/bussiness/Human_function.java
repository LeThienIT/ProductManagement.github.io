
package ql_sanpham.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ql_sanpham.connect.MyConnection;
import ql_sanpham.entity.Human;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */

public class Human_function implements Dao<Human>{
    private final String TABLE_NAME = "table_human";
    MyConnection myConnection = MyConnection.getInstance();

    @Override
    public List<Human> getAll() {
        List<Human> listHuman = new ArrayList<>();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String sql = "SELECT * FROM "+TABLE_NAME;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                listHuman.add(new Human(rs.getInt("id")
//                        , rs.getInt("idEventHuman")
                        , rs.getString("name")
                        , rs.getString("cmnd")
                        , rs.getString("address")
                        , rs.getString("telephone")
                        , rs.getString("email")
                        , rs.getString("country")
                        , rs.getString("job")
                        , rs.getString("workplace")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHuman;
    }

    @Override
    public Optional<Human> get(int id) {
        Human human = new Human();
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                human = new Human(rs.getInt("id")
                        , rs.getString("name")
                        , rs.getString("cmnd")
                        , rs.getString("address")
                        , rs.getString("telephone")
                        , rs.getString("email")
                        , rs.getString("country")
                        , rs.getString("job")
                        , rs.getString("workplace"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(human);
    }

    @Override
    public int insert(Human t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO " + TABLE_NAME + "(name, cmnd, address, telephone, email, country, job, workplace) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getName());
            ps.setString(2, t.getCmnd());
            ps.setString(3, t.getAddress());
            ps.setString(4, t.getTelephone());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getCountry());
            ps.setString(7, t.getJob());
            ps.setString(8, t.getWorkplace());
            if(ps.executeUpdate() > 0){
                rs = ps.getGeneratedKeys();
                while(rs.next()){
                    return rs.getInt(1);
                }
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(Human t) {
         Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE " + TABLE_NAME + " SET name = ?, cmnd = ?, address = ?, telephone = ?, email = ?, country = ?, job = ?, workplace = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getName());
            ps.setString(2, t.getCmnd());
            ps.setString(3, t.getAddress());
            ps.setString(4, t.getTelephone());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getCountry());
            ps.setString(7, t.getJob());
            ps.setString(8, t.getWorkplace());
            ps.setInt(9, t.getIdHuman());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(ps != null){
                    ps.close();
                }else if(rs != null){
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Human t) {
        Connection conn = myConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getIdHuman());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null){
                    rs.close();
                }else if(ps != null){
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    // test chức năng của human
     public static void main(String[] args) {
        System.out.println("Tất cả dữ liệu");
        Human_function human_function = new Human_function();
        for (Human human : human_function.getAll()) {
            System.out.println(human);
        }
    }
   
}
