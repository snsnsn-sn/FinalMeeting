package dao.impl;

import dao.DBConnection.DBConn;
import dao.MeetingInfoDao;
import vo.MeetingInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ZhouJiashu
 * @date 2020-12-03 16:55
 */
public class MeetingInfoImpl implements MeetingInfoDao {

    Connection conn = null;
    PreparedStatement pre = null;

    int flag = 0; //标识数据库操作是否成功


    /**
     * 根据id查询会议的详细信息
     *
     * @param id 要查询信息的会议id
     * @return 信息对象
     */
    @Override
    public MeetingInfo getInfoByMeetingId(String id) {
        ResultSet rs = null;
        MeetingInfo info = null;
        String sql = "select * from minfo where mid=" + id;

        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String mid = rs.getString("mid");
                String hotelId = rs.getString("mhotel_id");
                String hotelName = rs.getString("mhotel_name");
                String brief = rs.getString("mbrief");
                String part = rs.getString("part");
                int useCar = Integer.parseInt(rs.getString("useCar"));
                info = new MeetingInfo(mid, hotelName, hotelName, useCar, brief, part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();//关闭数据库连接
        }
        return info;
    }

    @Override
    public boolean insert(String mid, String hotelName, String hotelId, int useCar, String brief, String part) {
        String sql = "insert into minfo(mid,mhotel_id,mhotel_name,useCar,mbrief,part) values (?,?,?,?,?,?)";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, mid);
            pre.setString(2, hotelId);
            pre.setString(3, hotelName);
            pre.setString(4, Integer.toString(useCar));
            pre.setString(5, brief);
            pre.setString(6, part);
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
    public boolean delete(String mid) {
        String sql = "delete from minfo where mid=?";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, mid);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }
}
