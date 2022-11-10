package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

//@BsonDiscriminator(value = "Double",key = "_t")
@BsonDiscriminator
public class DoublePricingStrategy implements PricingStrategy {
    //@BsonProperty(value = "cost_per_day")
    private final int COST_PER_DAY=2;

    public DoublePricingStrategy(){}

    @Override
    public int getPerDayCost() {
        return COST_PER_DAY;
    }

    /*public int getCOST_PER_DAY() {
        return COST_PER_DAY;
    }*/
}
