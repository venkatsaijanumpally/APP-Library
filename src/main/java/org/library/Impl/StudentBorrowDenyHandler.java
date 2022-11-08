package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;

public class StudentBorrowDenyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
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
        catch (Exception e){
            exchange.getResponseHeaders().set("Content-Type","application/json");
            ExceptionHandler exceptionHandler=new ExceptionHandler(e);
            exchange.sendResponseHeaders(exceptionHandler.getErrorCode(), exceptionHandler.getResponseLength());
            OutputStream os=exceptionHandler.getResponseStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
    }
}
