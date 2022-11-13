package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Model.Catalog;
import org.library.Model.PricingStrategy;

import java.io.IOException;
import java.io.OutputStream;

public class BookPricingViewRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PricingStrategy pricingStrategy= Catalog.getPricingStrategy();
        BaseMarshalling<PricingStrategy> bs= new BaseMarshalling<>();
        exchange.sendResponseHeaders(200, bs.getResponseLength(pricingStrategy));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
