package dao;

import vo.CarTeam;

public interface CarTeamDao {
    //插入
    public void insert(String teamId, String password, String teamName);
    //验证
    public boolean check(String teamId, String password);
    //修改车队信息，查teamId的记录，后面是修改的信息
    public void update(String teamId, String teamId1, String password, String teamName);

    public CarTeam findById(String id);

    public void deleteById(String id);
    public void updatePassword(String id, String password);
}
