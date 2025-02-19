package data;

public class Smartphone extends Product{

    private String phoneManufacturer;

    public Smartphone(int id, String name, int price, String phoneManufacturer) {
        super(id, name, price);
        this.phoneManufacturer = phoneManufacturer;
    }
}
