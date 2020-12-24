package vo;

public class CarTeam {
    private String teamId;//车队登录账号
    private String password;//登录密码
    private String teamName;//车队姓名

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public CarTeam(String teamId, String password, String teamName) {
        this.teamId = teamId;
        this.password = password;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "CarTeam{" +
                "teamId='" + teamId + '\'' +
                ", password='" + password + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
