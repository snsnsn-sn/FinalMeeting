package vo;

public class JoinMeeting {
    private String userId;//参加会议人员的账号
    private String meetingId;//参加会议的会议号
    private String joinTime;//参会时间
    private String need;//参会的其他需求

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "JoinMeeting{" +
                "userId='" + userId + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", need='" + need + '\'' +
                '}';
    }

    public JoinMeeting(String userId, String meetingId, String joinTime, String need) {
        this.userId = userId;
        this.meetingId = meetingId;
        this.joinTime = joinTime;
        this.need = need;
    }
}
