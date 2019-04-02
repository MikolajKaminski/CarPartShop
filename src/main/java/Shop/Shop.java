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

    public Shop() {
        this.products = new ArrayList<>();
        this.users = new ArrayList<User>();
    }

    public void populateShop(String products, String users) {
        String[] productList = products.split(", ");
        IProductFactory factory = null;
        for(int i = 0; i < productList.length; i++) {
            switch(productList[i]) {
                case "Bumper":
                    factory = new BumperFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]), Integer.parseInt(productList[i + 7]), productList[i + 8]);
                    i += 8;
                    break;
                case "Spoiler":
                    factory = new SpoilerFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]), productList[i + 7]);
                    i += 7;
                    break;
                case "Wheel":
                    factory = new WheelFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]), productList[i + 7]);
                    i += 7;
                    break;
                default:
                    System.out.println("Incorrect item.");
            }
            this.addProduct(factory.getProduct());
        }

        String[] userList = users.split(", ");
        for (int i = 0; i < userList.length; i += 2) {
            String userName = userList[i];
            String userCountry = userList[i + 1];
            this.addUser(new User(userName, userCountry));
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
            System.out.println(user.getName() + " purchase completed.");
            user.makeInvoice();
        } else {
            System.out.println("One or more items is unavailable.");
        }
    }
}

