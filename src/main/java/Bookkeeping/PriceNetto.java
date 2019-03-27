package Bookkeeping;

import Services.AService;
import Products.AProduct;

import java.util.ArrayList;
import java.util.List;

public class PriceNetto {

    private Double totalPrice = 0.0;
    private List<AService> services = new ArrayList<AService>();

    public PriceNetto(List<AProduct> products) {
        for(AProduct product : products) {
            this.totalPrice += product.getPrice();
        }
    }

    public void addService(AService service) {
        this.services.add(service);
    }

    public Double getPrice() {
        Double totalServicePrice = 0.0;
        for(AService service : this.services) {
            totalServicePrice += service.getPrice();
        }
        return this.totalPrice + totalServicePrice;
    }

}
