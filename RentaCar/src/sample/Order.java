package sample;

public class Order {
    private int id;
    private String name;
    private int cargo_id;

    public Order(){

    }
    public Order(int id,String name,int cargo_id){
        this.name=name;
        this.id=id;
        this.cargo_id=cargo_id;
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

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }
}
