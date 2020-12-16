package service;

import dao.*;
import dao.impl.*;
import vo.Meeting;
import vo.MeetingInfo;
import vo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJiashu
 * @date 2020-11-29 20:47
 */
public class MeetingService {
    private MeetingDao meetingImpl = new MeetingImpl();
    private JoinMeetingDao jmImpl = new JoinMeetingImpl();
    private MeetingInfoDao infoImpl = new MeetingInfoImpl();
    private UserDao userImpl = new UserImpl();
    private OrderCarDao carImpl = new OrderCarImpl();
    private OrderHotelDao hotelImpl = new OrderHotelImpl();

    public List<Meeting> findAll() {
        return meetingImpl.findAll();
    }

    public List<Meeting> getCreatedMeetingByUserId(String id) {
        return meetingImpl.findByUserId(id);
    }

    /**
     * @param id 用户id
     * @return 该用户参加的会议对象列表
     */
    public List<Meeting> getJoinedMeetingByUserId(String id) {
        List<Meeting> meetingLsit = new ArrayList<>();
        for (String mid : jmImpl.findByUserId1(id)) {
            meetingLsit.addAll(meetingImpl.findByMeetingId(mid));
        }
        return meetingLsit;
    }

    public List<Meeting> getMeetingByMeetingId(String mid) {
        return meetingImpl.findByMeetingId(mid);
    }

    public List<Meeting> getMeetingByUserId(String uid) {
        return meetingImpl.findByUserId(uid);
    }

    public List<Meeting> getMeetingByMeetingName(String mname) {
        return meetingImpl.getMeetingByMeetingName(mname);
    }

    public boolean insert(String name, String uid, String part, String time, String place) {
        return meetingImpl.insert(name, uid, part, time, place);
    }

    public MeetingInfo getMeetingInfoById(String mid) {
        return infoImpl.getInfoByMeetingId(mid);
    }

    public boolean insertInfo(String mid, String hotelName, String hotelId, int useCar, String brief, String part) {
        return infoImpl.insert(mid, hotelName, hotelId, useCar, brief, part);
    }

    public boolean deleteInfo(String mid) {
        return infoImpl.delete(mid);
    }

    public boolean updateMeetingBasic(String mid, String name, String time, String place, String state) {
        Meeting m = meetingImpl.findByMeetingId(mid).get(0);
        meetingImpl.deleteByMeetingId(mid);
        return meetingImpl.insert(mid, m.getUserId(), place, m.getPeopleCount(), time, name, Integer.parseInt(state)) ? true : false;
    }

    /**
     * @param mid 会议id
     * @return 该会议的参与者列表
     */
    public List<User> getParticipants(String mid) {
        List<User> userList = new ArrayList<>();
        List<String> userIDs = jmImpl.findByMeetingId(mid);
        for (String id : userIDs) {
            userList.add(userImpl.findByUserId1(id));
        }
        return userList;
    }

    public boolean alterMeetingBrief(String mid, String brief) {
        MeetingInfo i = infoImpl.getInfoByMeetingId(mid);
        infoImpl.delete(mid);
        return infoImpl.insert(mid, i.getHotel_name(), i.getHotel_id(), i.getUseCar(), brief, i.getPart()) ? true : false;
    }

    public void deleteMeeting(String mid) {
        jmImpl.deleteByMeetingId(mid);
        infoImpl.delete(mid);
        meetingImpl.deleteByMeetingId(mid);
    }

    public void deleteParticipant(String mid, String uid) {
        jmImpl.deleteParticipant(mid, uid);
    }

    public boolean joinMeeting(String mid, String uid) {
//        return jmImpl.insert(uid, mid) == true ? true : false;
        return jmImpl.insert(uid, mid) == true ? true : false;
    }

    public void orderCar(String uid, String carPeople, String carTime, String carPlace) {
        carImpl.insert(uid, Integer.parseInt(carPeople), carPlace, 0, carTime);
    }


    /**
     * 还未整合MeetingInfo表，infoImpl无效
     *
     * */
    public void orderHotel(String uid, String hotelPeople, String hoteltime, String mid) {
        MeetingInfo i = infoImpl.getInfoByMeetingId(mid);
        hotelImpl.insert(uid, Integer.parseInt(hotelPeople), 0, i.getHotel_name(),hoteltime);
    }

    public boolean quitMeeting(String mid, String uid) {
        return jmImpl.deleteParticipant(mid, uid);
    }

    /**
     * @param mid 查询的会议id
     * @param uid 查询的用户id
     * @return 该用户是否加入了该会议
     */
    public boolean isJoined(String mid, String uid) {
        for (String s : jmImpl.findByMeetingId(mid)) {
            if (s.equals(uid))
                return true;
        }
        return false;
    }
}
