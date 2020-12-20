package dao;

import vo.Admin;

import java.util.List;

public interface AdminDao {
    //实现分页的查找
    public List<Admin> findAll(int pageId, int pageSize);
    //无分页
    public List<Admin> findAll();
    //增
    public boolean insert(String id, String password, String name);
    //根据 id 删除
    public boolean deleteById(String id);
    //根据 id 修改，修改密码和账号为password，id1
    public void update(String id, String id1, String password, String name);
    //查表的总记录数
    public int count();
    //查表是否存在
    public boolean check(String id, String password);

    public Admin findByAdminId(String adminId);
    public boolean updateBasic(String userId, String name);
    public boolean updatePassword(String uid, String pw);
}
