package Bookkeeping;

import Products.AProduct;

import java.util.List;

public class PriceBrutto extends PriceNetto {

    private Double vat;

    public PriceBrutto(List<AProduct> products, Integer vat) {
        super(products);
        this.vat = new Double(vat) / 100.0;
    }

    public Double getPrice() {
        Double vatAmount = Math.round(super.getPrice() * this.vat * 100.0) / 100.0;
        return super.getPrice() + vatAmount;
    }

}
