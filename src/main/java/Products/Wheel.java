package Products;

public class Wheel extends AProduct {

    private String inch;

    public Wheel(Integer quantity, Double price, String color, String brand, String name, Double weight, String inch) {
        super(quantity, price, color, brand, name, weight);
        this.inch = inch;
    }

    public String getSpecificInfo() {
        String info = "";
        info += "\nInch size: " + this.inch;
        return info;
    }
}
