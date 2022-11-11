package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.BookPricingHandlerFactory;
import org.library.Controller.BookPricingViewRequestHandler;
import org.library.Controller.ExceptionHandler;
import org.library.Model.Catalog;
import org.library.Model.PricingStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookPricingHandlerImpl extends BaseHttpHandler {
    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        BookPricingHandlerFactory pricingHandlerFactory=new BookPricingHandlerFactory();
        if(pricingHandlerFactory.getHttpHandler(exchange)!=null)
            pricingHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
