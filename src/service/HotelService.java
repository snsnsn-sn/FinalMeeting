package service;

import dao.HotelDao;
import dao.OrderHotelDao;
import dao.impl.HotelImpl;
import dao.impl.OrderHotelImpl;
import vo.Hotel;
import vo.OrderHotel;

import java.util.List;

public class HotelService {
    private OrderHotelDao order = new OrderHotelImpl();
    private HotelDao hotel = new HotelImpl();
    //根据酒店名字查订单信息
    public List<OrderHotel> findByHotelName(String hotelName){return order.findByHotelName(hotelName);}
    //根据用户id修改订单state
    public void updateState(String userId,String time,int state){order.updateState(userId,time,state);}
    //根据用户id删除订单
    public void deleteByUserId(String userId){order.deleteByUserId(userId);}
    //根据id查酒店的信息
    public List<Hotel> findAll(){return hotel.findAll();}

    //根据用户id和入住时间删除订单
    public void deleteByIdAndTime(String userId,String time){order.deleteByIdAndTime(userId,time);}

}
