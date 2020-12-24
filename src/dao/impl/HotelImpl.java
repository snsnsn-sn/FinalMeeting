package dao.impl;

import dao.DBConnection.DBConn;
import dao.HotelDao;
import vo.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelImpl implements HotelDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    Hotel hotel = null;

    @Override
    public List<Hotel> findAll() {
        List<Hotel> list = new ArrayList<>();

        String sql = "select * from hotel";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                String hotelName = rs.getString(3);
                String hotelPlace = rs.getString(4);
                hotel = new Hotel(id,password,hotelName,hotelPlace);
                list.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<Hotel> findAll(int pageId, int pageSize) {
        List<Hotel> list = new ArrayList<>();

        String sql = "select * from hotel limit ?,?";

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
                String hotelName = rs.getString(3);
                String hotelPlace = rs.getString(4);
                hotel = new Hotel(id,password,hotelName,hotelPlace);
                list.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void insert(String id, String password,String hotelName,String hotelPlace) {
        String sql="insert into hotel values (?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            pre.setString(2,password);
            pre.setString(3,hotelName);
            pre.setString(4,hotelPlace);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql="delete from hotel where hotelId = ?";
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
    public void update(String id, String id1, String password,String hotelName,String hotelPlace) {
        String sql="update hotel set hotelId = ?,password = ?,hotelName = ?,hotelPlace=? where hotelId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id1);
            pre.setString(2,password);
            pre.setString(3,hotelName);
            pre.setString(4,hotelPlace);
            pre.setString(5,id);
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
        String sql = "select count(*) from hotel";
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
    public String getHotelName(String hotelId){
        String hotelName = null;

        String sql = "select * from hotel where hotelId = ?";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,hotelId);
            rs = pre.executeQuery();
            while(rs.next()){
                hotelName = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return hotelName;
    }
    @Override
    public boolean check(String id, String password) {
        boolean flag = false;
        String sql = "select * from hotel where hotelId = ?";
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
