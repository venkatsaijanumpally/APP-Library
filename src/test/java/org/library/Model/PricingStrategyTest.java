package org.library.Model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PricingStrategyTest {
    final int EXPECTED_DOUBLE_VALUE = 2;
    final int EXPECTED_SINGLE_VALUE = 1;
    PricingStrategy pricingStrategy;

    @Test
    public void singlePricingStrategyTest(){
        pricingStrategy=new SinglePricingStrategy();
        Assert.assertEquals(pricingStrategy.getPerDayCost(),EXPECTED_SINGLE_VALUE);
        pricingStrategy=null;
    }

    @Test
    public void doublePricingStrategyTest(){
        pricingStrategy=new DoublePricingStrategy();
        Assert.assertEquals(pricingStrategy.getPerDayCost(),EXPECTED_DOUBLE_VALUE);
        pricingStrategy=null;
    }
}
