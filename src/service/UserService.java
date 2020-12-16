package service;

import dao.MeetingDao;
import dao.UserDao;
import dao.impl.MeetingImpl;
import dao.impl.UserImpl;
import vo.Meeting;
import vo.User;

import java.util.List;

/**
 * 用户服务类
 * 该类定义了用户相关操作的函数
 */
public class UserService {
    private UserDao userImpl = new UserImpl();
    private MeetingDao mettingImpl = new MeetingImpl();

    //返回一个装有系统全部用户的List
    public List<User> findAll() {
        return userImpl.findAll();
    }

    //返回以PageSize分页的第n页的用户List
    public List<User> findAll(int pageId, int pageSize) {
        return userImpl.findAll(pageId, pageSize);
    }

    /**
     * @param loginID 登录账号
     * @param loginPW 登录密码
     * @return 数据库中是否存在对应账号且密码正确
     */
    public boolean login(String loginID, String loginPW) {
        if (id_exist(loginID))
            return loginPW.equals(userImpl.findByUserId(loginID).getPassword()) ? true : false;
        else
            return false;
    }

    /**
     * 检查id是否在表中已存在
     *
     * @param id 要检查的字符串
     * @return 布尔值 该id是否在表中已存在
     */
    public boolean id_exist(String id) {
        return userImpl.findByUserId(id) != null ? true : false;
    }

    /**
     * 通过用户id查找该用户的昵称并返回,
     *
     * @param id
     * @return userName 若用户不存在,返回null
     */
    public String getNameById(String id) {
        return id_exist(id) ? userImpl.findByUserId(id).getName() : null;
    }

    /**
     * 通过用户id查找用户所属单位
     *
     * @param id
     * @return userPart 若用户不存在,返回null
     */
    public String getPartById(String id) {
        return id_exist(id) ? userImpl.findByUserId(id).getPart() : null;
    }

    /**
     * 查询用户参加的会议
     *
     * @param userId 查询的用户id
     * @return 该用户参加的全部会议列表
     */
    public List<Meeting> getUserMeeting(String userId) {
        return mettingImpl.findByUserId(userId);
    }

    /**
     * 向数据库插入新用户
     *
     * @param userId   新用户id
     * @param password 新用户密码
     * @param phone    新用户电话
     * @param part     新用户所属部门
     * @return 布尔值 是否插入成功
     */
    public boolean insert(String userId, String password, String phone, String part) {
        return userImpl.insert(userId, password, phone, part);
    }

    public boolean insert(String userId, String name, String password, String phone, String part) {
        return userImpl.insert(userId, name, password, phone, part);
    }

    /**
     * 删除用户
     *
     * @param userId 要删除的用户的id
     * @return 布尔值 是否删除成功
     */
    public boolean delete(String userId) {
        return userImpl.deleteByUserId(userId);
    }

    /**
     * 更新用户信息
     *
     * @param userId        要更改信息的用户的id
     * @param alterPassword 修改后的密码
     * @param alterPhone    修改后的电话
     * @param alterPart     修改后的部门
     * @return 布尔值 是否修改成功
     */
    public boolean update(String userId, String alterPassword, String alterPhone, String alterPart) {
        return userImpl.update(userId, alterPassword, alterPhone, alterPart);
    }

    public boolean update(String userId, String alterName, String alterPassword, String alterPhone, String alterPart) {
        return userImpl.update(userId, alterName, alterPassword, alterPhone, alterPart);
    }

    public User getUser(String uid) {
        return userImpl.findByUserId(uid);
    }

    public boolean alterPassword(String uid, String pw0, String pw1) {
        User u = userImpl.findByUserId(uid);
        //无该用户
        if (u == null)
            return false;
        //密码错误
        if (!u.getPassword().equals(pw0))
            return false;
        return userImpl.updatePassword(uid, pw1);
    }

    public boolean alterInfo(String uid, String name, String phone, String part) {
        User u = userImpl.findByUserId(uid);
        //无该用户
        if (u == null)
            return false;
        return userImpl.updateBasic(uid, name, phone, part);
    }
}

