package vo;

public class User {
    private String userId;//用户账号
    private String password;//用户密码
    private String phone;//用户电话
    private String part;//用户所属部门
    private String name;//真实姓名
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public User(String userId, String password, String phone, String part,String name) {
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.part = part;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", part='" + part + '\'' +
                '}';
    }
}
