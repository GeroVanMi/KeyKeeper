package models;

public class Entry {
    private String website, username, password;

    public Entry(String website, String password, String username) {
        this.website = website;
        this.password = password;
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
