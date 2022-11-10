package org.library.Impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.bson.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.library.Model.Catalog;
import org.library.Model.DoublePricingStrategy;
import org.library.Model.PricingStrategy;
import org.library.Model.SinglePricingStrategy;
import org.library.Utils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookPricingHandlerImplTest {

    HttpUriRequest request = new HttpGet( "http://localhost:8000/book/pricing");

    @Test
    public void testStatusCode() throws IOException {
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void testResponseJSON() throws IOException {
        Catalog.updatePricingStrategy(1);
        HttpResponse response1 = HttpClientBuilder.create().build().execute( request );
        Assert.assertEquals(Utils.retrieveResourceFromResponse(response1, SinglePricingStrategy.class).getPerDayCost(),1);
        Catalog.updatePricingStrategy(2);
        HttpResponse response2 = HttpClientBuilder.create().build().execute( request );
        Assert.assertEquals(Utils.retrieveResourceFromResponse(response2, DoublePricingStrategy.class).getPerDayCost(),2);
    }

}
