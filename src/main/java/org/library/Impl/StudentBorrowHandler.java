package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.ExceptionHandler;
import org.library.Controller.StudentBorrowRequestHandlerFactory;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentBorrowHandler extends BaseHttpHandler {
    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        StudentBorrowRequestHandlerFactory borrowRequestHandlerFactory=new StudentBorrowRequestHandlerFactory();
        if(borrowRequestHandlerFactory.getHttpHandler(exchange)!=null)
            borrowRequestHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
