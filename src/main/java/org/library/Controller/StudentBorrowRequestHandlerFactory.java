package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.Map;

public class StudentBorrowRequestHandlerFactory {

    private Map<String, HttpHandler> studentBorrowRequestMethodToHandlerMap = new HashMap<>();
    public StudentBorrowRequestHandlerFactory() {
        studentBorrowRequestMethodToHandlerMap.put("POST", new StudentBookBorrowRequestHandler());
        studentBorrowRequestMethodToHandlerMap.put("GET", new StudentBookBorrowingHistoryViewRequestHandler());
        studentBorrowRequestMethodToHandlerMap.put("DELETE", new StudentBookReturnRequestHandler());
    }
    public HttpHandler getHttpHandler(HttpExchange exchange) {
        return studentBorrowRequestMethodToHandlerMap.get(exchange.getRequestMethod());
    }
}
