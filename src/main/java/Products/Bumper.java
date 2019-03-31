package Products;

public class Bumper extends AProduct {

    private Integer safetyRaiting;

    public Bumper(Integer quantity, Double price, String color, String brand, String name, Double weight) {
        super(quantity, price, color, brand, name, weight);

        this.safetyRaiting = 3;
    }

    public String getSpecificInfo() {
        String info = "";
        info += "Safety rating: " + this.safetyRaiting;

        return info;
    }
}
