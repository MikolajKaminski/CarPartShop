package Products;

public class Bumper extends AProduct {

    private Integer safetyRaiting;
    private String compatibleModels;

    public Bumper(Integer quantity, Double price, String color, String brand, String name, Double weight, Integer safetyRaiting, String compatibleModels) {
        super(quantity, price, color, brand, name, weight);

        this.safetyRaiting = safetyRaiting;
        this.compatibleModels = compatibleModels;
    }

    public String getSpecificInfo() {
        String info = "";
        info += "\nSafety rating: " + this.safetyRaiting;
        info += "\nCompatible models: " + this.compatibleModels;

        return info;
    }
}
