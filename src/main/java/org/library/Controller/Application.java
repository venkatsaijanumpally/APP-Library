package org.library.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;
import org.library.Impl.*;
import org.library.Model.ScheduledTask;

public class Application {

    public static void main(String[] args) throws IOException {
        int serverPort = ConstantValues.SERVER_PORT;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(2);
        Runnable scheduledTask=new ScheduledTask();
        scheduledThreadPoolExecutor.schedule(scheduledTask, ConstantValues.SCHEDULED_UPDATE_INTERVAL, TimeUnit.SECONDS);

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

        server.createContext("/book/pricing", new BookPricingHandlerImpl());

        server.setExecutor(null); // creates a default executor
        server.start();
    }
}