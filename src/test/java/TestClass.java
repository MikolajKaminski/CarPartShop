import Bookkeeping.PriceBrutto;
import Bookkeeping.PriceNetto;

import Products.AProduct;
import Products.Bumper;
import Shop.Shop;
import Shop.User;

public class TestClass {

    private Shop shop;
    private String products = "Bumper, 3, 200.00, black, Mazda, Mazda Bumper Black, 30.00, " +
            "Bumper, 2, 150.00, red, Mazda, Mazda Bumper Red, 30.00, " +
            "Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00, " +
            "Spoiler, 5, 750.00, black, Mazda, Mazda Spoiler Black, 15.00, " +
            "Wheel, 4, 150.00, silver, Mazda, Mazda Wheel, 7.00, " +
            "Wheel, 20, 200.00, silver, BMW, BMW Wheel, 12.00";
    private String users = "Nick, Netherlands, Martin, Belgium, Elvis, Luxembourg";


    @org.junit.Before
    public void Before(){
        //Make shop
        this.shop = new Shop();
        //Populate shop
        this.shop.populateShop(this.products, this.users);
    }

    @org.junit.Test
    public void ReductionInQuantityOnPurchase() throws Exception {
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
    public void BruttoAndNettoTotalPrice() throws Exception {
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
    public void CalculateInvoiceWithDelivery() throws Exception {
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
    public void CalculateInvoiceWithInstalling() throws Exception {
        //Arrange

        //Act

        //Assert
        //assert(x == y);
    }

    @org.junit.Test
    public void TwoCartsForUser() throws Exception {
        //Arrange

        //Act

        //Assert
        //assert(x == y);
    }

    @org.junit.Test
    public void DifferentBruttoPricePerCountry() throws Exception {
        //Arrange

        //Act

        //Assert
        //assert(x == y);
    }

    @org.junit.Test
    public void AddingAndRetrievingFeedback() throws Exception {
        //Arrange

        //Act

        //Assert
        //assert(x == y);
    }

    @org.junit.Test
    public void AddingProducts() throws Exception {
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
    public void AddingUsers() throws Exception {
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

}
