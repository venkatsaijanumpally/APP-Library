package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.ExceptionHandler;
import org.library.Controller.StudentRequestHandlerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class StudentHandler extends BaseHttpHandler {

    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        StudentRequestHandlerFactory requestHandlerFactory=new StudentRequestHandlerFactory();
        if(requestHandlerFactory.getHttpHandler(exchange)!=null)
            requestHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
