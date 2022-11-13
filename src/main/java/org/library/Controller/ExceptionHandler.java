package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.ExceptionResolver;

import java.io.IOException;
import java.io.OutputStream;

public class ExceptionHandler implements HttpHandler {
    ExceptionResolver exceptionResolver;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(exceptionResolver.getErrorCode(), exceptionResolver.getResponseLength());
        OutputStream os= exceptionResolver.getResponseStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }

    public void handle(HttpExchange exchange, Exception exception) throws IOException {
        exceptionResolver=new ExceptionResolver(exception);
        handle(exchange);
    }
}
