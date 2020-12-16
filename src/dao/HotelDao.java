package dao;

import vo.Hotel;

import java.util.List;

public interface HotelDao {
    public boolean updatePassword(String id,String password);

    public Hotel findByHotelId(String id);
    //实现分页的查找
    public List<Hotel> findAll(int pageId, int pageSize);
    //无分页的查找
    public List<Hotel> findAll();
    //增
    public void insert(String id, String password, String hotelName, String hotelPlace);
    //根据 id 删除
    public void deleteById(String id);
    //根据 id 修改，修改密码和账号为password，id1
    public void update(String id, String id1, String password, String hotelName, String hotelPlace);
    //查表的总记录数
    public int count();
    //查表是否存在
    public boolean check(String id, String password);
    //得到当前酒店登陆者的酒店
    public String getHotelName(String hotelId);

    //
    public Hotel findByHotelName(String name);
}
