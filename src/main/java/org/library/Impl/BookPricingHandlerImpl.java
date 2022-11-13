package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.BookPricingHandlerFactory;

import java.io.IOException;

public class BookPricingHandlerImpl extends BaseHttpHandler {
    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        BookPricingHandlerFactory pricingHandlerFactory=new BookPricingHandlerFactory();
        if(pricingHandlerFactory.getHttpHandler(exchange)!=null)
            pricingHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
