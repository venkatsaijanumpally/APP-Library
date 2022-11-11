package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.Map;

public class BookPricingHandlerFactory {
    private Map<String, HttpHandler> bookPricingRequestMethodToHandlerMap = new HashMap<>();
    public BookPricingHandlerFactory() {
        bookPricingRequestMethodToHandlerMap.put("POST", new BookPricingUpdateRequestHandler());
        bookPricingRequestMethodToHandlerMap.put("GET", new BookPricingViewRequestHandler());
    }
    public HttpHandler getHttpHandler(HttpExchange exchange) {
        return bookPricingRequestMethodToHandlerMap.get(exchange.getRequestMethod());
    }
}
