package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Impl.BaseUnMarshalling;
import org.library.Model.BookBorrow;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentBookReturnRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String,String> attributes= BaseUnMarshalling.parse(exchange.getRequestBody());
        BookBorrow bookBorrowRecord=BookBorrow.deleteRecord(Integer.parseInt(attributes.get("id")),Integer.parseInt(attributes.get("book_id")));
        BaseMarshalling<BookBorrow> bs= new BaseMarshalling<>();
        exchange.getResponseHeaders().set("Content-Type","application/json");
        exchange.sendResponseHeaders(200, bs.getResponseLength(bookBorrowRecord));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
