package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Controller.BaseHttpHandler;
import org.library.Controller.ExceptionHandler;
import org.library.Controller.LibraryBookHandlerFactory;
import org.library.Model.LibraryBook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookHandler extends BaseHttpHandler {
    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        LibraryBookHandlerFactory libraryBookHandlerFactory=new LibraryBookHandlerFactory();
        if(libraryBookHandlerFactory.getHttpHandler(exchange)!=null)
            libraryBookHandlerFactory.getHttpHandler(exchange).handle(exchange);
        else exchange.sendResponseHeaders(405,-1);
    }
}
