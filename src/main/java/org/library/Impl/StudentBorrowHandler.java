package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.StudentBorrowRequestHandlerFactory;

import java.io.IOException;

public class StudentBorrowHandler extends BaseHttpHandler {
    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        StudentBorrowRequestHandlerFactory borrowRequestHandlerFactory = new StudentBorrowRequestHandlerFactory();
        if (borrowRequestHandlerFactory.getHttpHandler(exchange) != null)
            borrowRequestHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405, -1);
    }
}
