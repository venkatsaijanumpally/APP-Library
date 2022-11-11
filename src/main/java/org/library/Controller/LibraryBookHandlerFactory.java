package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.Map;

public class LibraryBookHandlerFactory {
    private Map<String, HttpHandler> libraryBookRequestMethodToHandlerMap = new HashMap<>();
    public LibraryBookHandlerFactory() {
        libraryBookRequestMethodToHandlerMap.put("POST", new LibraryBookCreateRequestHandler());
        libraryBookRequestMethodToHandlerMap.put("GET", new LibraryBookViewRequestHandler());
    }
    public HttpHandler getHttpHandler(HttpExchange exchange) {
        return libraryBookRequestMethodToHandlerMap.get(exchange.getRequestMethod());
    }
}
