package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.StudentRequestHandlerFactory;

import java.io.IOException;

public class StudentHandler extends BaseHttpHandler {

    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        StudentRequestHandlerFactory requestHandlerFactory=new StudentRequestHandlerFactory();
        if(requestHandlerFactory.getHttpHandler(exchange)!=null)
            requestHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
