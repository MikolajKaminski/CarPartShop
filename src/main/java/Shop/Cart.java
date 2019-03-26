package Shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Products.AProduct> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addToCart(Products.AProduct product) {
        this.products.add(product);
    }

    public List<Products.AProduct> getProducts() {
        return this.products;
    }
}
