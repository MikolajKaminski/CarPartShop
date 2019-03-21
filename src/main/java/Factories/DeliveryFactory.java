package Factories;

import Bookkeeping.Country;
import Services.AService;
import Products.AProduct;
import Services.Delivery;

import java.util.List;

public class DeliveryFactory implements IServiceFactory {
    private Delivery delivery;

    public DeliveryFactory(List<AProduct> products, Country country) {
        this.delivery = new Delivery(products, country);
    }

    public AService getService() {
        return this.delivery;
    }
}
