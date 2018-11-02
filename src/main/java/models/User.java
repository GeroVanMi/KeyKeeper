package models;

import java.util.ArrayList;

public class User {
    private String name, password;
    private ArrayList<Entry> entries;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.entries = new ArrayList<>();
    }

    public void createEntry(String website, String password, String username) {
        entries.add(new Entry(website, password, username));
    }

    public void deleteEntry(Entry e) {
        entries.remove(e);
    }

    public void deleteEntry(int index) {
        entries.remove(index);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
