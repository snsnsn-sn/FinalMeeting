package dao;

import vo.User;

import java.util.List;

public interface UserDao {
    //查全部用户的信息，无分页
    public List<User> findAll();
    //查全部用户的信息，分页
    public List<User> findAll(int pageId, int pageSize);
    //通过userId查，得到该用户信息
    public List<User> findByUserId(String userId);

    //通过userId查，得到该用户信息
    public User findByUserId1(String userId);

    //查该用户参加的会议，返回的是 会议号 集合，需要通过会议号去找相应的会议
    public List<String> getJoinMeetings(String userId);

    //增
    public void insert(String userId, String password, String phone, String part, String name);
    //删
    public void deleteByUserId(String userId);

    //增+name
    public boolean insert1(String userId, String password, String phone, String part, String name);
    //删
    public boolean deleteByUserId1(String userId);

    //改
    public void update(String userId, String userId1, String password, String phone, String part, String name);

    //改
    public boolean updateBasic(String userId, String name, String phone, String part);
    public boolean updatePassword(String uid, String pw);

    //改+name
    public boolean update1(String userId, String name, String password, String phone, String part);

    //总记录数
    public int count();

    //检查登录
    public boolean check(String id, String password);
}
