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

    int flag = 0; //标识数据库操作是否成功


    /**
     * 获取系统全体用户对象的列表
     *
     * @return 系统全部用户对象
     */
    @Override
    public List<User> findAll() {
        ResultSet rs = null;
        User u = null;
        List<User> list = new ArrayList<>();

        String sql = "select * from user";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String part = rs.getString("part");
                String name = rs.getString("name");
                u = new User(userId, name, password, phone, part);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    /**
     * 根据页长获取第i页的用户列表
     *
     * @param pageId
     * @param pageSize
     * @return
     */
    @Override
    public List<User> findAll(int pageId, int pageSize) {
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        User u = null;

        String sql = "select * from user limit ?,?";

        pageId = (pageId - 1) * pageSize;
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pageId);
            pre.setInt(2, pageSize);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String part = rs.getString("part");
                String name = rs.getString("name");
                u = new User(userId, name, password, phone, part);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return list;
    }

    /**
     * 新增用户
     *
     * @param userId
     * @param password
     * @param phone
     * @param part     部门
     */
    @Override
    public boolean insert(String userId, String password, String phone, String part) {
        return insert(userId, "", password, phone, part);
    }

    @Override
    public boolean insert(String userId, String name, String password, String phone, String part) {
        String sql = "insert into user values (?,?,?,?,?)";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.setString(2, password);
            pre.setString(3, phone);
            pre.setString(4, part);
            pre.setString(5, name);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    /**
     * 通过用户id删除用户
     *
     * @param userId
     */
    @Override
    public boolean deleteByUserId(String userId) {
        String sql = "delete from user where userId = ?";

        JoinMeetingImpl jm = new JoinMeetingImpl();
        jm.deleteByUserId(userId);

        MeetingImpl m = new MeetingImpl();
        m.deleteByUserId(userId);

        OrderCarImpl oc = new OrderCarImpl();
        oc.deleteByUserId(userId);

        OrderHotelImpl oh = new OrderHotelImpl();
        oh.deleteByUserId(userId);
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            flag = pre.executeUpdate();
            System.out.println("delete : affted Row=" + flag);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    /**
     * @param userId   要修改数据的用户id
     * @param password
     * @param phone
     * @param part
     */
    @Override
    public boolean update(String userId, String password, String phone, String part) {
        return update(userId, "", password, phone, part);
    }

    @Override
    public boolean updateBasic(String userId, String name, String phone, String part) {
        String sql = "update user set name=?,phone=?,part=? where userId = ?";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, phone);
            pre.setString(3, part);
            pre.setString(4, userId);
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
    public boolean updatePassword(String uid, String pw) {
        String sql = "update user set password=? where userId=?";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, pw);
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

    @Override
    public boolean update(String userId, String name, String password, String phone, String part) {
        String sql = "update user set password=?,phone=?,part=? name=? where userId = ?";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, phone);
            pre.setString(3, part);
            pre.setString(4, name);
            pre.setString(5, userId);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    /**
     * @return 用户数量
     */
    @Override
    public int count() {
        ResultSet rs = null;

        int rowCount = 0;
        String sql = "select count(*) from user";
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

    /**
     * 根据用户id返回用户对象
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(String userId) {
        ResultSet rs = null;
        User u = null;

        String sql = "select * from user where userId = ?";

        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String userId1 = rs.getString("userId");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String part = rs.getString("part");
                String name = rs.getString("name");
                u = new User(userId1, name, password, phone, part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }

        return u;
    }

//    /**   调用MeetingDao
//     * 根据用户id获取该用户参加的所有会议的会议id
//     * @param userId
//     * @return meetingId
//     */
//    @Override
//    public List<String> getJoinMeetings(String userId) {
//        List<String> list = new ArrayList<>();
//        String sql = "select jm.meetingId from joinmeeting jm where jm.userId = ?";
//        conn = DBConn.getConnection();
//        try {
//            pre = conn.prepareStatement(sql);
//            pre.setString(1,userId);
//            rs = pre.executeQuery();
//            while(rs.next()){
//                String meetingId = rs.getString(1);
//                list.add(meetingId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//            DBConn.close();
//        }
//        return list;
//    }
}