package dao;

import vo.OrderCar;

import java.util.List;

public interface OrderCarDao {
    //查全部
    public List<OrderCar> findAll(int currentPage, int pageSize);
    //查全部
    public List<OrderCar> findAll();
    //查，根据用户 id 查
    public List<OrderCar> findByUserId(String userId);
    //查，根据用户 id 查，分页
    public List<OrderCar> findByUserId(String userId, int currentPage, int pageSize);
    //增
    public void insert(String userId, int people, String place, int state, String deadline);

    //根据用户 id 删
    public void deleteByUserId(String userId);

    //改,根据用户 id 改
    public void update(String userId, String userId1, int people, String place, int state, String deadline);

    //查 该用户id 的记录数
    public int countUser(String userId);
    //查总记录数
    public int count();

    public OrderCar findById(String id);
    public void updateState(String userId, String deadline, int state);
    public void deleteByIdAndTime(String id, String time);
}
