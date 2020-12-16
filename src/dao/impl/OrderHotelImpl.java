package dao.impl;

import dao.DBConnection.DBConn;
import dao.OrderHotelDao;
import vo.OrderHotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHotelImpl implements OrderHotelDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    OrderHotel oh = null;

    @Override
    public void deleteByIdAndTime(String userId, String time) {
        String sql = "delete from orderhotel where userId = ? and ordertime = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,time);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void updateState(String userId, String time,int state) {
        String sql="update orderhotel set state = ? where userId = ? and ordertime = ?";
        try{
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,state);
            pre.setString(3,time);
            pre.setString(2,userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public List<OrderHotel> findByUserId(String userId, int currentPage, int pageSize) {
        List<OrderHotel> list = new ArrayList<>();

        String sql = "select * from orderhotel where userId = ? limit ?,? order by people";

        currentPage = (currentPage - 1) * pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2, currentPage);
            pre.setInt(3, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<OrderHotel> findByUserId(String userId) {
        List<OrderHotel> list = new ArrayList<>();
        String sql = "select * from orderhotel where userId = ? order by people";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<OrderHotel> findAll() {
        List<OrderHotel> list = new ArrayList<>();

        String sql = "select * from orderhotel order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public List<OrderHotel> findAll(int currentPage, int pageSize) {
        List<OrderHotel> list = new ArrayList<>();

        String sql = "select * from orderhotel limit ?,? order by userId";

        currentPage = (currentPage - 1) * pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, currentPage);
            pre.setInt(2, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public List<OrderHotel> findByHotelName(int currentPage, int pageSize, String hotelName) {
        List<OrderHotel> list = new ArrayList<>();

        String sql = "select * from orderhotel where hotelName = ? limit ?,? order by userId";
        currentPage = (currentPage - 1) * pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,hotelName);
            pre.setInt(2, currentPage);
            pre.setInt(3, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName1 = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName1,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public List<OrderHotel> findByHotelName(String hotelName) {
        List<OrderHotel> list = new ArrayList<>();

        String sql = "select * from orderhotel where hotelName = ? order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,hotelName);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int people = rs.getInt(2);
                int state = rs.getInt(3);
                String hotelName1 = rs.getString(4);
                String orderTime = rs.getString(5);
                oh = new OrderHotel(userid, people, state,hotelName1,orderTime);
                list.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void insert(String userId, int people, int state,String hotelName,String orderTime) {
        String sql="insert into orderhotel values (?,?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,people);
            pre.setInt(3,state);
            pre.setString(4,hotelName);
            pre.setString(5,orderTime);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql = "delete from orderhotel where userId = ?";
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
    public void update(String userId, String userId1, int people, int state,String hotelName,String orderTime) {
        String sql="update orderhotel set userId = ?,people = ?,state = ?,hotelName=?,ordertime=? where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId1);
            pre.setInt(2,people);
            pre.setInt(3,state);
            pre.setString(4,hotelName);
            pre.setString(5,userId);
            pre.setString(6,orderTime);
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
        String sql = "select count(*) from orderhotel";
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
    public int countUser(String userId) {
        int rowCount = 0;
        String sql = "select count(*) from orderhotel where userId=?";
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

    @Override
    public int countHotel(String hotelName) {
        int rowCount = 0;
        String sql = "select count(*) from orderhotel where hotelName=?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,hotelName);
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
