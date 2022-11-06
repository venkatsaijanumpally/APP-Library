package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentBorrowHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            String respText = "Book Borrowed";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            //Map<String,String> params=queryToMap(exchange.getRequestURI().getQuery());
            //Student.loanBooks(Integer.parseInt(params.get("id")),Integer.parseInt(params.get("book")));
            Map<String,String> attributes=BaseUnMarshalling.parse(exchange.getRequestBody());
            new BookBorrow(attributes);

            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }
        else if("GET".equals(exchange.getRequestMethod())){
            Iterable<BookBorrow> bookBorrowIterable=BookBorrow.getBookBorrowRecords();
            BaseMarshalling<BookBorrow> bs= new BaseMarshalling<>();
            exchange.getResponseHeaders().set("Content-Type","application/json");
            exchange.sendResponseHeaders(200, bs.getResponseLength(bookBorrowIterable, "Records"));
            OutputStream os=bs.getOutputStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
        else {
            exchange.sendResponseHeaders(405,-1);
        }
    }
}
