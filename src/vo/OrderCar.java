package vo;

public class OrderCar {
    private String userId;//订车用户的账号
    private Integer people;//乘车人数
    private String place;//乘车地点
    private Integer state;//订单状态，0表示为审核；1表示未通过，2表示已通过
    private String deadline;//预约的最晚时间，记得使用DBDate类里的方法

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public OrderCar(String userId, Integer people, String place, Integer state, String deadline) {
        this.userId = userId;
        this.people = people;
        this.place = place;
        this.state = state;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "OrderCar{" +
                "userId='" + userId + '\'' +
                ", people=" + people +
                ", place='" + place + '\'' +
                ", state=" + state +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
