package Factories;

public class DeliveryFactory implements IServiceFactory {
    private Delivery delivery;

    public DeliveryFactory(List<IProduct> products, Country country) {
        this.delivery = new Delivery(products, country);
    }

    @Override
    public Service getService() {
        return this.delivery;
    }
}
