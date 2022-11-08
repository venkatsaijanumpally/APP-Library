package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Model.LibraryBook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())) {
            String respText = "Created Book";
            Map<String, String> attributes = BaseUnMarshalling.parse(exchange.getRequestBody());
            new LibraryBook(attributes);
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }
        else if("GET".equals(exchange.getRequestMethod())) {
            Iterable<LibraryBook> books=LibraryBook.getBooks();
            BaseMarshalling<LibraryBook> bs= new BaseMarshalling<>();
            exchange.getResponseHeaders().set("Content-Type","application/json");
            exchange.sendResponseHeaders(200, bs.getResponseLength(books, "Books"));
            OutputStream os=bs.getOutputStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
    }
}
