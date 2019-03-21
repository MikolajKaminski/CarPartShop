package Factories;

public class WheelFactory implements IProductFactory {
    private Wheel wheel;

    public WheelFactory(Integer quantity, Double price, String color, String brand, String name, Double weight) {
        this.wheel = new Wheel(quantity, price, color, brand, name, weight);
    }

    @Override
    public IProduct getProduct() {
        return this.wheel;
    }
}
