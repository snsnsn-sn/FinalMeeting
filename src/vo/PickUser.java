package vo;

public class PickUser {
    private String userId;
    private String driverId;
    private String pickTime;//接送时间
    private String pickPlace;//司机给的接送地点

    public String getPickTime(){return pickTime;}
    public String getPickPlace(){return pickPlace;}
    public void setPickTime(String pickTime){
        this.pickTime = pickTime;
    }
    public void setPickPlace(String pickPlace){
        this.pickPlace = pickPlace;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public PickUser(String userId, String driverId,String pickTIme,String pickPlace) {
        this.userId = userId;
        this.driverId = driverId;
        this.pickTime = pickTIme;
        this.pickPlace = pickPlace;
    }

    @Override
    public String toString() {
        return "PickUser{" +
                "userId='" + userId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", pickTime='" + pickTime + '\'' +
                ", pickPlace='" + pickPlace + '\'' +
                '}';
    }
}
