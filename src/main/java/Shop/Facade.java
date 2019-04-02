package Shop;

import Bookkeeping.Invoice;
import Products.AProduct;
import java.util.List;
import java.util.Random;

public class Facade {

    private Shop shop;
    private Random random;

    public Facade(Shop shop) {
        this.shop = shop;
        this.random = new Random();
    }

    public Invoice buy(User user, AProduct product) {
        shop.addUser(user);
        user.makeCart();
        user.getCart().addToCart(product);
        return shop.makePurchase(user);
    }

    public Invoice buyWithDelivery(User user, Invoice invoice) {
        invoice.addService("Delivery");
        return this.shop.makePurchase(user);
    }

    public Invoice buyWithInstallation(User user, Invoice invoice) {
        invoice.addService("Installation");
        return this.shop.makePurchase(user);
    }

    public Invoice buyWithFullService(User user, Invoice invoice) {
        invoice.addService("Delivery");
        invoice.addService("Installation");
        return this.shop.makePurchase(user);
    }

    public Invoice buyMultiple(User user, List<AProduct> products) {
        user.makeCart();
        Cart cart = user.getCart();
        for(AProduct product : products) {
            cart.addToCart(product);
        }
        Invoice invoice = shop.makePurchase(user);

        if (invoice == null) {
            System.out.println("Unable to buy the products.");
        }
        return shop.makePurchase(user);
    }

    public User getRandomUser() {
        List<User> users = shop.getUsers();
        return users.get(random.nextInt(users.size()));
    }

    public AProduct getRandomProduct() {
        List<AProduct> products = shop.getProducts();
        return products.get(random.nextInt(products.size()));
    }

    public Invoice buyTheMostExpensive(User user) {
        List<AProduct> products = shop.getProducts();
        AProduct expensiveProduct = products.get(0);
        for(AProduct product : products) {
            if (product.getPrice() > expensiveProduct.getPrice()) {
                expensiveProduct = product;
            }
        }
        return buy(user, expensiveProduct);
    }

    public Invoice buyTheNewest(User user) {
        List<AProduct> products = shop.getProducts();
        AProduct newestProduct = products.get(products.size() - 1);
        return buy(user, newestProduct);
    }

    public Invoice randomUserRandomProduct() {
        return buy(getRandomUser(), getRandomProduct());
    }

}
