package Factories;

import Products.AProduct;
import Products.Bumper;

public class BumperFactory implements IProductFactory {
    private Bumper bumper;

    public BumperFactory(Integer quantity, Double price, String color, String brand, String name, Double weight) {
        this.bumper = new Bumper(quantity, price, color, brand, name, weight);
    }

    public AProduct getProduct() {
        return this.bumper;
    }
}
