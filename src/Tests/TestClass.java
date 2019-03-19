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
        i += 1.0;

        //Assert
        assert(i == 1.0);
    }

}