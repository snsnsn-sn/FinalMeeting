package dao;

import vo.MeetingInfo;

public interface MeetingInfoDao {
    //查
    public MeetingInfo getInfoByMeetingId(String id);

    //增
    public boolean insert(String mid, String hotel_name, String hotel_id, int useCar, String brief, String part);

    //删
    public boolean delete(String mid);


}
