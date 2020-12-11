package dao.impl;

import dao.DBConnection.DBConn;
import dao.JoinMeetingDao;
import vo.JoinMeeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinMeetingImpl implements JoinMeetingDao {
    Connection conn = null;
    PreparedStatement pre = null;


    @Override
    public List<JoinMeeting> findAll(int pageId, int pageSize) {
        ResultSet rs = null;
        JoinMeeting jm = null;
        List<JoinMeeting> list = new ArrayList<>();

        String sql = "select * from joinmeeting limit ?,? order by userId";

        pageId=(pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,pageId);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                jm = new JoinMeeting(userid,meetingid);
                list.add(jm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public void insert(String userId, String meetingId) {
        String sql="insert into joinmeeting values (?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,meetingId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql="delete from joinmeeting where userId = ?";
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
    public void deleteByMeetingId(String meetingId) {
        String sql="delete from joinmeeting where meetingId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void update(String userId, String userId1,String meetingId) {
        String sql="update joinmeeting set userId = ?,meetingId = ? where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId1);
            pre.setString(2,meetingId);
            pre.setString(3,userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally{
            DBConn.close();
        }
    }

    @Override
    public int count() {
        ResultSet rs = null;
        JoinMeeting jm = null;
        int rowCount = 0;
        String sql = "select count(*) from joinmeeting";
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
    public List<String> findByUserId(String userId) {
        ResultSet rs = null;
        JoinMeeting jm = null;
        List<String> list = new ArrayList<>();
        String sql = "select * from joinmeeting where userId = ? order by meetingId asc";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(2);
                list.add(meetingId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> findByMeetingId(String meetingId) {
        ResultSet rs = null;
        JoinMeeting jm = null;
        List<String> list = new ArrayList<>();
        String sql = "select * from joinmeeting where meetingId = ? order by userId asc";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userId = rs.getString(1);
                list.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<JoinMeeting> findAll() {
        ResultSet rs = null;
        JoinMeeting jm = null;
        List<JoinMeeting> list = new ArrayList<>();

        String sql = "select * from joinmeeting order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                jm = new JoinMeeting(userid,meetingid);
                list.add(jm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }


}