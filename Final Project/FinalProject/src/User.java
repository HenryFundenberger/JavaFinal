public class User {

    // User class is basic person class they are either an admin user or they are not and they have a name (primary key)
    private String name;
    private Boolean isAdmin;

    public User(String name, Boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}
