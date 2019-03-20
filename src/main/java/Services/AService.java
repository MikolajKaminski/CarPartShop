package Services;

import Products.AProduct;

import java.util.List;

public abstract class AService {

    private List<AProduct> products;

    public AService(List<AProduct> products) {
        this.products = products;
    }

    public List<AProduct> getProducts() {
        return this.products;
    }

    public abstract Double getPrice();

}
