package Bookkeeping;

import Factories.IServiceFactory;
import Factories.DeliveryFactory;
import Factories.InstallationFactory;
import Shop.Cart;

public class Invoice {

    private PriceBrutto totalPrice;
    private Cart cart;
    private Country country;

    public Invoice(Country country, Cart cart) {
        this.cart = cart;
        this.country = country;
        this.totalPrice = new PriceBrutto(this.cart.getProducts(), this.country.getVat());
    }

    public void addService(String service) {
        IServiceFactory factory = null;
        switch(service) {
            case "Delivery":
                factory = new DeliveryFactory(this.cart.getProducts(), this.country);
                break;
            case "Installation":
                factory = new InstallationFactory(this.cart.getProducts(), this.country);
                break;
            default:
                System.out.println("Wrong service added");
        }

        this.totalPrice.addService(factory.getService());
    }

    public Double getTotalPrice() {
        return this.totalPrice.getPrice();
    }

}
