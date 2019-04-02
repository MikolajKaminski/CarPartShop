package Products;

public class Feedback {
    private String comment;
    private Integer rating;

    public Feedback(String comment, Integer rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public String getComment() {
        return this.comment;
    }

    public Integer getRating() {
        return this.rating;
    }

}
