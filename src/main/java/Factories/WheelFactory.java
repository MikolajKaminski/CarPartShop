package Factories;

import Products.AProduct;
import Products.Wheel;

public class WheelFactory implements IProductFactory {
    private Wheel wheel;

    public WheelFactory(Integer quantity, Double price, String color, String brand, String name, Double weight, String inch) {
        this.wheel = new Wheel(quantity, price, color, brand, name, weight, inch);
    }

    public AProduct getProduct() {
        return this.wheel;
    }
}
