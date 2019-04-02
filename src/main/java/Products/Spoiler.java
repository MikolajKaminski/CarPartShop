package Products;

public class Spoiler extends AProduct {

    private String compatibleModels;

    public Spoiler(Integer quantity, Double price, String color, String brand, String name, Double weight, String compatibleModels) {
        super(quantity, price, color, brand, name, weight);

        this.compatibleModels = compatibleModels;
    }

    public String getSpecificInfo() {
        String info = "";
        info += "\nCompatible models: " + this.compatibleModels;

        return info;
    }
}
