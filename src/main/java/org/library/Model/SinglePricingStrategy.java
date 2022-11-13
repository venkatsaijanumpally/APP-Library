package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public class SinglePricingStrategy implements PricingStrategy {
    private final int COST_PER_DAY = 1;

    public SinglePricingStrategy(){ }

    @Override
    public int getPerDayCost() {
        return COST_PER_DAY;
    }
}
