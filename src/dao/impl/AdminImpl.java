package dao.impl;

import dao.AdminDao;
import dao.DBConnection.DBConn;
import vo.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminImpl implements AdminDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    Admin admin = null;

    @Override
    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();

        String sql = "select * from admin";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                admin = new Admin(id,password,name);
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    //currentPage是当前页数，从1开始；pageSize是页面存放的数据数
    public List<Admin> findAll(int currentPage, int pageSize) {
        List<Admin> list = new ArrayList<>();

        String sql = "select * from admin limit ?,?";

        currentPage = (currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,currentPage);
            pre.setInt(2,pageSize);

            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                admin = new Admin(id,password,name);
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public void insert(String id, String password,String name) {
        String sql="insert into admin values (?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            pre.setString(2,password);
            pre.setString(3,name);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql="delete from admin where adminId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void update(String id,String id1,String password,String name) {
        String sql="update admin set adminId = ?,password = ?,name=? where adminId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id1);
            pre.setString(2,password);
            pre.setString(3,name);
            pre.setString(4,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public int count() {
        int rowCount = 0;
        String sql = "select count(*) from admin";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);

            rs = pre.executeQuery();
            if(rs.next())
            {
                rowCount=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return rowCount;
    }

    @Override
    public boolean check(String id,String password) {
        boolean flag = false;
        String sql = "select * from admin where adminId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            rs = pre.executeQuery();
            while(rs.next()){
                String password1 = rs.getString(2);
                if(password.equals(password1))
                    flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return flag;
    }
}
