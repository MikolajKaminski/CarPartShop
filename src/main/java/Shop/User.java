package Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Bookkeeping.Country;
import Bookkeeping.Invoice;

public class User {
    private String name;
    private Cart cart;
    private List<Invoice> invoices;
    private Country country;

    public User(String name, String country) {
        this.name = name;
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
        this.invoices = new ArrayList<>();
    }

    public void makeCart() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return this.cart;
    }

    public void makeInvoice() {
        this.invoices.add(new Invoice(country, cart));
        //TODO: After the creation of the invoice, there is no way now to create a new cart without deleting the info in the old one
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }
}
