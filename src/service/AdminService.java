package service;

import dao.*;
import dao.impl.*;
import vo.*;

import java.util.List;

/**
 * 管理员服务类
 * 该类定义了与管理员有关的操作的函数以及方法
 */
public class AdminService {
    private final AdminRespository adminImpl = new AdminImpl();
    private DriverRespository dirverImpl = new DriverImpl();
    private HotelRespository hoteImpl = new HotelImpl();
    private JoinMeetingDao joinMeetingImpl = new JoinMeetingImpl();
    private final MeetingService meeting = new MeetingService();
    private OrderCarRespository orderCarImpl = new OrderCarImpl();
    private OrderHotelRespository orderHotelImpl = new OrderHotelImpl();
    private final UserService user = new UserService();

    //返回一个装有系统全部管理员的List
    public List<Admin> findAll(){return adminImpl.findAll();}

    //返回以PageSize分页的第n页的管理员的List
    public List<Admin> findAll(int pageId,int pageSize) {return adminImpl.findAll(pageId,pageSize);}

    /**
     * @param loginID 登录账号
     * @param loginPW 登录密码
     * @return 数据库中存在该账号时返回true，数据库中不存在该账号或者密码错误时返回false
     */
    public boolean login(String loginID, String loginPW){//需要修改：在adminimpl添加findByAdminID
        if(id_exist(loginID))
                return loginPW.equals(adminImpl.findByUserId(loginID).getPassword());
        else
            return false;
    }

    /**
     * 通过用户id查找该管理员的昵称并返回,
     * @param id 用户id
     * @return adminName 若用户不存在,返回null
     */
    public String getNameById(String id){
        return id_exist(id)?adminImpl.findByUserId(id).getName():null;
    }

    /**
     * 检查id是否在表中已存在
     * @param id 要检查的字符串
     * @return 布尔值 该id是否在表中已存在
     */
    public boolean id_exist(String id){
        return adminImpl.findByUserId(id) != null;
    }

    /**
     *
     * @return 全部用户id
     */
    public List<User> user_find(){
        return user.findAll();
    }

    /**
     *
     * @param pageId 页码
     * @param pageSize 位置
     * @return 用户id
     */
    public List<User> user_find(int pageId,int pageSize){
        return user.findAll(pageId,pageSize);
    }
    /**
     * 向数据库插入新用户
     * @param userId 新用户id
     * @param password 新用户密码
     * @param phone 新用户电话
     * @param part 新用户所属部门
     * @return 布尔值 是否插入成功
     */
    public boolean user_insert(String userId,String password,String phone,String part) {
        return user.insert(userId,password,phone,part);
    }

    /**
     *
     * @param userId 新用户Id
     * @param name  新用户姓名
     * @param password 新用户密码
     * @param phone 新用户电话
     * @param part 新用户部门
     * @return 是否成功
     */
    public boolean user_insert(String userId,String name,String password,String phone,String part) {

        return user.insert(userId,name,password,phone,part);
    }

    /**
     * 删除用户
     * @param userId 要删除的用户的id
     * @return 布尔值 是否删除成功
     */
    public boolean user_delete(String userId) {
        return user.delete(userId);
    }

    /**
     * 更新用户信息
     * @param userId 要更改信息的用户的id
     * @param alterPassword 修改后的密码
     * @param alterPhone 修改后的电话
     * @param alterPart 修改后的部门
     * @return boolean 布尔值 是否修改成功
     */
    public boolean user_update(String userId,String alterPassword,String alterPhone,String alterPart) {
        return user.update(userId,alterPassword,alterPhone,alterPart);
    }

    /**
     *
     * @param userId 要更改信息的用户的id
     * @param alterName 需要更改信息用户的姓名
     * @param alterPassword 修改后的密码
     * @param alterPhone 修改后的电话
     * @param alterPart 修改后的部门
     * @return boolean 是否修改成功
     */
    public boolean user_update(String userId,String alterName,String alterPassword,String alterPhone,String alterPart) {
        return user.update(userId,alterName,alterPassword,alterPhone,alterPart);
    }

    public List<Meeting> meeting_findAll(){
        return meeting.findAll();
    }

    public List<Meeting> meeting_getCreatedMeetingByUserId(String id){
        return meeting.getCreatedMeetingByUserId(id);
    }

    public  List<Meeting> meeting_getMeetingByMeetingId(String mid) {
        return meeting.getMeetingByMeetingId(mid);
    }

    public List<Meeting> meeting_getMeetingByUserId(String uid){
        return meeting.getMeetingByUserId(uid);
    }

    public List<Meeting> meeting_getMeetingByMeetingName(String mname){
        return meeting.getMeetingByMeetingName(mname);
    }

    public boolean meeting_insert(String name,String uid,String part,String time,String place){
        return meeting.insert(name,uid,part,time,place);
    }

}

