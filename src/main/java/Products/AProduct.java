package Products;

import java.util.ArrayList;
import java.util.List;

public abstract class AProduct {
    private Integer quantity;
    private Double price;
    private String color;
    private List<Feedback> feedback;
    private String brand;
    private String name;
    private Double weight;

    public AProduct(Integer quantity, Double price, String color, String brand, String name, Double weight) {
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.brand = brand;
        this.name = name;
        this.weight = weight;

        this.feedback = new ArrayList<Feedback>();
    }

    public void addFeedback(String comment, Integer raiting) {
        this.feedback.add(new Feedback(comment, raiting));
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public List<Feedback> getFeedback() {
        return this.feedback;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getName() {
        return this.name;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void buyItem() {
        if(available()) {
            this.quantity--;
        }
    }

    public Boolean available() {
        return this.quantity > 0;
    }

    public String getInfo() {
        //Get basic info
        String info = "";
        info += "Name: " + this.name;
        info += "\nBrand: " + this.brand;
        info += "\nColor: " + this.color;
        info += "\nPrice: " + this.price + "EUR net.";
        info += "\nAvailable units: " + this.quantity;

        //Get item specific info
        info += this.getSpecificInfo();

        //Get feedback if available
        if(this.feedback.size() > 0) {
            info += "\nFeedback: ";
            for(Feedback feed : this.feedback) {
                info += "\nRaiting: " + feed.getRaiting();
                info += "\nComment: " + feed.getComment();
            }
        }

        return info;
    }

    public abstract String getSpecificInfo();
}
