package service;

import dao.JoinMeetingDao;
import dao.MeetingDao;
import dao.impl.JoinMeetingImpl;
import dao.impl.MeetingImpl;
import vo.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJiashu
 * @date 2020-11-29 20:47
 */
public class MeetingService {
    private MeetingDao meetingImpl=new MeetingImpl();
    private JoinMeetingDao jmImpl=new JoinMeetingImpl();

    public List<Meeting> findAll(){ return meetingImpl.findAll();}

    public List<Meeting> getCreatedMeetingByUserId(String id){return meetingImpl.findByUserId(id);}

    /**
     *
     * @param id 用户id
     * @return 该用户参加的会议对象列表
     */
    public List<Meeting> getJoinedMeetingByUserId(String id){
        List<Meeting> meetingLsit=new ArrayList<>();
        for(String mid:jmImpl.findByUserId(id)){
            meetingLsit.addAll(meetingImpl.findByMeetingId(mid));
        }
        return meetingLsit;
    }

    public  List<Meeting> getMeetingByMeetingId(String mid){
        return meetingImpl.findByMeetingId(mid);
    }

    public List<Meeting> getMeetingByUserId(String uid){
        return meetingImpl.findByUserId(uid);
    }

    public List<Meeting> getMeetingByMeetingName(String mname){
        return meetingImpl.getMeetingByMeetingName(mname);
    }

    public boolean insert(String name,String uid,String part,String time,String place){return meetingImpl.insert(name,uid,part,time,place);}
}
