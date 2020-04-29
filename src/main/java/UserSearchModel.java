
public class UserSearchModel {
    private int userId;
    private int city;
    private String current_ts;
    private String search;

    public UserSearchModel(){

    }
    public UserSearchModel(int userId, int city, String current_ts, String search) {
        this.userId = userId;
        this.city = city;
        this.current_ts = current_ts;
        this.search = search;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getCurrent_ts() {
        return current_ts;
    }

    public void setCurrent_ts(String current_ts) {
        this.current_ts = current_ts;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
