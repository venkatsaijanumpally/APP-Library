package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public class DoublePricingStrategy implements PricingStrategy {
    private final int COST_PER_DAY=2;

    public DoublePricingStrategy(){}

    @Override
    public int getPerDayCost() {
        return COST_PER_DAY;
    }
}
