package Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Bookkeeping.Invoice;

public class User {
    private String name;
    private Cart cart;
    private List<Invoice> invoices;

    public User(String name) {
        this.name = name;

        this.invoices = new ArrayList<Invoice>();
    }

    public void makeCart() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return this.cart;
    }

    public void makeInvoice() {
        System.out.println("--------------------------------------");
        System.out.println("Select country:");
        System.out.println("Belgium: 1");
        System.out.println("Netherlands: 2");
        System.out.println("Luxembourg: 3");
        //TODO: Input not working in IntelliJ
        Scanner sc = new Scanner(System.in);
        Integer input = sc.nextInt();
        System.out.println("--------------------------------------");
        switch(input) {
            case 1:
                this.invoices.add(new Invoice("Belgium", this.cart));
                break;
            case 3:
                this.invoices.add(new Invoice("Luxembourg", this.cart));
                break;
            default:
                this.invoices.add(new Invoice("Netherlands", this.cart));
        }
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }
}
