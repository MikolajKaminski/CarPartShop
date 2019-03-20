import org.junit.Test;
import org.junit.Before;

import Shop.Shop;

public class TestClass {

    private Shop shop;
    private String products = "Bumper, 3, 200.00, black, Mazda, Mazda Bumper Black, 30.00, " +
            "Bumper, 2, 150.00, red, Mazda, Mazda Bumper Red, 30.00, " +
            "Bumper, 1, 400.00, black, BMW, BMW Bumper Black, 45.00, " +
            "Spoiler, 5, 750.00, black, Mazda, Mazda Spoiler Black, 15.00, " +
            "Wheel, 4, 150.00, silver, Mazda, Mazda Wheel, 7.00, " +
            "Wheel, 20, 200.00, silver, BMW, BMW Wheel, 12.00";
    private String users = "Nick, Martin";

    private Double i;

    @Before
    public void Before(){
        this.i = 0.0;
    }

    @Test
    public void FirstTestCase() {
        //Arrange

        //Act
        this.i += 1.0;

        //Assert
        assert(this.i == 1.0);
    }

}
