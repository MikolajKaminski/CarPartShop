package Products;

public class Feedback {
    private String comment;
    private Integer raiting;

    public Feedback(String comment, Integer raiting) {
        this.comment = comment;
        this.raiting = raiting;
    }

    public String getComment() {
        return this.comment;
    }

    public Integer getRaiting() {
        return this.raiting;
    }

}
