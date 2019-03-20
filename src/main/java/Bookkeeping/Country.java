package Bookkeeping;

public class Country {

    private Integer vat;
    private Double deliveryCost;
    private Double installationCost;

    public Country(Integer vat, Double deliveryCost, Double installationCost) {
        this.vat = vat;
        this.deliveryCost = deliveryCost;
        this.installationCost = installationCost;
    }

    public Integer getVat() {
        return this.vat;
    }

    public Double getDeliveryCost() {
        return this.deliveryCost;
    }

    public Double getInstallationCost() {
        return this.installationCost;
    }

}
