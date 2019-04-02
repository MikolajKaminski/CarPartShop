package Factories;

import Products.AProduct;
import Products.Spoiler;

public class SpoilerFactory implements IProductFactory {
    private Spoiler spoiler;

    public SpoilerFactory(Integer quantity, Double price, String color, String brand, String name, Double weight, String compatibleModels) {
        this.spoiler = new Spoiler(quantity, price, color, brand, name, weight, compatibleModels);
    }

    public AProduct getProduct() {
        return this.spoiler;
    }
}
