package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public abstract class BaseHttpHandler implements HttpHandler {
    public abstract void tryHandle(HttpExchange exchange) throws IOException;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            tryHandle(exchange);
        }
        catch (Exception e){
            ExceptionHandler exceptionHandler=new ExceptionHandler();
            exceptionHandler.handle(exchange,e);
        }
    }
}
