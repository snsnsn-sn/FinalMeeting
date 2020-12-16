package dao.impl;

import dao.DBConnection.DBConn;
import dao.PickUserDao;
import vo.PickUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PickUserImpl implements PickUserDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    PickUser pk = null;

    @Override
    public List<PickUser> findByUserId(String userId) {
        List<PickUser> list = new ArrayList<>();

        String sql = "select * from pickuser where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId1 = rs.getString(1);
                String driverId = rs.getString(2);
                String pickTime = rs.getString(3);
                String pickPlace = rs.getString(4);
                pk = new PickUser(userId1,driverId,pickTime,pickPlace);
                list.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<PickUser> findByDriverId(String driverId, int currentPage, int pageSize) {
        List<PickUser> list = new ArrayList<>();

        String sql = "select * from pickuser where driverId = ? limit ?,? order by userId";

        currentPage = (currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,driverId);
            pre.setInt(2,currentPage);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                String driverId1 = rs.getString(2);
                String pickTime = rs.getString(3);
                String pickPlace = rs.getString(4);
                pk = new PickUser(userId,driverId1,pickTime,pickPlace);
                list.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<PickUser> findByUserId(String userId, int currentPage, int pageSize) {
        List<PickUser> list = new ArrayList<>();

        String sql = "select * from pickuser where userId = ? limit ?,?";

        currentPage = (currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,currentPage);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId1 = rs.getString(1);
                String driverId = rs.getString(2);
                String pickTime = rs.getString(3);
                String pickPlace = rs.getString(4);
                pk = new PickUser(userId1,driverId,pickTime,pickPlace);
                list.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }


    @Override
    public List<PickUser> findAll(int currentPage, int pageSize) {
        List<PickUser> list = new ArrayList<>();

        String sql = "select * from pickuser limit ?,? order by userId";

        currentPage = (currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,currentPage);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                String driverId = rs.getString(2);
                String pickTime = rs.getString(3);
                String pickPlace = rs.getString(4);
                pk = new PickUser(userId,driverId,pickTime,pickPlace);
                list.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<PickUser> findAll() {
        List<PickUser> list = new ArrayList<>();

        String sql = "select * from pickuser order by userId";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                String driverId = rs.getString(2);
                String pickTime = rs.getString(3);
                String pickPlace = rs.getString(4);
                pk = new PickUser(userId,driverId,pickTime,pickPlace);
                list.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql="delete from pickuser where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }
    @Override
    public void deleteByDriverId(String driverId) {
        String sql="delete from pickuser where driverId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,driverId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void insert(String userId, String driverId,String pickTime,String pickPlace) {
        String sql="insert into pickuser values (?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,driverId);
            pre.setString(3,pickTime);
            pre.setString(4,pickPlace);
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
        String sql = "select count(*) from pickuser";
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
    public int countDriver(String driverId) {
        int rowCount = 0;
        String sql = "select count(*) from pickuser where driverId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,driverId);
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
    public int countUser(String userId) {
        int rowCount = 0;
        String sql = "select count(*) from pickuser where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
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
}
