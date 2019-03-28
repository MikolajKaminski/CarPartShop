import Bookkeeping.Invoice;
import Bookkeeping.PriceBrutto;
import Bookkeeping.PriceNetto;

import Products.AProduct;
import Products.Bumper;
import Products.Feedback;
import Shop.Shop;
import Shop.User;
import Shop.Cart;

public class TestClass {

    private Shop shop;

    @org.junit.Before
    public void Before(){
        String products = "Bumper, 3, 200.00, black, Mazda, Mazda Bumper Black, 30.00, " +
                "Bumper, 2, 150.00, red, Mazda, Mazda Bumper Red, 30.00, " +
                "Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00, " +
                "Spoiler, 5, 750.00, black, Mazda, Mazda Spoiler Black, 15.00, " +
                "Wheel, 4, 150.00, silver, Mazda, Mazda Wheel, 7.00, " +
                "Wheel, 20, 200.00, silver, BMW, BMW Wheel, 12.00";
        String users = "Nick, Netherlands, Martin, Belgium, Elvis, Luxembourg";
        //Make shop
        this.shop = new Shop();
        //Populate shop
        this.shop.populateShop(products, users);
    }

    @org.junit.Test
    public void ReductionInQuantityOnPurchase() {
        //Arrange
        Integer initialAmount = this.shop.getProducts().get(2).getQuantity();
        this.shop.getUsers().get(0).makeCart();
        this.shop.getUsers().get(1).makeCart();

        //Act
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(2));
        this.shop.getUsers().get(1).getCart().addToCart(this.shop.getProducts().get(2));
        this.shop.makePurchase(this.shop.getUsers().get(0));

        assert(this.shop.getProducts().get(2).getQuantity() == initialAmount - 1);

        this.shop.makePurchase(this.shop.getUsers().get(1));

        //Assert
        assert(this.shop.getProducts().get(2).getQuantity() == initialAmount - 1);
    }

    @org.junit.Test
    public void BruttoAndNettoTotalPrice() {
        //Arrange
        this.shop.getUsers().get(0).makeCart();

        //Act
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(0));
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(0));
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(0));

        PriceBrutto brutto = new PriceBrutto(this.shop.getUsers().get(0).getCart().getProducts(), 23);
        PriceNetto netto = new PriceNetto(this.shop.getUsers().get(0).getCart().getProducts());

        //Assert
        assert(netto.getPrice() == 600.00);
        assert(brutto.getPrice() == 738.00);

    }

    @org.junit.Test
    public void CalculateInvoiceWithDelivery() {
        //Arrange
        this.shop.getUsers().get(0).makeCart();

        //Act
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(0));
        this.shop.getUsers().get(0).getCart().addToCart(this.shop.getProducts().get(4));

        this.shop.makePurchase(this.shop.getUsers().get(0));

        this.shop.getUsers().get(0).getInvoices().get(0).addService("Delivery");

        //Assert
        assert(this.shop.getUsers().get(0).getInvoices().get(0).getTotalPrice() == 521.06);
    }

    @org.junit.Test
    public void CalculateInvoiceWithInstalling() {
        
        //Arrange
        User user = shop.getUsers().get(0);
        user.makeCart();
        Cart cart1 = user.getCart();
        AProduct product1 = shop.getProducts().get(1);

        //Act
        cart1.addToCart(product1);
        shop.makePurchase(user);
        Invoice invoice1 = user.getInvoices().get(0);
        invoice1.addService("Installation");


        //Assert
        assert(invoice1.getTotalPrice() == 215.24);
    }

    @org.junit.Test
    public void TwoCartsForUser() {
        //Arrange
        // making a first purchase
        User user = this.shop.getUsers().get(0);
        user.makeCart();
        Cart cart1 = user.getCart();
        AProduct product1 = this.shop.getProducts().get(0);
        AProduct product2 = this.shop.getProducts().get(4);

        //Act
        cart1.addToCart(product1);
        cart1.addToCart(product2);
        this.shop.makePurchase(user);
        Invoice invoice1 = user.getInvoices().get(0);
        invoice1.addService("Delivery");

        // making a second purchase with the same user
        user.makeCart();
        Cart cart2 = user.getCart();
        cart2.addToCart(product2);
        this.shop.makePurchase(user);
        Invoice invoice2 = user.getInvoices().get(1);
        invoice2.addService("Delivery");

        //Assert
        assert(invoice1.getTotalPrice() == 521.06);
        assert (invoice2.getTotalPrice() == 201.63);
    }

    @org.junit.Test
    public void DifferentBruttoPricePerCountry() {
        // TODO: Martin
        //Arrange

        //Act

        //Assert
    }

    @org.junit.Test
    public void AddingAndRetrievingFeedback() {
        //Arrange
        AProduct product = this.shop.getProducts().get(0);
        //Act
        product.addFeedback("Perfect product", 4);
        //Assert
        Feedback productFeedback = product.getFeedback().get(0);
        assert (productFeedback.getComment().equals("Perfect product"));
        assert (productFeedback.getRaiting() == 4);
    }

    @org.junit.Test
    public void AddingProducts() {
        //Arrange
        //Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00
        Integer amountOfProducts = this.shop.getProducts().size();
        AProduct newProduct = new Bumper(2, 300.00, "red", "Honda", "Honda Bumper Red", 35.00);

        //Act
        this.shop.addProduct(newProduct);
        Integer newAmountOfProducts = this.shop.getProducts().size();

        //Assert
        assert(amountOfProducts + 1 == newAmountOfProducts);
    }

    @org.junit.Test
    public void AddingUsers() {
        //Arrange
        //Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00
        Integer amountOfUsers = this.shop.getUsers().size();
        User newUser = new User("Allan", "Belgium");

        //Act
        this.shop.addUser(newUser);
        Integer newAmountOfUsers = this.shop.getUsers().size();

        //Assert
        assert(amountOfUsers + 1 == newAmountOfUsers);
    }

    //TODO: Should we maybe add some functionality to the products? Or do you think this is enough stuff?
}
