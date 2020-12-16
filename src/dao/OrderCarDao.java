package dao;

import vo.OrderCar;

import java.util.List;

public interface OrderCarDao {
    //查全部
    public List<OrderCar> findAll(int currentPage, int pageSize);

    //查全部
    public List<OrderCar> findAll();

    //增
    public void insert(String userId, int people, String place, int state, String deadline);

    //根据用户 id 删
    public void deleteByUserId(String userId);

    //改,根据用户 id 改
    public void update(String userId, String userId1, int people, String place, int state, String deadline);

    //查，根据用户 id 查
    public List<OrderCar> findByUserId(String userId);

    //查总记录数
    public int count();
}