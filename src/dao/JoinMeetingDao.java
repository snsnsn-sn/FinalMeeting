package dao;

import vo.JoinMeeting;
import java.util.List;

public interface JoinMeetingDao {
    //查全部
    public List<JoinMeeting> findAll(int pageId, int pageSize);
    //查全部
    public List<JoinMeeting> findAll();

    //增
    public void insert(String userId, String meetingId);

    //根据用户id 删除记录
    public void deleteByUserId(String userId);
    //根据会议id 删除记录
    public void deleteByMeetingId(String meetingId);

    //改，根据用户 id 改
    public void update(String userId, String userId1, String meetingId);

    //查总记录数
    public int count();
    //根据用户Id查所参加的会议，返回一个meetingId的集合，查会议信息还得根据会议号去查表
    public List<String> findByUserId(String userId);
    //根据会议id查所有参加的用户，返回一个userId的集合，查用户信息还得根据用户名去查表
    public List<String> findByMeetingId(String meetingId);
}
