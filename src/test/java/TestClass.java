import Bookkeeping.Invoice;
import Bookkeeping.PriceBrutto;
import Bookkeeping.PriceNetto;

import Products.AProduct;
import Products.Bumper;
import Products.Feedback;
import Shop.Shop;
import Shop.User;
import Shop.Cart;
import Shop.Facade;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

    private Shop shop;
    private Facade facade;

    @org.junit.Before
    public void Before(){
        String products = "Bumper, 3, 200.00, black, Mazda, Mazda Bumper Black, 30.00, 4, MX-5 NA; MX-5 NB, " +
                "Bumper, 2, 150.00, red, Mazda, Mazda Bumper Red, 30.00, 4, MX-5 NC; MX-5 ND, " +
                "Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00, 5, 328i; 330d; 330i, " +
                "Spoiler, 5, 750.00, black, Mazda, Mazda Spoiler Black, 15.00, MX-5 NA; MX-5 NB, " +
                "Wheel, 4, 150.00, silver, Mazda, Mazda Wheel, 7.00, R17, " +
                "Wheel, 20, 200.00, silver, BMW, BMW Wheel, 12.00, R19, ";
        String users = "Nick, Netherlands, Martin, Belgium, Elvis, Luxembourg";
        //Make shop
        this.shop = new Shop();
        //Populate shop
        this.shop.populateShop(products, users);
        this.facade = new Facade(shop);
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

        //Assert
        assert(this.shop.getProducts().get(2).getQuantity() == initialAmount - 1);

        this.shop.makePurchase(this.shop.getUsers().get(1));
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
        User user = this.shop.getUsers().get(0);
        user.makeCart();
        Cart cart1 = user.getCart();
        AProduct product1 = this.shop.getProducts().get(1);

        //Act
        cart1.addToCart(product1);
        this.shop.makePurchase(user);
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
        assert(invoice2.getTotalPrice() == 201.63);
    }

    @org.junit.Test
    public void DifferentBruttoPricePerCountry() {
        //Arrange
        // users
        User nl_user = this.shop.getUsers().get(0);
        nl_user.makeCart();
        User be_user = this.shop.getUsers().get(1);
        be_user.makeCart();
        User lu_user = this.shop.getUsers().get(2);
        lu_user.makeCart();
        // carts
        Cart nl_cart = nl_user.getCart();
        Cart be_cart = be_user.getCart();
        Cart lu_cart = lu_user.getCart();
        // bruttos
        PriceBrutto nl_brutto, be_brutto, lu_brutto;

        //Act
        // nl_user
        nl_cart.addToCart(this.shop.getProducts().get(1));
        nl_cart.addToCart(this.shop.getProducts().get(0));
        // be_user
        be_cart.addToCart(this.shop.getProducts().get(5));
        be_cart.addToCart(this.shop.getProducts().get(4));
        // lu_user
        lu_cart.addToCart(this.shop.getProducts().get(3));
        // bruttos
        nl_brutto = new PriceBrutto(nl_cart.getProducts(),nl_user.getCountry().getVat());
        be_brutto = new PriceBrutto(be_cart.getProducts(),be_user.getCountry().getVat());
        lu_brutto = new PriceBrutto(lu_cart.getProducts(),lu_user.getCountry().getVat());

        //Assert
        assert(nl_brutto.getPrice() == 430.50);
        assert(be_brutto.getPrice() == 416.50);
        assert(lu_brutto.getPrice() == 840);

    }

    @org.junit.Test
    public void AddingAndRetrievingFeedback() {
        //Arrange
        AProduct product = this.shop.getProducts().get(0);

        //Act
        product.addFeedback("Perfect product", 4);

        //Assert
        Feedback productFeedback = product.getFeedback().get(0);
        assert(productFeedback.getComment().equals("Perfect product"));
        assert(productFeedback.getRating() == 4);
    }

    @org.junit.Test
    public void AddingProducts() {
        //Arrange
        Integer amountOfProducts = this.shop.getProducts().size();
        AProduct newProduct = new Bumper(2, 300.00, "red", "Honda", "Honda Bumper Red", 35.00, 2, "CR-V");

        //Act
        this.shop.addProduct(newProduct);
        Integer newAmountOfProducts = this.shop.getProducts().size();

        //Assert
        assert(amountOfProducts + 1 == newAmountOfProducts);
    }

    @org.junit.Test
    public void AddingUsers() {
        //Arrange
        Integer amountOfUsers = this.shop.getUsers().size();
        User newUser = new User("Allan", "Belgium");

        //Act
        this.shop.addUser(newUser);
        Integer newAmountOfUsers = this.shop.getUsers().size();

        //Assert
        assert(amountOfUsers + 1 == newAmountOfUsers);
    }

    @org.junit.Test
    public void productSpecialInfo() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        AProduct product2 = this.shop.getProducts().get(3);
        AProduct product3 = this.shop.getProducts().get(4);

        //Act
        String longInfo1 = product1.getInfo();
        String shortInfo1 = product1.getSpecificInfo();
        String longInfo2 = product2.getInfo();
        String shortInfo2 = product2.getSpecificInfo();
        String longInfo3 = product3.getInfo();
        String shortInfo3 = product3.getSpecificInfo();

        //Assert
        assert(longInfo1.length() > shortInfo1.length());
        assert(longInfo2.length() > shortInfo2.length());
        assert(longInfo3.length() > shortInfo3.length());

        assert(longInfo1.contains("Safety rating"));
        assert(!shortInfo1.contains("Brand"));

        assert(longInfo2.contains("Compatible models"));
        assert(!shortInfo2.contains("Brand"));

        assert(longInfo3.contains("Inch size"));
        assert(!shortInfo2.contains("Brand"));
    }

    @org.junit.Test
    public void facadeInvoice() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice regularInvoice = this.facade.buy(user, product1);

        //Assert
        assert(regularInvoice.getTotalPrice() == 246.0);
    }

    @org.junit.Test
    public void facadeDeliveryInvoice() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice regularInvoice = this.facade.buy(user, product1);
        Invoice deliveryInvoice = this.facade.buyWithDelivery(user, regularInvoice);

        //Assert
        assert(deliveryInvoice.getTotalPrice() == 246.0);
    }

    @org.junit.Test
    public void facadeInstallationInvoice() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice regularInvoice = this.facade.buy(user, product1);
        Invoice installationInvoice = this.facade.buyWithInstallation(user, regularInvoice);

        //Assert
        assert(installationInvoice.getTotalPrice() == 246.0);
    }

    @org.junit.Test
    public void facadeFullInvoice() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice regularInvoice = this.facade.buy(user, product1);
        Invoice fullServiceInvoice = this.facade.buyWithFullService(user, regularInvoice);

        //Assert
        assert(fullServiceInvoice.getTotalPrice() == 246.0);
    }

    @org.junit.Test
    public void facadeMultipleInvoices() {
        //Arrange
        AProduct product1 = this.shop.getProducts().get(0);
        AProduct product2 = this.shop.getProducts().get(1);
        User user = this.shop.getUsers().get(0);

        List<AProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        //Act
        Invoice multipleProductsInvoice = this.facade.buyMultiple(user, products);

        //Assert
        assert(multipleProductsInvoice.getTotalPrice() == 430.5);
    }

    @org.junit.Test
    public void facadeExpensiveInvoice() {
        //Arrange
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice expensiveInvoice = this.facade.buyTheMostExpensive(user);

        //Assert
        assert(expensiveInvoice.getTotalPrice() == 922.5);
    }

    @org.junit.Test
    public void facadeNewestInvoice() {
        //Arrange
        User user = this.shop.getUsers().get(0);

        //Act
        Invoice newestProductInvoice = this.facade.buyTheNewest(user);

        //Assert
        assert(newestProductInvoice.getTotalPrice() == 246.0);
    }

    @org.junit.Test
    public void facadeRandomInvoice() {
        //Act
        Invoice randomInvoice = this.facade.randomUserRandomProduct();

        //Assert
        assert(randomInvoice.getTotalPrice() != null);
    }
}
