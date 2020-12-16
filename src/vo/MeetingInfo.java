package vo;

/**
 * 会议信息类
 *
 * @author ZhouJiashu
 * @date 2020-12-03 16:45
 */
public class MeetingInfo {
    private String mid;
    private String mhotel_name;
    private String mhotel_id;
    private int useCar;
    private String mbrief;
    private String part;

    public MeetingInfo(String mid, String hotel_name, String hotel_id, int useCar, String mbrief, String part) {
        this.mid = mid;
        this.mhotel_name = hotel_name;
        this.mhotel_id = hotel_id;
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
        return mhotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.mhotel_name = hotel_name;
    }

    public String getHotel_id() {
        return mhotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.mhotel_id = hotel_id;
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
                ", hotel_name='" + mhotel_name + '\'' +
                ", hotel_id='" + mhotel_id + '\'' +
                ", useCar=" + useCar +
                ", mbrief='" + mbrief + '\'' +
                '}';
    }
}
