package dao;

import vo.Meeting;

import java.util.List;

public interface MeetingDao {
    //实现分页的查找，查找全部
    public List<Meeting> findAll(int pageId, int pageSize);
    //无分页
    public List<Meeting> findAll();

    //根据用户 Id 查找，返回会议列表
    public List<Meeting> findByUserId(String userId, int pageId, int pageSize);
    //根据用户 Id 查找，返回会议列表，无分页
    public List<Meeting> findByUserId(String userId);
    //根据会议 Id 查找，返回会议列表，虽然是列表，但主键值唯一，其实就一条记录
    public List<Meeting> findByMeetingId(String MeetingId, int pageId, int pageSize);
    //根据会议 Id 查找，返回会议列表，无分页
    public List<Meeting> findByMeetingId(String MeetingId);

    //增
    public void insert(String meetingId, String userId, String place, int peopleCount, String time, String meetingName, int state);

    //根据 meetingId删
    public void deleteByMeetingId(String meetingId);

    //根据会议id 修改
    public void update(String meetingId, String meetingId1, String userId, String place, int peopleCount, String time, String meetingName, int state);

    //查表的总记录数
    public int count();
    //查该用户创建会议的记录数
    public int countUser(String userId);
    //查会议 meetingId 的记录数
    public int countMeeting(String meetingId);

}
