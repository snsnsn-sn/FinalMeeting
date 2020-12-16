package dao.impl;

import dao.DBConnection.DBConn;
import dao.MeetingDao;
import vo.Meeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeetingImpl implements MeetingDao {
    private int mid;
    private int flag = 0;
    Connection conn = null;
    PreparedStatement pre = null;

    //分配会议id
    public String getMid() {
        List<Meeting> meetings = findAll();
        meetings.sort(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return Integer.parseInt(o1.getMeetingId()) - Integer.parseInt(o2.getMeetingId());
            }
        });

        mid = Integer.parseInt(meetings.get(meetings.size() - 1).getMeetingId()) + 1;
        return Integer.toString(mid);
    }

    @Override
    public List<Meeting> findByMeetingId(String meetingId, int pageId, int pageSize) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where meetingId = ? limit ?,? order by meetingId";
        pageId = (pageId - 1) * pageSize;
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            pre.setInt(2, pageId);
            pre.setInt(3, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId1 = rs.getString(1);
                String userId = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId1, userId, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Meeting> findByMeetingId(String meetingId) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where meetingId = ? order by meetingId";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId1 = rs.getString(1);
                String userId = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId1, userId, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<Meeting> getMeetingByMeetingName(String mname) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where meetingName = ? order by meetingId";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, mname);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId, userId1, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<Meeting> findByUserId(String userId, int pageId, int pageSize) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where userId = ? limit ?,? order by meetingId";
        pageId = (pageId - 1) * pageSize;
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.setInt(2, pageId);
            pre.setInt(3, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId, userId1, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<Meeting> findByUserId(String userId) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();
        String sql = "select * from meeting where userId = ? order by meetingId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId, userId1, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return list;
    }

    @Override
    public List<Meeting> findAll(int pageId, int pageSize) {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();

        String sql = "select * from meeting limit ?,? order by userId";

        pageId = (pageId - 1) * pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pageId);
            pre.setInt(2, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId = rs.getString(1);
                String userId1 = rs.getString(2);
                String place = rs.getString(3);
                int peopleCount = rs.getInt(4);
                String time = rs.getString(5);
                String meetingName = rs.getString(6);
                int state = rs.getInt(7);
                m = new Meeting(meetingId, userId1, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public List<Meeting> findAll() {
        ResultSet rs = null;
        Meeting m = null;
        List<Meeting> list = new ArrayList<>();

        String sql = "select * from meeting order by userId";

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String meetingId = rs.getString("meetingId");
                String userId1 = rs.getString("userId");
                String place = rs.getString("place");
                int peopleCount = rs.getInt("peopleCount");
                String time = rs.getString("time");
                String meetingName = rs.getString("meetingName");
                int state = rs.getInt("state");
                m = new Meeting(meetingId, userId1, place, peopleCount, time, meetingName, state);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    @Override
    public boolean insert(String meetingId, String userId, String place, int peopleCount, String time, String meetingName, int state) {
        String sql = "insert into meeting values (?,?,?,?,?,?,?)";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            pre.setString(2, userId);
            pre.setString(3, place);
            pre.setInt(4, peopleCount);
            pre.setString(5, time);
            pre.setString(6, meetingName);
            pre.setInt(7, state);
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
    public boolean insert(String name, String uid, String part, String time, String place) {
        String id = getMid();
        if (insert(id, uid, place, 0, time, name, 0)) {
            MeetingInfoImpl meetingInfo = new MeetingInfoImpl();
            meetingInfo.insert(id, "未选择酒店", "", 0, "这里空空的什么也没有", part);
            return true;
        }
        return false;
    }

    @Override
    public void deleteByMeetingId(String meetingId) {
        String sql = "delete from meeting where meetingId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
    }

    @Override
    public void update(String meetingId, String meetingId1, String userId, String place, int peopleCount, String time, String meetingName, int state) {
        String sql = "update meeting set meetingId=?,userId=?,place=?,peopleCount=?,time=?,meetingName=?,state=? where meetingId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId1);
            pre.setString(2, userId);
            pre.setString(3, place);
            pre.setInt(4, peopleCount);
            pre.setString(5, time);
            pre.setString(6, meetingName);
            pre.setInt(7, state);
            pre.setString(8, meetingId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
    }

    @Override
    public boolean update(String meetingId, String userId, String place, int peopleCount, String time, String meetingName, int state) {
        int flag = 0;
        String sql = "update meeting set userId=?,place=?,peopleCount=?,time=?,meetingName=?,state=? where meetingId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.setString(2, place);
            pre.setInt(3, peopleCount);
            pre.setString(4, time);
            pre.setString(5, meetingName);
            pre.setInt(6, state);
            pre.setString(7, meetingId);
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
    public int count() {
        ResultSet rs = null;
        int rowCount = 0;
        String sql = "select count(*) from meeting";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        ResultSet rs = null;
        int rowCount = 0;
        String sql = "select count(*) from meeting where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            rs = pre.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        ResultSet rs = null;
        int rowCount = 0;
        String sql = "select count(*) from meeting where meetingId=?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, meetingId);
            rs = pre.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
        return rowCount;
    }

    @Override
    public void deleteByUserId(String userId) {
        String sql = "delete from meeting where userId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }
    }
}
