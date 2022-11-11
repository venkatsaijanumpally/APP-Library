package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentRequestHandlerFactory {
    private final Map<String, HttpHandler> studentRequestMethodToHandlerMap = new HashMap<>();
    public StudentRequestHandlerFactory() {
        studentRequestMethodToHandlerMap.put("POST", new StudentCreateRequestHandler());
        studentRequestMethodToHandlerMap.put("GET", new StudentGetRequestHandler());
        studentRequestMethodToHandlerMap.put("DELETE", new StudentDeleteRequestHandler());
    }
    public HttpHandler getHttpHandler(HttpExchange exchange) throws IOException {
        return studentRequestMethodToHandlerMap.get(exchange.getRequestMethod());
    }
}
