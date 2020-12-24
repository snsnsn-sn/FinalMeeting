package service;

import dao.DriverDao;
import dao.PickUserDao;
import dao.impl.DriverImpl;
import dao.impl.PickUserImpl;
import vo.Driver;
import vo.PickUser;

import java.text.ParseException;
import java.util.List;

/**
 * @author TuiJinWei
 * @date s{DATE} @month s{MONTH} @year s{YEAR}
 */
public class DriverService {
    private DriverDao driverImpl=new DriverImpl();
    private PickUserDao pickUserDao=new PickUserImpl();
    private CarService carService=new CarService();
     //查全部司机的信息，无分页
    public List<Driver> findAll(){return driverImpl.findAll();}
    //返回PageSize分页的第n页的司机list
    public List<Driver> findAll(int pageId, int pageSize){return driverImpl.findAll(pageId,pageSize);}
    //查找id是否存在
    public  boolean id_exit(String id){return driverImpl.findByDriverId1(id)!=null?true:false;}
    //登录是否成功
    public boolean login(String id,String loginPW){
        if(id_exit(id)){
            System.out.println(driverImpl.findByDriverId1(id).toString());
            System.out.println(driverImpl.findByDriverId1(id).getPassword());
            return loginPW.equals(driverImpl.findByDriverId1(id).getPassword())?true:false;
        }
        else
            return false;
    }
    //通过司机id查找司机电话并返回
    public String getPhoneById(String id){
        return id_exit(id)?driverImpl.findByDriverId1(id).getPhone():null;
    }
    //通过司机id查找司机名字的并返回
    public String getNameById(String id){
        return id_exit(id)?driverImpl.findByDriverId1(id).getDriverName():null;
    }
    //向数据库插入新司机
    public void insert(String id,  String password, String phone, int passenger, int state, String driverName){
        driverImpl.insert(id, password, phone, passenger, state, driverName);
    }
    //删除司机
    public void delete(String id){driverImpl.deleteById(id);}
    //更矮司机信息
    public void update(String driverId,String driverId1, String password, String phone, Integer passengers, Integer state, String driverName){
        driverImpl.update(driverId,driverId1,password,phone,passengers,state,driverName);
    }
    //查id返回司机对象
    public Driver getDriver(String id){return driverImpl.findByDriverId1(id);}
    /**改密码,返回是否成功
    * @param id,原密码，新密码
     */
    public boolean alterPassword(String id,String pw0,String pw1){
        Driver driver=driverImpl.findByDriverId1(id);
        //无司机用户
        if(driver==null)
            return false;
        //密码错误
        if(!driver.getPassword().equals(pw0))
            return false;
        driverImpl.updatePassword(id,pw1);
        return true;
    }

    //查看司机车队分配
     public List<PickUser> getPickUsersList(String id) throws ParseException {
         List<PickUser> list=carService.getListOfDriver(id);
         return list;
     }
    //设置接车时间和地点,成功返回true，失败false
    public boolean setTP(String time,String palace,String id) throws ParseException {
        return carService.setTP(time,palace,id);
    }
}
