package dao;

import vo.User;

import java.util.List;

public interface UserDao {
    //查全部用户的信息，无分页
    public List<User> findAll();

    //查全部用户的信息，分页
    public List<User> findAll(int pageId, int pageSize);

    //通过userId查，得到该用户信息
    public User findByUserId(String userId);

    //增
    public boolean insert(String userId, String password, String phone, String part);

    //增+name
    public boolean insert(String userId, String name, String password, String phone, String part);

    //删
    public boolean deleteByUserId(String userId);

    //改
    public boolean update(String userId, String password, String phone, String part);

    //改
    public boolean updateBasic(String userId, String name, String phone, String part);

    //改+name
    public boolean update(String userId, String name, String password, String phone, String part);

    public boolean updatePassword(String uid, String pw);

    //总记录数
    public int count();
}