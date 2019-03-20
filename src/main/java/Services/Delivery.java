package Services;

import Bookkeeping.Country;
import Products.AProduct;

import java.util.List;

public class Delivery extends AService {

    private Double deliveryPrice;

    public Delivery(List<AProduct> products, Country country) {
        super(products);

        this.deliveryPrice = country.getDeliveryCost();

		/*Double totalWeight = 0.0;

		for(IProduct product : super.getProducts()) {
			totalWeight += product.getWeight();
		}

		this.totalPrice = Math.round(totalWeight * country.getDeliveryCost() * 100.0) / 100.0;
		*/
    }

    @Override
    public Double getPrice() {
        Double totalWeight = 0.0;

        for(AProduct product : super.getProducts()) {
            totalWeight += product.getWeight();
        }

        return Math.round(totalWeight * this.deliveryPrice * 100.0) / 100.0;
    }

}
