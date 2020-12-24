package dao.impl;

import dao.CarTeamDao;
import dao.DBConnection.DBConn;
import vo.Admin;
import vo.CarTeam;
import vo.OrderHotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTeamImpl implements CarTeamDao {
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;
    OrderHotel oh = null;
    CarTeam c;

    @Override
    public void updatePassword(String id,String password) {
        String sql="update carteam set password = ? where teamId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,password);
            pre.setString(2,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql="delete from carteam where teamId = ?";
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
    public CarTeam findById(String id) {
        String sql = "select * from carteam where teamId = ?";

        conn = DBConn.getConnection();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1,id);
            rs = pre.executeQuery();
            while(rs.next()){
                String teamId = rs.getString("teamId");
                String name = rs.getString("teamName");
                String password=rs.getString("password");
                c = new CarTeam(teamId,password,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConn.close();
        }
        return c;
    }

    @Override
    public void insert(String teamId, String password,String teamName) {
        String sql="insert into carteam values (?,?,?)";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,teamId);
            pre.setString(2,password);
            pre.setString(3,teamName);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }

    @Override
    public boolean check(String teamId, String password) {
        boolean flag = false;
        String sql = "select * from carteam where teamId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,teamId);
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

    @Override
    public void update(String teamId, String teamId1, String password, String teamName) {
        String sql="update carteam set teamId = ?,password = ?,teamName=? where teamId = ?";
        try {
            conn = DBConn.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,teamId1);
            pre.setString(2,password);
            pre.setString(3,teamName);
            pre.setString(4,teamId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBConn.close();
        }
    }
}
