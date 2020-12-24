package dao;

import vo.Driver;

import java.util.List;

public interface DriverDao {
    //实现分页的查找
    public List<Driver> findAll(int pageId, int pageSize);
    //无分页的查找
    public List<Driver> findAll();
    //增
    public void insert(String id, String password, String phone, int passenger, int state, String driverName);
    //根据 id 删除
    public void deleteByDriverId(String driverId);
    //根据 id 修改，修改密码、账号、电话
    public void update(String id, String id1, String password, String phone, int passenger, int state, String driverName);
    //查表的总记录数
    public int count();
    //查表是否存在
    public boolean check(String id, String password);

    //查driver的信息
    public List<Driver> findByDriverId(String id);
}
