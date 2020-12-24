package dao.impl;

import dao.DBConnection.DBConn;
import dao.UserDao;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    User u = null;

    @Override
    public List<String> getJoinMeetings(String userId) {
        List<String> list = new ArrayList<>();
        String sql = "select jm.meetingId from joinmeeting jm where jm.userId = ?";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(1);
                list.add(meetingId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        String sql = "select * from user order by userId";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                String password = rs.getString(2);
                String phone = rs.getString(3);
                String part = rs.getString(4);
                String name = rs.getString(5);
                u = new User(userId,password,phone,part,name);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<User> findAll(int pageId, int pageSize) {
        List<User> list = new ArrayList<>();

        String sql = "select * from user limit ?,? order by userId";

        pageId = (pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,pageId);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                String password = rs.getString(2);
                String phone = rs.getString(3);
                String part = rs.getString(4);
                String name = rs.getString(5);
                u = new User(userId,password,phone,part,name);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void insert(String userId, String password, String phone, String part,String name) {
        String sql="insert into user values (?,?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,password);
            pre.setString(3,phone);
            pre.setString(4,part);
            pre.setString(5,name);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql="delete from user where userId = ?";
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
    public void update(String userId, String userId1, String password, String phone, String part,String name) {
        String sql="update user set userId=?,password=?,phone=?,part=?,name = ? where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId1);
            pre.setString(2,password);
            pre.setString(3,phone);
            pre.setString(4,part);
            pre.setString (5,name);
            pre.setString(6,userId);
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
        String sql = "select count(*) from user";
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
    public List<User> findByUserId(String userId) {
        List<User> list = new ArrayList<>();
        String sql = "select * from user where userId = ?";

        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId1 = rs.getString(1);
                String password = rs.getString(2);
                String phone = rs.getString(3);
                String part = rs.getString(4);
                String name = rs.getString(5);
                u = new User(userId1,password,phone,part,name);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean check(String id, String password) {
        boolean flag = false;
        String sql = "select * from user where userId = ?";
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
