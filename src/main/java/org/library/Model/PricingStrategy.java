package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

//@BsonDiscriminator(value = "Pricing", key = "_t")
@BsonDiscriminator
public interface PricingStrategy {
    int getPerDayCost();
}
