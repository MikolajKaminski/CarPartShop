package Bookkeeping;

import Factories.IServiceFactory;
import Factories.DeliveryFactory;
import Factories.InstallationFactory;
import Shop.Cart;

public class Invoice {

    private PriceBrutto totalPrice;
    private Cart cart;
    private Country country;

    public Invoice(String country, Cart cart) {
        switch(country) {
            case "Belgium":
                this.country = new Country(19, 4.99, 39.99);
                break;
            case "Luxembourg":
                this.country = new Country(12, 9.99, 49.99);
                break;
            default:
                this.country = new Country(23, 1.99, 24.99);
        }
        this.cart = cart;

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
        return totalPrice.getPrice();
    }

}
