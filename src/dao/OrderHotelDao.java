package dao;

import vo.OrderHotel;

import java.util.List;

public interface OrderHotelDao {
    //查全部
    public List<OrderHotel> findAll(int currentPage, int pageSize);
    //查全部
    public List<OrderHotel> findAll();
    //查，根据用户 id 查，分页
    public List<OrderHotel> findByUserId(String userId, int currentPage, int pageSize);
    //查，根据用户 id 查
    public List<OrderHotel> findByUserId(String userId);
    //按照酒店的名字查记录
    public List<OrderHotel> findByHotelName(String hotelName);
    //分页
    public List<OrderHotel> findByHotelName(int currentPage, int pageSize, String hotelName);

    //增
    public void insert(String userId, int people, int state, String hotelName);

    //根据用户 id 删
    public void deleteByUserId(String userId);

    //改,根据用户 id 改
    public void update(String userId, String userId1, int people, int state, String hotelName);



    //查总记录数
    public int count();
    //查 userId 的总记录数
    public int countUser(String userId);
    //查 hotelName 的记录数
    public int countHotel(String hotelName);
}
