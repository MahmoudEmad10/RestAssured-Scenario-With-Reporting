package pojos;

public class registerAndLoginUsers {

    private String email;
    private String password;

    public registerAndLoginUsers() {

    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public registerAndLoginUsers(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
