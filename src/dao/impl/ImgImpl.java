package dao.impl;

import dao.DBConnection.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ZhouJiashu
 * @date 2020-12-24 17:51
 */
public class ImgImpl {
    Connection conn = null;
    PreparedStatement pre = null;
    int flag;

    //获取会议图片地址
    public String getImgPath(String mid) {
        String sql = "select path from img where meetingId=?";
        ResultSet rs = null;
        String path = "/Final/default.jpg";
        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, mid);
            rs = pre.executeQuery();
            while (rs.next()) {
                path = rs.getString("path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close();
        }

        return path;
    }

    //上传会议图片地址
    public boolean uploadImg(String mid, String path) {
        String sql = "insert into img(meetingId,path) values (?,?)";
        try {
            flag = 0;
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, mid);
            pre.setString(2, path);
            flag = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConn.close();
            return flag == 0 ? false : true;
        }
    }

    //删除会议图片地址
    public boolean deleteImg(String mid) {
        String sql = "delete from img where meetingId = ?";
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
