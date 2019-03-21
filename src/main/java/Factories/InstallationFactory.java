package Factories;

import Bookkeeping.Country;
import Services.AService;
import Products.AProduct;
import Services.Installation;

import java.util.List;

public class InstallationFactory implements IServiceFactory {
    private Installation installation;

    public InstallationFactory(List<AProduct> products, Country country) {
        this.installation = new Installation(products, country);
    }

    public AService getService() {
        return this.installation;
    }
}
