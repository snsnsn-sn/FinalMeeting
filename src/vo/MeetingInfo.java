package vo;

/**
 * 会议信息类
 *
 * @author ZhouJiashu
 * @date 2020-12-03 16:45
 */
public class MeetingInfo {
    private String mid;//会议id
    private String hotel_name;
    private String hotel_id;
    private int useCar;
    private String mbrief;
    private String part;//举办单位

    public MeetingInfo(String mid, String hotel_name, String hotel_id, int useCar, String mbrief, String part) {
        this.mid = mid;
        this.hotel_name = hotel_name;
        this.hotel_id = hotel_id;
        this.useCar = useCar;
        this.mbrief = mbrief;
        this.part = part;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getUseCar() {
        return useCar;
    }

    public void setUseCar(int useCar) {
        this.useCar = useCar;
    }

    public String getMbrief() {
        return mbrief;
    }

    public void setMbrief(String mbrief) {
        this.mbrief = mbrief;
    }

    @Override
    public String toString() {
        return "MeetingInfo{" +
                "mid='" + mid + '\'' +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_id='" + hotel_id + '\'' +
                ", useCar=" + useCar +
                ", mbrief='" + mbrief + '\'' +
                '}';
    }
}
