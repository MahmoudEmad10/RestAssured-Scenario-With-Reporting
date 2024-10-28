package pojos;

public class createNewUser {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public createNewUser() {

    }

    public createNewUser(String name, String job) {
        this.name = name;
        this.job = job;
    }

}
