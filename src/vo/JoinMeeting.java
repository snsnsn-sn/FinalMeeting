package vo;

public class JoinMeeting {
    private String userId;//参加会议人员的账号
    private String meetingId;//参加会议的会议号

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

    public JoinMeeting(String userId, String meetingId) {
        this.userId = userId;
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "JoinMeeting{" +
                "userId='" + userId + '\'' +
                ", meetingId='" + meetingId + '\'' +
                '}';
    }
}
