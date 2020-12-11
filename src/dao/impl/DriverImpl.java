package dao.impl;

import dao.DBConnection.DBConn;
import dao.DriverRespository;
import vo.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DriverImpl implements DriverRespository {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    Driver driver = null;

    @Override
    public List<Driver> findAll() {
        List<Driver> list = new ArrayList<>();

        String sql = "select * from driver";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                String phone = rs.getString(3);
                driver = new Driver(id,password,phone);
                list.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<Driver> findAll(int pageId, int pageSize) {
        List<Driver> list = new ArrayList<>();

        String sql = "select * from driver limit ?,?";

        pageId=(pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,pageId);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                String phone = rs.getString(3);
                driver = new Driver(id,password,phone);
                list.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
        return list;
    }

    @Override
    public void insert(String id, String password, String phone) {
        String sql="insert into driver values (?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            pre.setString(2,password);
            pre.setString(3,phone);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql="delete from driver where driverId = ?";
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
    public void update(String id, String id1, String password, String phone) {
        String sql="update driver set driverId = ?,password = ?,phone = ? where driverId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id1);
            pre.setString(2,password);
            pre.setString(3,phone);
            pre.setString(4,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally{
            DBConn.close();
        }
    }

    @Override
    public int count() {
        int rowCount = 0;
        String sql = "select count(*) from driver";
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
    public boolean check(String id, String password) {
        boolean flag = false;
        String sql = "select * from driver where driverId = ?";
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