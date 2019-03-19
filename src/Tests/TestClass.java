package Tests;

import org.junit.Test;
import org.junit.Before;

public class TestClass {

    private Double i;

    @Before
    public void Before(){
        this.i = 0.0;
    }

    @Test
    public void ReductionInQuantityOnPurchase() throws Exception {
        //Arrange

        //Act
        this.i += 1.0;

        //Assert
        assert(this.i == 1.0);
    }

}