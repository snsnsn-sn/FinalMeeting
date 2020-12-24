package dao.impl;

import dao.DBConnection.DBConn;
import dao.OrderCarDao;
import vo.OrderCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderCarImpl implements OrderCarDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    OrderCar oc = null;

    @Override
    public List<OrderCar> findByUserId(String userId) {
        List<OrderCar> list = new ArrayList<>();
        String sql = "select * from ordercar where userId = ? order by people";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                String place = rs.getString(3);
                int state = rs.getInt(4);
                String deadline = rs.getString(5);
                oc = new OrderCar(userid, people, place, state, deadline);
                list.add(oc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<OrderCar> findByUserId(String userId, int currentPage, int pageSize) {
        List<OrderCar> list = new ArrayList<>();

        String sql = "select * from ordercar where userId = ? limit ?,? order by userId";

        currentPage=(currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,currentPage);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                String place = rs.getString(3);
                int state = rs.getInt(4);
                String deadline = rs.getString(5);
                oc = new OrderCar(userid,people,place,state,deadline);
                list.add(oc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public List<OrderCar> findAll() {
        List<OrderCar> list = new ArrayList<>();

        String sql = "select * from ordercar order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                String place = rs.getString(3);
                int state = rs.getInt(4);
                String deadline = rs.getString(5);
                oc = new OrderCar(userid,people,place,state,deadline);
                list.add(oc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public List<OrderCar> findAll(int currentPage, int pageSize) {
        List<OrderCar> list = new ArrayList<>();

        String sql = "select * from ordercar limit ?,? order by userId";

        currentPage=(currentPage-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,currentPage);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                String place = rs.getString(3);
                int state = rs.getInt(4);
                String deadline = rs.getString(5);
                oc = new OrderCar(userid,people,place,state,deadline);
                list.add(oc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void insert(String userId, int people, String place, int state, String deadline) {
        String sql="insert into ordercar values (?,?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,people);
            pre.setString(3,place);
            pre.setInt(4,state);
            pre.setString(5,deadline);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql = "delete from ordercar where userId = ?";
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
    public void update(String userId, String userId1, int people, String place, int state, String deadline) {
        String sql="update ordercar set userId = ?,people = ?,place = ?,state = ?,deadline = ? where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId1);
            pre.setInt(2,people);
            pre.setString(3,place);
            pre.setInt(4,state);
            pre.setString(5,deadline);
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
        String sql = "select count(*) from ordercar";
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
    public int countUser(String userId){
        int rowCount = 0;
        String sql = "select count(*) from ordercar where userId = ?";
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
