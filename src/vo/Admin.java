package vo;

public class Admin {
    private String adminId;//管理员账号
    private String password;//管理员密码

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}