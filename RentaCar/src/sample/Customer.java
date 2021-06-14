package sample;

public class Customer {

    private int id;
    private String name;
    private String surname;
    private String kullanici_adi;
    private String sifre;
    private String phone;
    private String email;
    private double last_order;
    private double total_order;

    public Customer(int id, String name, String surname, String kullanici_adi, String phone, String email, double last_order, double total_order) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.kullanici_adi = kullanici_adi;
        this.phone = phone;
        this.email = email;
        this.last_order = last_order;
        this.total_order = total_order;
    }
    public Customer(int id, String name, String surname, String kullanici_adi, String sifre, String phone, String email, double last_order, double total_order) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
        this.phone = phone;
        this.email = email;
        this.last_order = last_order;
        this.total_order = total_order;
    }
    public Customer(String name, String surname,  String email, String kullanici_adi, String sifre, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.sifre = sifre;
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
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
