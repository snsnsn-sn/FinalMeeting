package vo;

public class OrderHotel {
    private String userId;//预定酒店的用户账号
    private Integer people;//住房人数
    private Integer state;//预定状态，0表示未审核，1表示未通过，2,表示已通过

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public OrderHotel(String userId, Integer people, Integer state) {
        this.userId = userId;
        this.people = people;
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderHotel{" +
                "userId='" + userId + '\'' +
                ", people=" + people +
                ", state=" + state +
                '}';
    }
}
