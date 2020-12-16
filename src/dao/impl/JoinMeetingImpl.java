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
    ResultSet rs = null;

    @Override
    public List<String> findByMeetingId(String meetingId) {
        ResultSet rs = null;
        JoinMeeting jm = null;
        List<String> list = new ArrayList<>();
        String sql = "select * from joinmeeting where meetingId = ? order by userId asc";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userId = rs.getString(1);
                list.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(String userId, String meetingId) {
        int flag=0;
        String sql = "insert into joinmeeting(userId,meetingId) values (?,?)";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.setString(2, meetingId);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    @Override
    public boolean deleteParticipant(String mid, String uid) {
        int flag=0;
        String sql = "delete from joinmeeting where meetingId =? and userId=?";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, mid);
            pre.setString(2, uid);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    JoinMeeting jm = null;

    @Override
    public List<String> findByUserId1(String userId) {
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
    public List<JoinMeeting> findAll(int pageId, int pageSize) {
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
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
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
    public void insert(String userId, String meetingId,String joinTime,String need) {
        String sql="insert into joinmeeting values (?,?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,meetingId);
            pre.setString(3,joinTime);
            pre.setString(4,need);
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
    public void update(String userId, String userId1,String meetingId,String joinTime,String need) {
        String sql="update joinmeeting set userId = ?,meetingId = ?,joinTime=?,need=? where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId1);
            pre.setString(2,meetingId);
            pre.setString(3,joinTime);
            pre.setString(4,need);
            pre.setString(5,userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally{
            DBConn.close();
        }
    }


    @Override
    public List<JoinMeeting> findByUserId(String userId) {
        List<JoinMeeting> list = new ArrayList<>();
        String sql = "select * from joinmeeting where userId = ? order by meetingId asc";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
                list.add(jm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<JoinMeeting> findByUserId(String userId, int pageId, int pageSize) {
        List<JoinMeeting> list = new ArrayList<>();

        String sql = "select * from joinmeeting where userId=? limit ?,? order by userId";

        pageId=(pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setInt(2,pageId);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
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
    public List<JoinMeeting> findByMeetingId(String meetingId, int pageId, int pageSize) {
        List<JoinMeeting> list = new ArrayList<>();

        String sql = "select * from joinmeeting where meetingId=? limit ?,? order by userId";

        pageId=(pageId-1)*pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            pre.setInt(2,pageId);
            pre.setInt(3,pageSize);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
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
    public List<JoinMeeting> findByMeetingId1(String meetingId) {
        List<JoinMeeting> list = new ArrayList<>();
        String sql = "select * from joinmeeting where meetingId = ? order by meetingId asc";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,meetingId);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
                list.add(jm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<JoinMeeting> findAll() {
        List<JoinMeeting> list = new ArrayList<>();

        String sql = "select * from joinmeeting order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String userid = rs.getString(1);
                String meetingid = rs.getString(2);
                String joinTime = rs.getString(3);
                String need = rs.getString(4);
                jm = new JoinMeeting(userid,meetingid,joinTime,need);
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
    public int count() {
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
    public int countMeeting(String userId) {
        int rowCount = 0;
        String sql = "select count(*) from joinmeeting where userId = ?";
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
    public int countUser(String meetingId) {
        int rowCount = 0;
        String sql = "select count(*) from joinmeeting where meetingId = ?";
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
