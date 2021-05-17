package sample;

public class Customer {
    //sütun isimleri

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private double last_order;
    private double total_order;

    public Customer(int id, String name, String surname, String phone, String email, double last_order, double total_order) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.last_order = last_order;
        this.total_order = total_order;
    }

    public Customer(String name, String surname,  String email,String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getLast_order() {
        return last_order;
    }

    public void setLast_order(double last_order) {
        this.last_order = last_order;
    }

    public double getTotal_order() {
        return total_order;
    }

    public void setTotal_order(double total_order) {
        this.total_order = total_order;
    }
}
