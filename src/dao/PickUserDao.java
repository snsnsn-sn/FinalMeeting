package dao;

import vo.PickUser;

import java.util.List;

public interface PickUserDao {
    //查全部
    public List<PickUser> findAll(int currentPage, int pageSize);
    //查看全部记录，显示所有司机接送信息
    public List<PickUser> findAll();
    //根据司机名找记录
    public List<PickUser> findByDriverId(String driverId, int currentPage, int pageSize);
    public List<PickUser> findByDriverId(String driverId);

    //根据用户名找记录
    public List<PickUser> findByUserId(String userId, int currentPage, int pageSize);
    public List<PickUser> findByUserId(String userId);
    //接送完之后就根据用户 id 删除记录
    public void deleteByUserId(String userId);
    //根据司机删除
    public void deleteByDriverId(String driverId);

    //插入接送信息
    public void insert(String userId, String driverId, String pickTime, String pickPlace);

    //查总记录数
    public int count();
    //查找司机 driverId 的接客记录
    public int countDriver(String driverId);
    //查找userId的乘车记录
    public int countUser(String userId);

    public void deleteByUidAndDid(String uid, String did);


}
