package Shop;

import Bookkeeping.Invoice;
import Products.AProduct;

import java.util.List;

public class Facade {

    private Shop shop;

    public Facade(Shop shop) {
        this.shop = shop;
    }

    private void buyOneOfEach(String brand, User user) {

    }

    private void buyWithDelivery(User user, AProduct product) {
        shop.addUser(user);
        user.makeCart();
        user.getCart().addToCart(product);
        Invoice invoice = shop.makePurchase(user);

        invoice.addService("Delivery");
    }

    private void buyWithInstallation(User user, AProduct product) {
        this.shop.addUser(user);
        user.makeCart();
        user.getCart().addToCart(product);
        Invoice invoice = this.shop.makePurchase(user);
    }

    private void buyWithFullService(User user) {
        //
    }


}
