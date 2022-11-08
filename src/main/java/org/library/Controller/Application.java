package org.library.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import org.library.Impl.BookHandler;
import org.library.Impl.StudentBorrowDenyHandler;
import org.library.Impl.StudentBorrowHandler;
import org.library.Impl.StudentHandler;

public class Application {

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/hello", (exchange -> {
            String respText = "Hello!";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.createContext("/api/he", (exchange -> {
            if(!"GET".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(405,-1);
            }
            else{
                String respText = "{\n" +
                        "  \"status\" : \"ALLOWED\",\n" +
                        "  \"email\" : \"abhi@yahoo.in\",\n" +
                        "  \"program\" : \"CSE\",\n" +
                        "  \"phone\" : \"2345678910\",\n" +
                        "  \"id\" : 0\n" +
                        "}";
                exchange.getResponseHeaders().set("Content-Type","application/json");
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
                exchange.close();
            }
        }));

        server.createContext("/student", new StudentHandler());

        server.createContext("/student/borrow", new StudentBorrowHandler());

        server.createContext("/student/borrow/deny", new StudentBorrowDenyHandler());

        server.createContext("/book", new BookHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
    }
}