package vo;

public class Hotel {
    private String hotelId;//酒店人员账号
    private String password;//密码
    private String hotelName;//酒店名字
    private String hotelPlace;//酒店位置

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

    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelPlace() {
        return hotelPlace;
    }

    public void setHotelPlace(String hotelPlace) {
        this.hotelPlace = hotelPlace;
    }

    public Hotel(String hotelId, String password, String hotelName, String hotelPlace) {
        this.hotelId = hotelId;
        this.password = password;
        this.hotelName = hotelName;
        this.hotelPlace = hotelPlace;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", password='" + password + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelPlace='" + hotelPlace + '\'' +
                '}';
    }
}
