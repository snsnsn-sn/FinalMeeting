package vo;

public class Hotel {
    private String hotelId;//酒店人员账号
    private String password;//密码

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Hotel(String hotelId, String password) {
        this.hotelId = hotelId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
