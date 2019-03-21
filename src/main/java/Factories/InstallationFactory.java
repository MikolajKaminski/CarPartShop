package Factories;

public class InstallationFactory implements IServiceFactory {
    private Installation installation;

    public InstallationFactory(List<IProduct> products, Country country) {
        this.installation = new Installation(products, country);
    }

    @Override
    public Service getService() {
        return this.installation;
    }
}
