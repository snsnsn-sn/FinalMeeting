package service;

import dao.impl.*;
import vo.Driver;
import vo.OrderCar;
import vo.PickUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 车队服务类
 * 该类定义了车队和司机相关操作的函数
 */

public class CarService {
    private CarTeamImpl team = new CarTeamImpl();
    private PickUserImpl pickUserImpl =new PickUserImpl();
    private OrderCarImpl orderCarImpl = new OrderCarImpl();
    private DriverImpl driverImpl = new DriverImpl();
    private UserImpl userImpl = new UserImpl();

    //车队分配，获得pickUser表，时间，地点为null表示司机未确定
    public List<PickUser> fun1() throws ParseException {
        List<OrderCar> list = orderCarImpl.findAll();//预定车列表
        List<Driver> list1 = driverImpl.findAll();//司机列表
        List<PickUser> li=new ArrayList<>();
        int c=list1.get(0).getPassengers();//车内剩余可乘人数
        for (int i = 0, j = 0; i < list.size() && list.get(i).getState() == 0; i++) {
            //如果用户预约未审核，分配给空闲车司机最大乘客数，默认空闲时无人
            int d = list.get(i).getPeople();//用户的乘客人数
            if (j < list1.size()) {
                if (list1.get(j).getState() == 0 && c > d) {
                    li.add(new PickUser(list.get(i).getUserId(),list1.get(j).getDriverId(),null,null));
                    pickUserImpl.insert(list.get(i).getUserId(),list1.get(j).getDriverId(),null,null);
                    c=c-d;
                } else {
                    //司机状态为忙碌，找下一位空闲司机
                    j++;
                    c = list1.get(j).getPassengers();
                    i--;//司机忙碌。i不变
                }
            }
            else break;//司机都忙碌，剩余用户等待
        }
        return li;
    }

    //司机查看收到的pickUser
    public List<PickUser> getListOfDriver(String id) throws ParseException {
        //执行fun1，获得列表
        List<PickUser> list=new ArrayList<>();
        List<PickUser> list1=fun1();
        for (int i = 0; i <list1.size() ; i++) {
            //查找司机id
            if(id.equals(list.get(i).getDriverId())){
                list.add(list.get(i));
            }
        }
        return list;
    }
    //司机接收或拒绝订车表 0拒绝，1接受
    public boolean isAccept(String i, String id) throws ParseException {
        List<PickUser> list=pickUserImpl.findAll();
        int m = Integer.parseInt(i);
        //接受，司机的stare变为1，用户state变为2
        if (m == 1) {
            /*更新driver表的state状态*/
            Driver a = driverImpl.findByDriverId1(id);
            driverImpl.update(a.getDriverId(), a.getDriverId(), a.getPassword(), a.getPhone(), a.getPassengers(), 1, a.getDriverName());
            /*更新oderCar表的state状态*/
            for (PickUser pickUser : list) {
                if (pickUser.getDriverId().equals(id)) {
                    //找到orderCar对象
                    OrderCar u = orderCarImpl.findById(pickUser.getUserId());
                    //用户state变为2,2表示通过
                    orderCarImpl.update(u.getUserId(), u.getUserId(), u.getPeople(), u.getPlace(), 2, u.getDeadline());
                }
            }
            return true;
        } else {


            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getDriverId().equals(id)) {
                    //拒绝则删除pickUser记录
                    pickUserImpl.deleteByUserId(list.get(j).getUserId());
                    //找到orderCar对象
                    OrderCar u = orderCarImpl.findById(list.get(j).getUserId());
                    //用户state变为1,未通过
                    orderCarImpl.update(u.getUserId(), u.getUserId(), u.getPeople(), u.getPlace(), 1, u.getDeadline());
                }
            }
            return false;
        }
    }

    //设置接车时间和地点,成功返回true
    public boolean setTP (String time,String palace,String driverId) throws ParseException {
        List<PickUser> list=fun1();
        DriverImpl driverImpl = new DriverImpl();
        OrderCarImpl orderCarImpl = new OrderCarImpl();
        boolean flag=false;
        for (int i = 0; i < list.size(); i++){
            //找到属于司机的用户
            if(driverId.equals(list.get(i).getDriverId())){
                //插入数据进pickUser表
                OrderCar o=orderCarImpl.findById(list.get(i).getUserId());
                //查看输入时间是否合法
                if(time.compareTo(o.getDeadline())<=0){
                    //插入数据进pickUser表
                    pickUserImpl.insert(list.get(i).getUserId(),list.get(i).getDriverId(),time,palace);
                    flag=true;
                }
                else return false;
            }
        }
        return flag;
    }
    //用户查询返回pickUser表
    public List<PickUser> getListByUserId(String id){
        List<PickUser> list=pickUserImpl.findByUserId(id);
        return list;
    }
    //用户确认完成,pickUser表删除数据,司机的state变为0
    public void delete(String id){
        pickUserImpl.deleteByUserId(id);
    }
    //查看全部的pickUser表
    public List<PickUser> findAll(){
        return pickUserImpl.findAll();
    }
    //插入车队
    public void insert(String teamId, String password, String teamName){
        team.insert(teamId,password,teamName);
    }
    //车队登录是否成功
    public boolean login(String loginId,String loginPW){
        return team.check(loginId,loginPW);
    }
    //修改车队信息
    public void update (String teamId, String teamId1, String password, String teamName){
        team.update(teamId,teamId1,password,teamName);
    }

}