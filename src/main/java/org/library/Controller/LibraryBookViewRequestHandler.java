package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Model.LibraryBook;

import java.io.IOException;
import java.io.OutputStream;

public class LibraryBookViewRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Iterable<LibraryBook> books=LibraryBook.getBooks();
        BaseMarshalling<LibraryBook> bs= new BaseMarshalling<>();
        exchange.sendResponseHeaders(200, bs.getResponseLength(books, "Books"));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
