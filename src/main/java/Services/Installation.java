package Services;

import Bookkeeping.Country;
import Products.AProduct;

import java.util.List;

public class Installation extends AService {

    private Double totalPrice;

    public Installation(List<AProduct> products, Country country) {
        super(products);

        this.totalPrice = super.getProducts().size() * country.getInstallationCost();
    }

    public Double getPrice() {
        return this.totalPrice;
    }

}
