package Shop;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Products.AProduct> products;
    private List<User> users;
    private static final NUMBER_OF_FIELDS = 7L;

    public Shop() {
        this.products = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void populateShop(String products, String users) {
        String[] productList = products.split(", ");
        for(int i = 0; i < productList.length; i += NUMBER_OF_FIELDS) {
            switch(productList[i]) {
                case "Bumper":
                    ProductFactory factory = new BumperFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                case "Spoiler":
                    ProductFactory factory = new SpoilerFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                case "Wheel":
                    ProductFactory factory = new WheelFactory(Integer.parseInt(productList[i + 1]), Double.parseDouble(productList[i + 2]), productList[i + 3], productList[i + 4], productList[i + 5], Double.parseDouble(productList[i + 6]));
                    break;
                default:
                    System.out.println("Incorrect item.");
            }
            this.products.add(factory.getProduct());
        }

        String[] userList = users.split(", ");
        for(String name : userList) {
            this.users.add(new User(name));
        }
    }

    public List<IProduct> getProducts() {
        return this.products;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void makePurchase(User user) {
        List<IProduct> userCart = user.getCart().getProducts();

        Boolean available = true;
        for(IProduct item : userCart) {
            if(!item.available()) {
                available = false;
            }
        }

        if(available) {
            for(IProduct item : userCart) {
                item.buyItem();
            }
            System.out.println("Purchase complete.");
            user.makeInvoice();
        } else {
            System.out.println("One or more items is unavailable.");
        }
    }
}

