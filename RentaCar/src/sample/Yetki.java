package sample;

public class Yetki {
    private String name;
    private String surname;
    private String username;
    private String phone;
    private String email;
    private String role_id;

    public Yetki(String name, String surname, String username, String phone, String email, String role_id) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
