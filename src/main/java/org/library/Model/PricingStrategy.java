package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public interface PricingStrategy {
    int getPerDayCost();
}
