package Shop;

import java.util.ArrayList;
import java.util.List;

import Factories.IProductFactory;
import Factories.SpoilerFactory;
import Factories.WheelFactory;
import Products.AProduct;
import Factories.BumperFactory;


public class Shop {
    private List<AProduct> products;
    private List<User> users;
    private static final int NUMBER_OF_FIELDS = 7;

    public Shop() {
        this.products = new ArrayList<AProduct>();
        this.users = new ArrayList<User>();
    }

    public void populateShop(String products, String users) {
        String[] productList = products.split(", ");
        IProductFactory factory = null;
        for(int i = 0; i < productList.length; i += NUMBER_OF_FIELDS) {
            switch(productList[i]) {
                case "Bumper":
                    factory = new BumperFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                case "Spoiler":
                    factory = new SpoilerFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                case "Wheel":
                    factory = new WheelFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                default:

                    System.out.println("Incorrect item.");
            }
            this.addProduct(factory.getProduct());
        }

        String[] userList = users.split(", ");
        for(String name : userList) {
            this.addUser(new User(name));
        }
    }

    public List<AProduct> getProducts() {
        return this.products;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addProduct(AProduct product) {
        this.products.add(product);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void makePurchase(User user) {
        List<AProduct> userCart = user.getCart().getProducts();

        Boolean available = true;
        for(AProduct item : userCart) {
            if(!item.available()) {
                available = false;
            }
        }

        if(available) {
            for(AProduct item : userCart) {
                item.buyItem();
            }
            System.out.println("Purchase complete.");
            user.makeInvoice();
        } else {
            System.out.println("One or more items is unavailable.");
        }
    }
}

