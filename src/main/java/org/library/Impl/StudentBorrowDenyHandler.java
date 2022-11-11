package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Controller.BaseHttpHandler;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;

public class StudentBorrowDenyHandler extends BaseHttpHandler {

    @Override
    public void tryHandle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){
            Iterable<BookBorrow> bookBorrowDenyIterable=BookBorrow.getDenyList();
            BaseMarshalling<BookBorrow> bs= new BaseMarshalling<>();
            exchange.getResponseHeaders().set("Content-Type","application/json");
            exchange.sendResponseHeaders(200, bs.getResponseLength(bookBorrowDenyIterable, "Deny Records"));
            OutputStream os=bs.getOutputStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
        else {
            exchange.sendResponseHeaders(405,-1);
        }
    }
}
