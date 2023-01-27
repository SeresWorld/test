package data;

public class User {

    private String login;
    private String password;

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
