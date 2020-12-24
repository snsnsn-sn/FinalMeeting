package dao.impl;

import dao.DBConnection.DBConn;
import dao.MeetingDao;
import vo.Meeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingImpl implements MeetingDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    Meeting m = null;

    @Override
    public List<Meeting> findByMeetingId(String meetingId, int pageId, int pageSize) {
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where meetingId = ? limit ?,? order by meetingId";
        pageId = (pageId-1)*pageSize;
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            pre.setInt(2,pageId);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId1 = rs.getString(1);
                String userId = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId1,userId,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Meeting> findByMeetingId(String meetingId) {
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where meetingId = ? order by meetingId";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId1 = rs.getString(1);
                String userId = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId1,userId,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Meeting> findByUserId(String userId, int pageId, int pageSize){
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where userId = ? limit ?,? order by meetingId";
        pageId = (pageId-1)*pageSize;
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,pageId);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId,userId1,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Meeting> findByUserId(String userId){
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where userId = ? order by meetingId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId,userId1,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Meeting> findAll(int pageId, int pageSize) {
        List<Meeting> list = new ArrayList<>();

        String sql = "select * from meeting limit ?,? order by userId";

        pageId = (pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1,pageId);
            pre.setInt(2,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId,userId1,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<Meeting> findAll() {
        List<Meeting> list = new ArrayList<>();

        String sql = "select * from meeting order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId,userId1,place,peopleCount,time,meetingName,state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();//关闭数据库连接
        }
        return list;
    }
    @Override
    public void insert(String meetingId, String userId, String place, int peopleCount, String time,String meetingName,int state) {
        String sql="insert into meeting values (?,?,?,?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            pre.setString(2,userId);
            pre.setString(3,place);
            pre.setInt(4,peopleCount);
            pre.setString(5,time);
            pre.setString(6,meetingName);
            pre.setInt(7,state);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteByMeetingId(String meetingId) {
        String sql="delete from meeting where meetingId = ?";
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
    public void update(String meetingId, String meetingId1, String userId, String place, int peopleCount, String time,String meetingName,int state) {
        String sql="update meeting set meetingId=?,userId=?,place=?,peopleCount=?,time=?,meetingName=?,state=? where meetingId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId1);
            pre.setString(2,userId);
            pre.setString(3,place);
            pre.setInt(4,peopleCount);
            pre.setString(5,time);
            pre.setString(6,meetingName);
            pre.setInt(7,state);
            pre.setString(8,meetingId);
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
        String sql = "select count(*) from meeting";
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
        String sql = "select count(*) from meeting where userId = ?";
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
    public int countMeeting(String meetingId) {
        int rowCount = 0;
        String sql = "select count(*) from meeting where meetingId=?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
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
