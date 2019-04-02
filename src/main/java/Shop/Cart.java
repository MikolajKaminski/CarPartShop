package Shop;

import Products.AProduct;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<AProduct> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addToCart(AProduct product) {
        this.products.add(product);
    }

    public List<AProduct> getProducts() {
        return this.products;
    }
}
