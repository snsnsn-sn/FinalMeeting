package dao.impl;

import dao.DBConnection.DBConn;
import dao.AdminRespository;
import vo.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminImpl implements AdminRespository{
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs =null;
    Admin admin = null;

    /**
     * 根据页长获取第i页的用户列表
     * @param pageId 页码
     * @param pageSize 数量
     * @return 第i页的用户列表
     */

    @Override
    public List<Admin> findAll(int pageId, int pageSize){
        List<Admin> list = new ArrayList<>();

        String sql = "select * from Admin limit ?,?";

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
                admin = new Admin(id,password);
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
        return list;
    }

    /**
     * 获取系统全体用户对象的列表
     * @return 系统全部用户对象
     */

    @Override
    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();

        String sql = "select * from admin";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String password = rs.getString(2);
                admin = new Admin(id,password);
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
        return list;
    }

    /**
     *  添加管理员
     * @param id 用户id
     * @param password 密码
     */
    @Override
    public void insert(String id, String password) {
        String sql="insert into admin values (?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            pre.setString(2,password);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @Override
    public void deleteById(String id) {
        String sql="delete from admin where adminId = ?";
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

    /**
     * 更新用户密码
     * @param id 旧id
     * @param id1 新id
     * @param password 新密码
     */
    @Override
    public void update(String id, String id1, String password) {
        String sql="update admin set adminId = ?,password = ?,phone = ? where adminId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,id1);
            pre.setString(2,password);
            pre.setString(4,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally{
            DBConn.close();
        }
    }

    /**
     * @return 用户数量
     */
    @Override
    public int count() {
        int rowCount = 0;
        String sql = "select count(*) from admin";
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

    /**
     * 用户是否存在
     * @param id 用户id
     * @param password 用户密码
     * @return false为不存在，true为存在
     */
    @Override
    public boolean check(String id, String password) {
        boolean flag = false;
        String sql = "select * from admin where adminId = ?";
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
