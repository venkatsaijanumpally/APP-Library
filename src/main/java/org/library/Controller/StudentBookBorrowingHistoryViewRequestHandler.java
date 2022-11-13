package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;

public class StudentBookBorrowingHistoryViewRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Iterable<BookBorrow> bookBorrowIterable=BookBorrow.getBookBorrowRecords();
        BaseMarshalling<BookBorrow> bs= new BaseMarshalling<>();
        exchange.sendResponseHeaders(200, bs.getResponseLength(bookBorrowIterable, "Records"));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
